/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Offre;
import Entites.Offre_Profil_Util_CV;
import Entites.ProfilMetier;
import Entites.UtilisateurHardis;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author anastasia.salari
 */
@Stateless
public class Offre_Profil_Util_CVFacade extends AbstractFacade<Offre_Profil_Util_CV> implements Offre_Profil_Util_CVFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Offre_Profil_Util_CVFacade() {
        super(Offre_Profil_Util_CV.class);
    }

    @Override
    public void creerOPUC(Offre offre, ProfilMetier PM, UtilisateurHardis utilisateur, String lienCV) {
        Offre_Profil_Util_CV ob = new Offre_Profil_Util_CV();
        ob.setLienCV(lienCV);
        ob.setOffre(offre);
        ob.setProfil(PM);
        ob.setUtilisateur(utilisateur);
        em.persist(ob);
    }

    @Override
    public void modifierOPUC(Offre_Profil_Util_CV object,Offre offre, ProfilMetier PM, UtilisateurHardis utilisateur, String lienCV ) {
   
        if (object!=null){
          
            
            if (offre!=null)
              object.setOffre(offre);
            if (PM!=null)
                  object.setProfil(PM);
             if (!lienCV.equals(""))
             object.setLienCV(lienCV);
             if (utilisateur!=null)
    object.setUtilisateur(utilisateur);
             em.merge(object);
            
        }
    }

    @Override
    public void supprimerOPUC(long id) {
         Offre_Profil_Util_CV object = null;
        Query requete = em.createQuery("SELECT p from Offre_Profil_Util_CV as p where p.id=:id");
        requete.setParameter("id",id);     
        List<Offre_Profil_Util_CV> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            object  =  liste.get(0); 
            em.remove(object);
        }
    }

    @Override
    public Offre_Profil_Util_CV rechercheOPUCParId(long id) {
               Offre_Profil_Util_CV object = null;
        Query requete = em.createQuery("SELECT p from Offre_Profil_Util_CV as p where p.id=:id");
        requete.setParameter("id",id);     
        List<Offre_Profil_Util_CV> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            object  =  liste.get(0); 
            
        }
        return object;
    }

    @Override
    public List<Offre_Profil_Util_CV> rechercheOPUCParUtilisateur(UtilisateurHardis u) {
          
        Query requete = em.createQuery("SELECT p from Offre_Profil_Util_CV as p where p.utilisateur=:u");
        requete.setParameter("u",u);     
        List<Offre_Profil_Util_CV> liste =  requete.getResultList();
       
        return liste;
    }

    @Override
    public List<Offre_Profil_Util_CV> rechercheOPUCParPM(ProfilMetier pm) {
        Query requete = em.createQuery("SELECT p from Offre_Profil_Util_CV as p where p.profil=:pm");
        requete.setParameter("pm",pm);     
        List<Offre_Profil_Util_CV> liste =  requete.getResultList();   
        return liste;
    }
    
    
    
    
    
    
    
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Expertise;
import Entites.NiveauHabilitation;
import Entites.Offre_Profil_Util_CV;
import Entites.ProfilMetier;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gabrielleite



/**
 *
 * @author anastasia.salari

 */
@Stateless
public class ProfilMetierFacade extends AbstractFacade<ProfilMetier> implements ProfilMetierFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfilMetierFacade() {
        super(ProfilMetier.class);
    }



    @Override
    public void creerProfilMetier(NiveauHabilitation niveau, Expertise expertise, float plafond, List<Offre_Profil_Util_CV> offre_p_util_cv) {
        ProfilMetier PM = new ProfilMetier();
        PM.setNiveauExpertise(expertise);
        PM.setNiveauHabilitation(niveau);
        PM.setPlafond(plafond);
        PM.setOffre_Profil_Utils(offre_p_util_cv);
        em.persist(PM);
    }

    @Override
    public void modifierProfilMetier(ProfilMetier pm,NiveauHabilitation niveau, Expertise expertise, float plafond, List<Offre_Profil_Util_CV> offre_p_util_cv ) {
       
        if (pm!=null){
            
            if (expertise!=null)
                pm.setNiveauExpertise(expertise);
            if (niveau!=null)
                pm.setNiveauHabilitation(niveau);
             if (plafond>=0)
                pm.setPlafond(plafond);
             if (offre_p_util_cv!=null)
                 pm.setOffre_Profil_Utils(offre_p_util_cv);
             em.merge(pm);
            
        }
    }

    @Override
    public void supprimerProfilMetier(long id) {
          ProfilMetier pm = null;
        Query requete = em.createQuery("SELECT p from ProfilMetier as p where p.id=:id");
        requete.setParameter("id",id);     
        List<ProfilMetier> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            pm  =  liste.get(0); 
            em.remove(pm);
        }
    }

    @Override
    public ProfilMetier recherchePMParId(long id) {
        ProfilMetier pm = null;
        Query requete = em.createQuery("SELECT p from ProfilMetier as p where p.id=:id");
        requete.setParameter("id",id);     
        List<ProfilMetier> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            pm  = (ProfilMetier) liste.get(0); 
            
        }
        return pm;
    }

    @Override
    public List<ProfilMetier> recherchePMParExpertise(Expertise exp) {
        Query requete = em.createQuery("SELECT p from ProfilMetier as p where p.niveauExpertise=:exp");
        requete.setParameter("exp",exp);     
        List<ProfilMetier> liste =  requete.getResultList();
      
        return liste;
    }

    @Override
    public List<ProfilMetier> recherchePMParHabilitation(NiveauHabilitation habi) {
         Query requete = em.createQuery("SELECT p from ProfilMetier as p where p.niveauHabilitation=:habi");
        requete.setParameter("habi",habi);     
        List<ProfilMetier> liste =  requete.getResultList();
      
        return liste;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
}

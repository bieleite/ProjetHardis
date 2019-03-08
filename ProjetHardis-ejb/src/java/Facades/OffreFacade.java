/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Offre;
<<<<<<< HEAD
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gabrielleite
=======
import Entites.Offre_Profil_Util_CV;
import Entites.ProfilMetier;
import Entites.Service;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author anastasia.salari
>>>>>>> origin/v3
 */
@Stateless
public class OffreFacade extends AbstractFacade<Offre> implements OffreFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OffreFacade() {
        super(Offre.class);
    }
<<<<<<< HEAD
=======

    @Override
    public void creerOffre(String lib, List<Offre_Profil_Util_CV> liste) {
     Offre offre = new Offre();
     offre.setLibelle(lib);
     offre.setOffre_Profil_Utils(liste);
     em.persist(offre);
    }

    @Override
    public void modifierOffre(Offre offre ,  List<Offre_Profil_Util_CV> liste, String lib ) {
        Offre o = null;
        Query requete = em.createQuery("SELECT s from Offre as s where s.id=:id");
        requete.setParameter("id",offre.getId());     
        List<Offre> listeO =  requete.getResultList();
        if (!listeO.isEmpty()){
            o =   listeO.get(0);
            if (lib.equals(""))
                offre.setLibelle(lib);
            if (liste!=null)
                  offre.setOffre_Profil_Utils(liste);
            em.merge(o);
    }
    
    }

    @Override
    public void supprimerOffre(long id) {
        Offre pm = null;
        Query requete = em.createQuery("SELECT p from Offre as p where p.id=:id");
        requete.setParameter("id",id);     
        List<Offre> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            pm  =  liste.get(0); 
            em.remove(pm);
        }
    }

    @Override
    public Offre rechercheOffreParId(long id) {
        Offre s = null;
        Query requete = em.createQuery("SELECT s from Offre as s where s.id=:id");
        requete.setParameter("id",id);     
        List<Offre> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            s =   liste.get(0); 
        }
        return s;
    }
    
    
    
    
    
   
>>>>>>> origin/v3
    
}

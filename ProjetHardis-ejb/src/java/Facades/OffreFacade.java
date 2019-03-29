/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Agence;
import Entites.Offre;
import Entites.Offre_Profil_Util_CV;
import Entites.Service;
import Entites.ServiceStandard;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gabrielleite

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

    
    


    @Override
    public Offre creerOffre(String lib) {
     Offre offre = new Offre();
     offre.setLibelle(lib);
     offre.setOffre_Profil_Utils(new ArrayList<>());
     em.persist(offre);
     return offre;
    }

    @Override
    public void modifierOffre(Offre offre ,  List<Offre_Profil_Util_CV> liste, String lib ) {
      
        if (offre!=null){
            if (lib.equals(""))
                offre.setLibelle(lib);
            if (liste!=null)
                  offre.setOffre_Profil_Utils(liste);
            em.merge(offre);
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
            s =   (Offre)liste.get(0); 
        }
        return s;
    }

    @Override
    public Offre rechercheOffreParLibelle(String lib) {
        Offre s = null;
        Query requete = em.createQuery("SELECT s from Offre as s where s.libelle=:lib");
        requete.setParameter("lib",lib);     
        List<Offre> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            s =   (Offre)liste.get(0); 
        }
        return s;
    }
    
    @Override
    public Offre rechercheOffreParService(Service lib) {
        Offre s = null;
        Query requete = em.createQuery("SELECT s from Offre as s where s.services=:lib");
        requete.setParameter("lib",lib);     
        List<Offre> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            s =   (Offre)liste.get(0); 
        }
        return s;
    }
    
    @Override
    public Offre rechercheOffreParServiceS(ServiceStandard lib) {
        Offre s = null;
        Query requete = em.createQuery("SELECT s from Offre as s where s.services=:lib");
        requete.setParameter("lib",lib);     
        List<Offre> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            s =   (Offre)liste.get(0); 
        }
        return s;
    }
    
    @Override
    public List<Offre> listOffres() {
        String txt="SELECT ad FROM Offre AS ad ";
        Query req=getEntityManager().createQuery(txt);
        List<Offre> result=req.getResultList();
        return result;
    }
    
    
    
    
    
   

    
}

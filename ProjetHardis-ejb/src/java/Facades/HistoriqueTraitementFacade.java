/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;


import Entites.Devis;
import Entites.HistoriqueTraitement;
import Entites.TypeUtilisateur;
import Entites.UtilisateurHardis;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gabrielleite

import Entites.HistoriqueTraitement;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author anastasia.salari

 */
@Stateless
public class HistoriqueTraitementFacade extends AbstractFacade<HistoriqueTraitement> implements HistoriqueTraitementFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    @Override
    public HistoriqueTraitement creerHistoriqueTraitement( Date datedebut, Date datefin,TypeUtilisateur utilisateurcourant, Devis devis,UtilisateurHardis consultant, UtilisateurHardis reflocal, UtilisateurHardis validateur ) {
        HistoriqueTraitement ht = new HistoriqueTraitement();
        ht.setDateDebut(datedebut);
        ht.setDateFin(datefin);
        ht.setUtilisateurCourant(utilisateurcourant);
        ht.setDevis(devis);
        ht.setConsultant(consultant);
        ht.setRefLocal(reflocal);
        ht.setValidateur(validateur);
        em.persist(ht);
        return ht;
    }
    
    @Override
    public List<HistoriqueTraitement> listHistoriqueTraitement() {
        String txt="SELECT co FROM HistoriqueTraitement AS co ";
        Query req=getEntityManager().createQuery(txt);
        List<HistoriqueTraitement> result=req.getResultList();
        return result;
    }

    @Override
    public HistoriqueTraitement rechercheHistoriqueTraitement(Long id) {
        HistoriqueTraitement he = null;        
        String txt = "SELECT he FROM HistoriqueTraitement AS he WHERE he.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);  
        List<HistoriqueTraitement> res = req.getResultList();
        if (res.size() >= 1)
        {
              he = (HistoriqueTraitement) res.get(0);
        }
        return he;
    }

    @Override
    public   List<HistoriqueTraitement> rechercheHistoriqueTraitementParDevis(Devis devis) {
        HistoriqueTraitement he = null;        
        String txt = "SELECT he FROM HistoriqueTraitement AS he WHERE he.devis=:devis ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("devis",devis);
        List<HistoriqueTraitement> res = req.getResultList();
      
        return res;
    }
    
    @Override
    public   List<HistoriqueTraitement> rechercheHistoriqueTraitementParConsultant(UtilisateurHardis consultant) {
        HistoriqueTraitement he = null;        
        String txt = "SELECT he FROM HistoriqueTraitement AS he WHERE he.consultant=:consultant ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("consultant",consultant);
        List<HistoriqueTraitement> res = req.getResultList();
        
        return res;
    }
    
    @Override
    public   List<HistoriqueTraitement> rechercheHistoriqueTraitementParValidateur(UtilisateurHardis validateur) {
        HistoriqueTraitement he = null;        
        String txt = "SELECT he FROM HistoriqueTraitement AS he WHERE he.validateur=:validateur ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("validateur",validateur );
        List<HistoriqueTraitement> res = req.getResultList();
     
        return res;
    }
    
    @Override
    public   List<HistoriqueTraitement> rechercheHistoriqueTraitementParUtilisateurCourant(UtilisateurHardis utilisateurCourant) {
        HistoriqueTraitement he = null;        
        String txt = "SELECT he FROM HistoriqueTraitement AS he WHERE he.utilisateurCourant=:utilisateurCourant ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("utilisateurCourant",utilisateurCourant );
        List<HistoriqueTraitement> res = req.getResultList();
       
        return res;
    }
    
    
    
    @Override
    public  void modifHistoriqueTraitement(HistoriqueTraitement entite, Date datedebut, Date datefin,TypeUtilisateur utilisateurcourant, Devis devis,UtilisateurHardis consultant, UtilisateurHardis reflocal, UtilisateurHardis validateur) {       
     
        if (entite!=null){

       
            if (datedebut!=null)
        {
            entite.setDateDebut(datedebut);
        }
            if (datefin!=null)
        {
            entite.setDateFin(datefin);
        }
            if (utilisateurcourant!=null)
        {
            entite.setUtilisateurCourant(utilisateurcourant);
        }
            if (devis!=null)
        {
            entite.setDevis(devis);
        }
            if (consultant!=null)
        {
            entite.setConsultant(consultant);
        }
            if (reflocal!=null)
        {
            entite.setRefLocal(reflocal);
        }
            if (validateur!=null)
        {
            entite.setValidateur(validateur);
        }
            em.merge(entite);
        }
    }


    public HistoriqueTraitementFacade() {
        super(HistoriqueTraitement.class);
    }
    
}

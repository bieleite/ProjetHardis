/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Document;
import Entites.HistoriqueDevis;

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

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author anastasia.salari

 */
@Stateless
public class HistoriqueDevisFacade extends AbstractFacade<HistoriqueDevis> implements HistoriqueDevisFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    @Override
    public void creerHistoriqueDevis( Date datedebut, Date datefin, int numpropo, UtilisateurHardis utilisateur, List<Document> doc) {
        HistoriqueDevis hd = new HistoriqueDevis();
        hd.setDateDebut(datedebut);
        hd.setDateFin(datefin);
        hd.setNumPropo(numpropo);
        hd.setUtilHardis(utilisateur);
        hd.setDocuments(doc);
        em.persist(hd);
    }
    @Override
    public List<HistoriqueDevis> listHistoriqueDevis() {
        List<HistoriqueDevis> co=null;
        String txt="SELECT co FROM HistoriqueDevis AS co ";
        Query req=getEntityManager().createQuery(txt);
        List<HistoriqueDevis> result=req.getResultList();
        return result;
    }

    @Override
    public HistoriqueDevis rechercheHistoriqueDevis(Long id) {
        HistoriqueDevis hd = null;        
        String txt = "SELECT hd FROM HistoriqueDevis AS hd WHERE hd.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);  
        List<HistoriqueDevis> res = req.getResultList();
        if (res.size() >= 1)
        {
              hd = (HistoriqueDevis) res.get(0);
        }
        return hd;
    }

    @Override
    public  HistoriqueDevis rechercheHistoriqueDevisParUtilisateur(UtilisateurHardis utilisateur) {
        HistoriqueDevis di = null;        
        String txt = "SELECT di FROM HistoriqueDevis AS di WHERE di.interlocuteur=:utilisateur ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("utilisateur",utilisateur.getId() );
        List<HistoriqueDevis> res = req.getResultList();
        if (res.size() >= 1)
        {
              di = (HistoriqueDevis) res.get(0);
        }
        return di;
    }
    
     @Override
    public  HistoriqueDevis modificationHistoriqueDevis(HistoriqueDevis hd, Date datedebut, Date datefin, int numpropo, UtilisateurHardis utilisateur) {
               
        String txt = "SELECT hd FROM HistoriqueDevis AS hd WHERE hd.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", hd.getId());
        List<HistoriqueDevis> res = req.getResultList();
        if (res.size() >= 1)
        {
            hd.setDateDebut(datedebut);
            hd.setDateFin(datefin);
            hd.setNumPropo(numpropo);
            hd.setUtilHardis(utilisateur);
            em.merge(hd);
        }
        return hd;
    }
    
    @Override
    public  void modifHistoriqueDevis(HistoriqueDevis entite, Date datedebut, Date datefin, int numpropo, UtilisateurHardis utilisateur,List<Document> doc) {       
        String txt = "SELECT entite FROM HistoriqueDevis AS entite WHERE entite.id=:id ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", entite.getId());
        List<HistoriqueDevis> liste = req.getResultList();
        if (!liste.isEmpty()){
            entite =   liste.get(0);
       
            if (datedebut!=null)
        {
            entite.setDateDebut(datedebut);
        }
            if (datefin!=null)
        {
            entite.setDateFin(datefin);
        }
            if (numpropo!=0)
        {
            entite.setNumPropo(numpropo);
        }
            if (utilisateur!=null)
        {
            entite.setUtilHardis(utilisateur);
        }
            if(!doc.isEmpty()){
                entite.setDocuments(doc);
            }
            em.merge(entite);
        }
    }

    public HistoriqueDevisFacade() {
        super(HistoriqueDevis.class);
    }
    
}

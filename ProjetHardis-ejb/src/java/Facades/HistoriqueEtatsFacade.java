/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;


import Entites.Devis;
import Entites.HistoriqueEtats;
import Entites.Service;
import Entites.Statut;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gabrielleite

import Entites.HistoriqueEtats;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author anastasia.salari

 */
@Stateless
public class HistoriqueEtatsFacade extends AbstractFacade<HistoriqueEtats> implements HistoriqueEtatsFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    @Override
    public void creerHistoriqueEtats( Date datemaj, Statut statut, Devis devis, Service service ) {
        HistoriqueEtats he = new HistoriqueEtats();
        he.setDateMAJ(datemaj);
        he.setStatut(statut);
        he.setDevis(devis);
        he.setService(service);
        em.persist(he);
    }
    @Override
    public List<HistoriqueEtats> listHistoriqueEtats() {
        List<HistoriqueEtats> co=null;
        String txt="SELECT co FROM HistoriqueEtats AS co ";
        Query req=getEntityManager().createQuery(txt);
        List<HistoriqueEtats> result=req.getResultList();
        return result;
    }

    @Override
    public HistoriqueEtats rechercheHistoriqueEtats(Long id) {
        HistoriqueEtats he = null;        
        String txt = "SELECT he FROM HistoriqueEtats AS he WHERE he.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);  
        List<HistoriqueEtats> res = req.getResultList();
        if (res.size() >= 1)
        {
              he = (HistoriqueEtats) res.get(0);
        }
        return he;
    }

    @Override
    public  HistoriqueEtats rechercheHistoriqueEtatsParDevis(Devis devis) {
        HistoriqueEtats he = null;        
        String txt = "SELECT he FROM HistoriqueEtats AS he WHERE he.devis=:devis ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("devis",devis.getId() );
        List<HistoriqueEtats> res = req.getResultList();
        if (res.size() >= 1)
        {
              he = (HistoriqueEtats) res.get(0);
        }
        return he;
    }
    
    @Override
    public  HistoriqueEtats rechercheHistoriqueEtatsParService(Service service) {
        HistoriqueEtats he = null;        
        String txt = "SELECT he FROM HistoriqueEtats AS he WHERE he.service=:service ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("service",service.getId() );
        List<HistoriqueEtats> res = req.getResultList();
        if (res.size() >= 1)
        {
              he = (HistoriqueEtats) res.get(0);
        }
        return he;
    }
    
     @Override
    public  HistoriqueEtats modificationHistoriqueEtats(HistoriqueEtats he, Date datemaj, Statut statut, Devis devis, Service service) {
               
        String txt = "SELECT hd FROM HistoriqueEtats AS hd WHERE hd.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", he.getId());
        List<HistoriqueEtats> res = req.getResultList();
        if (res.size() >= 1)
        {
            he.setDateMAJ(datemaj);
            he.setStatut(statut);
            he.setDevis(devis);
            he.setService(service);
            em.merge(he);
        }
        return he;
    }
    
    @Override
    public  void modifHistoriqueEtats(HistoriqueEtats entite,Date datemaj, Statut statut, Devis devis, Service service) {       
        String txt = "SELECT entite FROM HistoriqueEtats AS entite WHERE entite.id=:id ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", entite.getId());
        List<HistoriqueEtats> liste = req.getResultList();
        if (!liste.isEmpty()){
            entite =   liste.get(0);
       
            if (datemaj!=null)
        {
            entite.setDateMAJ(datemaj);
        }
            if (statut!=null)
        {
            entite.setStatut(statut);
        }
            if (devis!=null)
        {
            entite.setDevis(devis);
        }
            if (service!=null)
        {
            entite.setService(service);
        }
            
            em.merge(entite);
        }
    }


    public HistoriqueEtatsFacade() {
        super(HistoriqueEtats.class);
    }
    
}

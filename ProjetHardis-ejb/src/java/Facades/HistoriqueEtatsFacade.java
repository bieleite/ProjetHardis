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
    public HistoriqueEtats creerHistoriqueEtats(  Statut statut, Devis devis) {
        HistoriqueEtats he = new HistoriqueEtats();
        he.setDateMAJ(new Date());
        he.setStatut(statut);
        he.setDevis(devis);
        em.persist(he);
        return he;
    }
    @Override
    public List<HistoriqueEtats> listHistoriqueEtats() {
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
    public  List<HistoriqueEtats> rechercheHistoriqueEtatsParDevis(Devis devis) {      
        String txt = "SELECT he FROM HistoriqueEtats AS he WHERE he.devis=:devis ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("devis",devis.getId() );
        List<HistoriqueEtats> res = req.getResultList();      
        return res;
    }
    
    @Override
    public  List<HistoriqueEtats> rechercheHistoriqueEtatsParService(Service service) {     
        String txt = "SELECT he FROM HistoriqueEtats AS he WHERE he.service=:service ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("service",service.getId() );
        List<HistoriqueEtats> res = req.getResultList();
       
        return res;
    }
    
    
    
    @Override
    public  void modifHistoriqueEtats(HistoriqueEtats entite,Date datemaj, Statut statut, Devis devis) {       
      
        if (entite!=null){
            
       
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
            
            em.merge(entite);
        }
    }


    public HistoriqueEtatsFacade() {
        super(HistoriqueEtats.class);
    }
    
}

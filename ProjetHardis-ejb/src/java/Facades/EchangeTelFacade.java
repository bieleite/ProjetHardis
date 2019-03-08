/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;


import Entites.Devis;
import Entites.EchangeTel;
import Entites.UtilisateurHardis;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gabrielleite

import Entites.EchangeTel;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author anastasia.salari

 */
@Stateless
public class EchangeTelFacade extends AbstractFacade<EchangeTel> implements EchangeTelFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    @Override
    public void creerEchangeTel( String text, Devis devis, UtilisateurHardis interlocuteur) {
        EchangeTel et = new EchangeTel();
        et.setTexte(text);
        et.setDevis(devis);
        et.setInterlocuteur(interlocuteur);
        em.persist(et);
    }
    @Override
    public List<EchangeTel> listEchangeTel() {
        List<EchangeTel> co=null;
        String txt="SELECT co FROM EchangeTel AS co ";
        Query req=getEntityManager().createQuery(txt);
        List<EchangeTel> result=req.getResultList();
        return result;
    }

    @Override
    public EchangeTel rechercheEchangeTel(Long id) {
        EchangeTel co = null;        
        String txt = "SELECT co FROM EchangeTel AS co WHERE co.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);  
        List<EchangeTel> res = req.getResultList();
        if (res.size() >= 1)
        {
              co = (EchangeTel) res.get(0);
        }
        return co;
    }

    @Override
    public  EchangeTel rechercheEchangeTelParUtilisateur(UtilisateurHardis utilisateur) {
        EchangeTel di = null;        
        String txt = "SELECT di FROM EchangeTel AS di WHERE di.interlocuteur=:utilisateur ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("utilisateur",utilisateur.getId() );
        List<EchangeTel> res = req.getResultList();
        if (res.size() >= 1)
        {
              di = (EchangeTel) res.get(0);
        }
        return di;
    }
    
     @Override
    public  EchangeTel modificationEchangeTel(EchangeTel et, String text, Devis devis, UtilisateurHardis interlocuteur) {
               
        String txt = "SELECT et FROM EchangeTel AS et WHERE et.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", et.getId());
        List<EchangeTel> res = req.getResultList();
        if (res.size() >= 1)
        {
            et.setTexte(text);
            et.setDevis(devis);
            et.setInterlocuteur(interlocuteur);
            em.merge(et);
        }
        return et;
    }

    @Override
    public  void modifEchangeTel(EchangeTel entite, String text, Devis devis, UtilisateurHardis interlocuteur) {       
        String txt = "SELECT entite FROM EchangeTel AS entite WHERE entite.id=:id ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", entite.getId());
        List<EchangeTel> liste = req.getResultList();
        if (!liste.isEmpty()){
            entite =   liste.get(0);
       
            if (!"".equals(text))
        {
            entite.setTexte(text);
        }
            if (devis!=null)
        {
            entite.setDevis(devis);
        }
            if (interlocuteur!=null)
        {
            entite.setInterlocuteur(interlocuteur);
        }
            em.merge(entite);
        }
    }

    public EchangeTelFacade() {
        super(EchangeTel.class);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;


import Entites.Logs;

import Entites.Action;
import Entites.Interlocuteur;
import Entites.Logs;
import Entites.Utilisateur;
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

 * @author anastasia.salari

 */
@Stateless
public class LogsFacade extends AbstractFacade<Logs> implements LogsFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LogsFacade() {
        super(Logs.class);
    }



    @Override
    public void creerLog(Action action, Date date, String libelle, Utilisateur util) {
        Logs log = new Logs();
        log.setActionEffectue(action);
        log.setDateAction(date);
        log.setLibelle(libelle);
        log.setUtilisateur(util);   
        em.persist(log);
    } 

    @Override
    public List<Logs> rechercheLogsParUtilisateur(Utilisateur u) { 
        Query requete = em.createQuery("SELECT s from Logs as s where s.utilisateur=:u");
        requete.setParameter("u",u);     
        List<Logs> liste =  requete.getResultList();   
        return liste;
    }

    @Override
    public List<Logs> rechercheLogsParAction(Action action) {
        Query requete = em.createQuery("SELECT s from Logs as s where s.actionEffectue=:action");
        requete.setParameter("action",action);     
        List<Logs> liste =  requete.getResultList();   
        return liste;
    }
    
    
    
    
    
    

    
}

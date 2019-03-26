/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;


import Entites.Logs;

import Entites.*;
import Entites.Adresse;
import Entites.Agence;
import Entites.Atelier;
import Entites.Client;
import Entites.Communication;
import Entites.Devis;

import Entites.Disponibilite;
import Entites.Document;
import Entites.EchangeTel;
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
    
    @Override
    public void creerLogCreate( Utilisateur util, Object o) {
        Logs log = new Logs();
        log.setActionEffectue(Action.Create);
        log.setDateAction(new Date());
        String clas = null;
        Long id = null;
        if (o instanceof Adresse ){
            id = ((Adresse) o).getId();
            clas = o.getClass().toString();
        }
        else if (o instanceof Agence ){
             id = ((Agence) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Atelier ){
             id = ((Atelier) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Client ){
             id = ((Client) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Communication ){
             id = ((Communication) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Devis ){
             id = ((Devis) o).getId();
             clas = o.getClass().toString();
        }
       
        else if (o instanceof Document ){
             id = ((Document) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof EchangeTel ){
             id = ((EchangeTel) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Entreprise ){
             id = ((Entreprise) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Facture ){
             id = ((Facture) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof HistoriqueDevis ){
             id = ((HistoriqueDevis) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof HistoriqueEtats ){
             id = ((HistoriqueEtats) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof HistoriqueTraitement ){
             id = ((HistoriqueTraitement) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Interlocuteur ){
             id = ((Interlocuteur) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Livrable ){
             id = ((Livrable) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Offre ){
             id = ((Offre) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Offre_Profil_Util_CV ){
             id = ((Offre_Profil_Util_CV) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof ProfilMetier ){
             id = ((ProfilMetier) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Service ){
             id = ((Service) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof ServiceStandard ){
             id = ((ServiceStandard) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Utilisateur ){
             id = ((Utilisateur) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof UtilisateurHardis ){
             id = ((UtilisateurHardis) o).getId();
             clas = o.getClass().toString();
        }
        String libelle = "Creer en :"+ clas +"id: " + id +" par lutilisateur: "+ util.getId();
        log.setLibelle(libelle);
        log.setUtilisateur(util);   
        em.persist(log);
    } 
    
    @Override
    public void creerLogDelete(Utilisateur util, Object o) {
        Logs log = new Logs();
        log.setActionEffectue(Action.Delete);
        log.setDateAction(new Date());
        String clas = null;
        Long id = null;
        if (o instanceof Adresse ){
            id = ((Adresse) o).getId();
            clas = o.getClass().toString();
        }
        else if (o instanceof Agence ){
             id = ((Agence) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Atelier ){
             id = ((Atelier) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Client ){
             id = ((Client) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Communication ){
             id = ((Communication) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Devis ){
             id = ((Devis) o).getId();
             clas = o.getClass().toString();
        }
   
        else if (o instanceof Document ){
             id = ((Document) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof EchangeTel ){
             id = ((EchangeTel) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Entreprise ){
             id = ((Entreprise) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Facture ){
             id = ((Facture) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof HistoriqueDevis ){
             id = ((HistoriqueDevis) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof HistoriqueEtats ){
             id = ((HistoriqueEtats) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof HistoriqueTraitement ){
             id = ((HistoriqueTraitement) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Interlocuteur ){
             id = ((Interlocuteur) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Livrable ){
             id = ((Livrable) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Offre ){
             id = ((Offre) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Offre_Profil_Util_CV ){
             id = ((Offre_Profil_Util_CV) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof ProfilMetier ){
             id = ((ProfilMetier) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Service ){
             id = ((Service) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof ServiceStandard ){
             id = ((ServiceStandard) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Utilisateur ){
             id = ((Utilisateur) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof UtilisateurHardis ){
             id = ((UtilisateurHardis) o).getId();
             clas = o.getClass().toString();
        }
        String libelle = "Suppression du :"+ clas +"id: " + id +" par lutilisateur: "+ util.getId();
        log.setLibelle(libelle);
        log.setUtilisateur(util);   
        em.persist(log);
    } 
    
    @Override
    public void creerLogUpdate(Utilisateur util, Object o) {
        Logs log = new Logs();
        log.setActionEffectue(Action.Update);
        log.setDateAction(new Date());
        String clas = null;
        Long id = null;
        if (o instanceof Adresse ){
            id = ((Adresse) o).getId();
            clas = o.getClass().toString();
        }
        else if (o instanceof Agence ){
             id = ((Agence) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Atelier ){
             id = ((Atelier) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Client ){
             id = ((Client) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Communication ){
             id = ((Communication) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Devis ){
             id = ((Devis) o).getId();
             clas = o.getClass().toString();
        }
        
        else if (o instanceof Document ){
             id = ((Document) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof EchangeTel ){
             id = ((EchangeTel) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Entreprise ){
             id = ((Entreprise) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Facture ){
             id = ((Facture) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof HistoriqueDevis ){
             id = ((HistoriqueDevis) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof HistoriqueEtats ){
             id = ((HistoriqueEtats) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof HistoriqueTraitement ){
             id = ((HistoriqueTraitement) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Interlocuteur ){
             id = ((Interlocuteur) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Livrable ){
             id = ((Livrable) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Offre ){
             id = ((Offre) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Offre_Profil_Util_CV ){
             id = ((Offre_Profil_Util_CV) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof ProfilMetier ){
             id = ((ProfilMetier) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Service ){
             id = ((Service) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof ServiceStandard ){
             id = ((ServiceStandard) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Utilisateur ){
             id = ((Utilisateur) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof UtilisateurHardis ){
             id = ((UtilisateurHardis) o).getId();
             clas = o.getClass().toString();
        }
        String libelle = "Modification du :"+ clas +"id: " + id +" par lutilisateur: "+ util.getId();
        log.setLibelle(libelle);
        log.setUtilisateur(util);   
        em.persist(log);
    } 
    
    @Override
    public void creerLogResearch(Utilisateur util, Object o) {
        Logs log = new Logs();
        log.setActionEffectue(Action.Research);
        log.setDateAction(new Date());
        String clas = null;
        Long id = null;
        if (o instanceof Adresse ){
            id = ((Adresse) o).getId();
            clas = o.getClass().toString();
        }
        else if (o instanceof Agence ){
             id = ((Agence) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Atelier ){
             id = ((Atelier) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Client ){
             id = ((Client) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Communication ){
             id = ((Communication) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Devis ){
             id = ((Devis) o).getId();
             clas = o.getClass().toString();
        }
       
        else if (o instanceof Document ){
             id = ((Document) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof EchangeTel ){
             id = ((EchangeTel) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Entreprise ){
             id = ((Entreprise) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Facture ){
             id = ((Facture) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof HistoriqueDevis ){
             id = ((HistoriqueDevis) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof HistoriqueEtats ){
             id = ((HistoriqueEtats) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof HistoriqueTraitement ){
             id = ((HistoriqueTraitement) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Interlocuteur ){
             id = ((Interlocuteur) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Livrable ){
             id = ((Livrable) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Offre ){
             id = ((Offre) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Offre_Profil_Util_CV ){
             id = ((Offre_Profil_Util_CV) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof ProfilMetier ){
             id = ((ProfilMetier) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Service ){
             id = ((Service) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof ServiceStandard ){
             id = ((ServiceStandard) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof Utilisateur ){
             id = ((Utilisateur) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof UtilisateurHardis ){
             id = ((UtilisateurHardis) o).getId();
             clas = o.getClass().toString();
        }
        else if (o instanceof String ){
             id = null;
             clas = o.getClass().toString();
        }
        String libelle = "Research du :"+ clas +"id: " + id +" par lutilisateur: "+ util.getId();
        log.setLibelle(libelle);
        log.setUtilisateur(util);   
        em.persist(log);
    } 
    

    
}

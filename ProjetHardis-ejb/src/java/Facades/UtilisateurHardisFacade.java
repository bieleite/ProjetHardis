/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;


import Entites.Agence;
import Entites.Client;
import Entites.UtilisateurHardis;

import Entites.EchangeTel;
import Entites.Entreprise;
import Entites.Helpers;
import Entites.HistoriqueDevis;
import Entites.HistoriqueTraitement;
import Entites.ProfilTechnique;
import Entites.StatutUtilisateur;
import Entites.Utilisateur;
import Entites.UtilisateurHardis;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class UtilisateurHardisFacade extends AbstractFacade<UtilisateurHardis> implements UtilisateurHardisFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurHardisFacade() {
        super(UtilisateurHardis.class);
    }

    
    


    @Override
    public UtilisateurHardis creerUtilisateurH(String nom, String prenom, String login, String mdp, ProfilTechnique profil, Agence agence) {
       UtilisateurHardis u = new UtilisateurHardis();  
       u.setNom(nom);
       u.setPrenom(prenom);
       u.setLogin(login);
       try {
            u.setMdp(Helpers.sha1(mdp));
        } 
       catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ClientFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
       u.setDateRGPD(null);
       u.setRGPD(-1);
       u.setStatut(StatutUtilisateur.Actif);
       u.setLienCVDefaut("");
       u.setProfilTechique(profil);
       u.setEchangeTels(new ArrayList<EchangeTel>());
       u.setHistoriqueDeviss(new ArrayList<HistoriqueDevis>());
       u.setHistoriqueTraitements(new ArrayList<>());
       u.setOffre_Profil_Utils(new ArrayList<>());
       u.setQuestionSecrete("");
        try {
            u.setMailHache(Helpers.sha1(login));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ClientFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
       u.setReponseSecrete("");
        u.setVisible(true);
        u.setAgence(agence);
       em.persist(u);
       return u;
    }
    
    
    @Override
    public UtilisateurHardis rechercheUtilisateurParId(Long id) {
        UtilisateurHardis cl = null;        
        String txt = "SELECT cl FROM UtilisateurHardis AS cl WHERE cl.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);  
        List<UtilisateurHardis> res = req.getResultList();
        if (res.size() >= 1)
        {
              cl  = (UtilisateurHardis) res.get(0);
        }
        return cl;
    }

    
    @Override
    public  UtilisateurHardis rechercheUtilisateurParLogin(String login) {
        UtilisateurHardis cl = null;        
        String txt = "SELECT cl FROM UtilisateurHardis AS cl WHERE cl.login=:login ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("login", login);
        List<UtilisateurHardis> res = req.getResultList();
        if (res.size() >= 1)
        {
              cl = (UtilisateurHardis) res.get(0);
        }
        return cl;
    }

    @Override
    public UtilisateurHardis modfiUtilisateurMDP(UtilisateurHardis cl, String MDP) {
                
     if (cl!=null)
        {
            try {
            cl.setMdp(Helpers.sha1(MDP));
        } 
       catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ClientFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
            em.merge(cl);
        }
       return cl; 
    }
    
    @Override
    public  void modfiUtilisateurQSRS(UtilisateurHardis cl, String QS, String RS) {
              
        if (cl!=null)
        {
            cl.setQuestionSecrete(QS);
            try {
            cl.setReponseSecrete(Helpers.sha1(RS));
        } 
       catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ClientFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
            em.merge(cl);
        }
        
    }
    
    @Override
    public  void SuppressionUtilisateur(UtilisateurHardis utilisateur) {
              
        String txt = "SELECT cl FROM UtilisateurHardis AS cl WHERE cl.id=:id ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", utilisateur.getId());
        List<UtilisateurHardis> res = req.getResultList();
        if (res.size() >= 1)
        {
            res.get(0).setVisible(false);
            em.merge(res.get(0));
        }
        
    }
   
    @Override
    public  void modifUtilisateurHardis(UtilisateurHardis entite, String nom, String prenom, Date dateRGPD, int rgpd, ProfilTechnique profil, StatutUtilisateur statut, String lienCV,Agence agence) {       
        if (entite!=null)
        { 
         
       
            if (!"".equals(nom))
        {
            entite.setNom(nom);
        }
            if (!"".equals(prenom))
        {
            entite.setPrenom(prenom);
        }
       
            if (rgpd >= 0)
        {
            entite.setRGPD(rgpd);
        }
            if (dateRGPD!=null)
        {
            entite.setDateRGPD(dateRGPD);
        }
            if (profil!=null)
        {
            entite.setProfilTechique(profil);
        }
             if (statut!=null)
        {
            entite.setStatut(statut);
        }
             if (agence!=null)
        {
            entite.setAgence(agence);
        }
               if (!"".equals(lienCV))
        {
            entite.setLienCVDefaut(lienCV);
        }
            
            em.merge(entite);
        }
    }

    @Override
    public UtilisateurHardis authentificationHardis(String log, String mdp) {
        
        UtilisateurHardis c = null;
         try {
            String m = Helpers.sha1(mdp);
             Query requete = em.createQuery("SELECT a from UtilisateurHardis as a where a.login=:lo and a.mdp=:m and a.statut=:actif");
        requete.setParameter("actif", StatutUtilisateur.Actif);
             requete.setParameter("lo", log);
        requete.setParameter("m", m);       
        List<UtilisateurHardis> liste =  requete.getResultList();
        if (!liste.isEmpty()) {
           c =  (UtilisateurHardis)liste.get(0);
           c.setConnecte(true);
           em.merge(c);
           
        }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UtilisateurHardisFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return c;
        
    }

    @Override
    public List<UtilisateurHardis> rechercheUtilisateurHParAgence(Agence agence) {
        String txt = "SELECT cl FROM UtilisateurHardis AS cl WHERE cl.agence=:agence";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("agence", agence);
        List<UtilisateurHardis> res = req.getResultList();

        return res;
    }

    @Override
    public List<UtilisateurHardis> listUHardis() {
         String txt="SELECT co FROM UtilisateurHardis AS co ";
        Query req=getEntityManager().createQuery(txt);
        List<UtilisateurHardis> result=req.getResultList();
        return result;
    }

    @Override
    public  UtilisateurHardis rechercheUtilisateurParQS(String QS, String RS) {
        UtilisateurHardis cl = null;        
        String txt = "SELECT cl FROM UtilisateurHardis AS cl WHERE cl.questionSecrete=:qs and cl.reponseSecrete=:rs ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("qs", QS);
        req = req.setParameter("rs", RS);
        List<UtilisateurHardis> res = req.getResultList();
        if (res.size() >= 1)
        {
              cl = (UtilisateurHardis) res.get(0);
        }
        return cl;
    }

    @Override
    public UtilisateurHardis rechercheParEmailHache(String email) {
        UtilisateurHardis cl = null;        
        String txt = "SELECT cl FROM UtilisateurHardis AS cl WHERE cl.mailHache=:email";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("email", email);
        List<UtilisateurHardis> res = req.getResultList();
        if (res.size() >= 1)
        {
              cl = (UtilisateurHardis) res.get(0);
        }
        return cl;
    }
    
    @Override
    public  void changVisibiliteUtilisateur(UtilisateurHardis entite) {       
        entite.setVisible(false);
        entite.setStatut(StatutUtilisateur.Inactif);
    }
    
    @Override
    public  void signout(UtilisateurHardis entite) {       
        entite.setConnecte(false);
        em.merge(entite);
    }
        

    
}

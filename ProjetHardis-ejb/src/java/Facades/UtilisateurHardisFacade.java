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
import Entites.HistoriqueDevis;
import Entites.HistoriqueTraitement;
import Entites.ProfilTechnique;
import Entites.StatutUtilisateur;
import Entites.Utilisateur;
import Entites.UtilisateurHardis;
import java.util.ArrayList;
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
    public void creerUtilisateurH(String nom, String prenom, String login, String mdp, String questSecrete, String repSecrete, Date dateRGPD, int rgpd, ProfilTechnique profil, StatutUtilisateur statut, String lienCV, Agence agence) {
       UtilisateurHardis u = new UtilisateurHardis();  
       u.setNom(nom);
       u.setPrenom(prenom);
       u.setLogin(login);
       u.setMdp(mdp);
       u.setDateRGPD(null);
       u.setRGPD(-1);
       u.setStatut(statut);
       u.setLienCVDefaut(lienCV);
       u.setProfilTechique(profil);
       u.setEchangeTels(new ArrayList<EchangeTel>());
       u.setHistoriqueDeviss(new ArrayList<HistoriqueDevis>());
       u.setHistoriqueTraitement(new HistoriqueTraitement());
       u.setOffre_Profil_Utils(new ArrayList());
       u.setQuestionSecrete(questSecrete);
       u.setReponseSecrete(repSecrete);
         u.setVisible(true);
         u.setAgence(agence);
       em.persist(u);
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
    public void modfiUtilisateurMDP(UtilisateurHardis cl, String MDP) {
                
     if (cl!=null)
        {
            cl.setMdp(MDP);
            em.merge(cl);
        }
        
    }
    
    @Override
    public  void modfiUtilisateurQSRS(UtilisateurHardis cl, String QS, String RS) {
              
         if (cl!=null)
        {
            cl.setMdp(RS);
            em.merge(cl);
        }
        
    }
    
    @Override
    public  void SuppressionUtilisateur(Long id) {
              
        String txt = "SELECT cl FROM UtilisateurHardis AS cl WHERE cl.id=:id ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);
        List<UtilisateurHardis> res = req.getResultList();
        if (res.size() >= 1)
        {
            res.get(0).setVisible(false);
            em.merge(res.get(0));
        }
        
    }
   
    @Override
    public  void modifClient(UtilisateurHardis entite, String nom, String prenom, Date dateRGPD, int rgpd, ProfilTechnique profil, StatutUtilisateur statut, String lienCV) {       
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
               if (!"".equals(lienCV))
        {
            entite.setLienCVDefaut(lienCV);
        }
            
            em.merge(entite);
        }
    }

    @Override
    public UtilisateurHardis authentificationHardis(String log, String mdp) {
        Query requete = em.createQuery("SELECT a from UtilisateurHardis as a where a.login=:lo and a.mdp=:m");
        requete.setParameter("lo", log);
        requete.setParameter("m", mdp);       
        List<UtilisateurHardis> liste =  requete.getResultList();
        if (!liste.isEmpty())
            return (UtilisateurHardis)liste.get(0);
        else return null;
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
    
    

    
}

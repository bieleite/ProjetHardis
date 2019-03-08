/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Client;

import Entites.Entreprise;
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
public class ClientFacade extends AbstractFacade<Client> implements ClientFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }
    

    @Override
    public void creerClient(String Nom,String Prenom, String Login, String MDP, String QuestionSecrete, String ReponseSecrete, int RGPD, Date dateRDGP, Entreprise entreprise) {
        Client cl = new Client();
        cl.setNom(Nom);
        cl.setPrenom(Prenom);
        cl.setLogin(Login);
        cl.setMdp(MDP);
        cl.setQuestionSecrete(QuestionSecrete);
        cl.setReponseSecrete(ReponseSecrete);
        cl.setRGPD(RGPD);
        cl.setDateRGPD(dateRDGP);
        cl.setVisible(true);
        em.persist(cl);
    }
    
    @Override
    public List<Client> listClient() {
        List<Client> cl=null;
        String txt="SELECT cl FROM Client AS cl ";
        Query req=getEntityManager().createQuery(txt);
        List<Client> result=req.getResultList();
        return result;
    }

    @Override
    public Client rechercheClient(Long id) {
        Client cl = null;        
        String txt = "SELECT cl FROM Client AS cl WHERE cl.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);  
        List<Client> res = req.getResultList();
        if (res.size() >= 1)
        {
              cl = (Client) res.get(0);
        }
        return cl;
    }

    @Override
    public  Client rechercheClientParNom(String nom) {
        Client cl = null;        
        String txt = "SELECT cl FROM Client AS cl WHERE cl.nom=:nom ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("nom", nom);
        List<Client> res = req.getResultList();
        if (res.size() >= 1)
        {
              cl = (Client) res.get(0);
        }
        return cl;
    }
    
    @Override
    public  Client rechercheClientParLogin(String login) {
        Client cl = null;        
        String txt = "SELECT cl FROM Client AS cl WHERE cl.login=:login ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("login", login);
        List<Client> res = req.getResultList();
        if (res.size() >= 1)
        {
              cl = (Client) res.get(0);
        }
        return cl;
    }
    
     @Override
    public  void modfiClientNomPrenom(Client cl, String Nom, String Prenom) {
                
        String txt = "SELECT cl FROM Client AS cl WHERE cl.id=:id ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", cl.getId());
        List<Client> res = req.getResultList();
        if (res.size() >= 1)
        {
            cl.setNom(Nom);
            cl.setPrenom(Prenom);
            em.merge(cl);
        }
        
    }
    
    @Override
    public  void modfiClientMDP(Client cl, String MDP) {
                
        String txt = "SELECT cl FROM Client AS cl WHERE cl.id=:id ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", cl.getId());
        List<Client> res = req.getResultList();
        if (res.size() >= 1)
        {
            cl.setMdp(MDP);
            em.merge(cl);
        }
        
    }
    
    @Override
    public  void modfiClientQSRS(Client cl, String QS, String RS) {
              
        String txt = "SELECT cl FROM Client AS cl WHERE cl.id=:id ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", cl.getId());
        List<Client> res = req.getResultList();
        if (res.size() >= 1)
        {
            cl.setMdp(RS);
            em.merge(cl);
        }
        
    }
    
    @Override
    public  void SuppressionClient(Client cl) {
              
        String txt = "SELECT cl FROM Client AS cl WHERE cl.id=:id ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", cl.getId());
        List<Client> res = req.getResultList();
        if (res.size() >= 1)
        {
            cl.setVisible(false);
            em.merge(cl);
        }
        
    }
   
    @Override
    public  void modifClient(Client entite, String Nom,String Prenom, String Login, String MDP, String QuestionSecrete, String ReponseSecrete, int RGPD, Date dateRDGP, Entreprise entreprise) {       
        String txt = "SELECT entite FROM Client AS entite WHERE entite.id=:id ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", entite.getId());
        List<Client> liste = req.getResultList();
        if (!liste.isEmpty()){
            entite =   liste.get(0);
       
            if (!"".equals(Nom))
        {
            entite.setNom(Nom);
        }
            if (!"".equals(Prenom))
        {
            entite.setPrenom(Prenom);
        }
            if (!"".equals(Login))
        {
            entite.setLogin(Login);
        }
            if (!"".equals(MDP))
        {
            entite.setMdp(MDP);
        }
            if (!"".equals(QuestionSecrete))
        {
            entite.setQuestionSecrete(QuestionSecrete);
        }
            if (!"".equals(ReponseSecrete))
        {
            entite.setReponseSecrete(ReponseSecrete);
        }
            if (RGPD==0 && RGPD==1)
        {
            entite.setRGPD(RGPD);
        }
            if (dateRDGP!=null)
        {
            entite.setDateRGPD(dateRDGP);
        }
            if (entreprise!=null)
        {
            entite.setEntreprise(entreprise);
        }
            
            em.merge(entite);
        }
    }

}

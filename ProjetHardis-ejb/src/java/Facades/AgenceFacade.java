/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Agence;

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
public class AgenceFacade extends AbstractFacade<Agence> implements AgenceFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    @Override
    public Agence creerAgence(String NomAgence, String add) {
        Agence ag = new Agence();
        ag.setNomAgence(NomAgence);
        ag.setAdresse(add);
        em.persist(ag);
        
        return ag;
    }
    @Override
    public List<Agence> listAgence() {
        String txt="SELECT ad FROM Agence AS ad ";
        Query req=getEntityManager().createQuery(txt);
        List<Agence> result=req.getResultList();
        return result;
    }

    @Override
    public Agence rechercheAgence(long id) {
        Agence ad = null;        
        String txt = "SELECT ad FROM Agence AS ad WHERE ad.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);  
        List<Agence> res = req.getResultList();
        if (res.size() >= 1)
        {
              ad = (Agence) res.get(0);
        }
        return ad;
    }

    @Override
    public  Agence rechercheAgenceParNom(String NomAgence) {
        Agence ag = null;        
        String txt = "SELECT ag FROM Agence AS ag WHERE ag.nomAgence=:nomagence ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("nomagence", NomAgence);
        List<Agence> res = req.getResultList();
        if (res.size() >= 1)
        {
              ag = (Agence) res.get(0);
        }
        return ag;
    }
    
      
    @Override
    public  void modifAgence(Agence entite, String NomAgence) {       
      
        if (entite!=null){
       
            if (!"".equals(NomAgence))
        {
            entite.setNomAgence(NomAgence);
        }
            em.merge(entite);
        }
    }
    
    @Override
    public boolean supprimerAgence(Agence entite) {
        Query requete = em.createQuery("SELECT s from Agence as s where s.id=:id");
        requete.setParameter("id",entite.getId());   
        boolean b = true;
        List<Agence> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            entite =   liste.get(0); 
            try{
            em.remove(entite);
            }
            catch (javax.ejb.EJBException ex)
            {
                b = false;
            }
        } return b;
    }
    
    
    public AgenceFacade() {
        super(Agence.class);
    }
    
}

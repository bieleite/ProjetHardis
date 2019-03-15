/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Atelier;

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
public class AtelierFacade extends AbstractFacade<Atelier> implements AtelierFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    @Override
    public Atelier creerAtelier(String NomAtelier) {
        Atelier ag = new Atelier();
        ag.setNomAtelier(NomAtelier);
        em.persist(ag);
        return ag;
    }
    @Override
    public List<Atelier> listAtelier() {
        String txt="SELECT ad FROM Atelier AS ad ";
        Query req=getEntityManager().createQuery(txt);
        List<Atelier> result=req.getResultList();
        return result;
    }

    @Override
    public Atelier rechercheAtelier(long id) {
        Atelier ad = null;        
        String txt = "SELECT ad FROM Atelier AS ad WHERE ad.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);  
        List<Atelier> res = req.getResultList();
        if (res.size() >= 1)
        {
              ad = (Atelier) res.get(0);
        }
        return ad;
    }

    @Override
    public  Atelier rechercheAtelierParNom(String nom) {
        Atelier ag = null;        
        String txt = "SELECT ag FROM Atelier AS ag WHERE ag.nomAtelier=:nomatelier ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("nomatelier",  nom);
        List<Atelier> res = req.getResultList();
        if (res.size() >= 1)
        {
              ag = (Atelier) res.get(0);
        }
        return ag;
    }
    
    
    @Override
    public  void modifAtelier(Atelier entite, String NomAtelier) {       
       
        if (entite!=null){

            if (!"".equals(NomAtelier))
        {
            entite.setNomAtelier(NomAtelier);
        }
            em.merge(entite);
        }
    }
    
     @Override
    public void supprimerAtelier(Atelier entite) {
        Query requete = em.createQuery("SELECT s from Atelier as s where s.id=:id");
        requete.setParameter("id",entite.getId());     
        List<Atelier> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            entite =   liste.get(0); 
            
            em.remove(entite);
        }
    }

    public AtelierFacade() {
        super(Atelier.class);
    }
    
}

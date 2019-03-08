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
    public void creerAtelier(String NomAtelier) {
        Atelier ag = new Atelier();
        ag.setNomAtelier(NomAtelier);
        em.persist(ag);
    }
    @Override
    public List<Atelier> listAtelier() {
        List<Atelier> ad=null;
        String txt="SELECT ad FROM Atelier AS ad ";
        Query req=getEntityManager().createQuery(txt);
        List<Atelier> result=req.getResultList();
        return result;
    }

    @Override
    public Atelier rechercheAtelier(Long id) {
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
    public  Atelier rechercheAtelierParNom(String NomAtelier) {
        Atelier ag = null;        
        String txt = "SELECT ag FROM Atelier AS ag WHERE ag.NomAtelier=:nomatelier ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("nomatelier", NomAtelier);
        List<Atelier> res = req.getResultList();
        if (res.size() >= 1)
        {
              ag = (Atelier) res.get(0);
        }
        return ag;
    }
    
    
    @Override
    public  void modifAtelier(Atelier entite, String NomAtelier) {       
        String txt = "SELECT ad FROM Atelier AS ad WHERE ad.id=:id ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", entite.getId());
        List<Atelier> liste = req.getResultList();
        if (!liste.isEmpty()){
            entite =   liste.get(0);
       
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

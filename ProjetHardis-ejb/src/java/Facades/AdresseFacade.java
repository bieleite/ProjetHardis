/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Adresse;

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
public class AdresseFacade extends AbstractFacade<Adresse> implements AdresseFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdresseFacade() {
        super(Adresse.class);
    }
    

    @Override
    public void creerAdresse(int NumRue, String NomRue, String Ville, String CodePostal) {
        Adresse ad = new Adresse();
        ad.setNumeroRue(NumRue);
        ad.setNomRue(NomRue);
        ad.setVille(Ville);
        ad.setCodePostal(CodePostal);
        em.persist(ad);
    }
    
    @Override
    public List<Adresse> listAdresse() {
        String txt="SELECT ad FROM Adresse AS ad ";
        Query req=getEntityManager().createQuery(txt);
        List<Adresse> result=req.getResultList();
        return result;
    }

    @Override
    public Adresse rechercheAdresse(Long id) {
        Adresse ad = null;        
        String txt = "SELECT ad FROM Adresse AS ad WHERE ad.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);  
        List<Adresse> res = req.getResultList();
        if (res.size() >= 1)
        {
              ad = (Adresse) res.get(0);
        }
        return ad;
    }

    @Override
    public  Adresse rechercheAdresseParCP(String cp) {
        Adresse ad = null;        
        String txt = "SELECT ad FROM Adresse AS ad WHERE ad.codePostal=:CP ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("CP", cp);
        List<Adresse> res = req.getResultList();
        if (res.size() >= 1)
        {
              ad = (Adresse) res.get(0);
        }
        return ad;
    }
    
   
    
    @Override
    public  void modifAdresse(Adresse ad, int NumRue, String NomRue, String Ville, String CodePostal) {       
      
        if (ad!=null){
       
            if (!"".equals(NomRue))
        {
            ad.setNomRue(NomRue);
        }
        
            if (!"".equals(Ville))
        {
            ad.setVille(Ville);
        }
           
            if (!"".equals(CodePostal))
        {
            ad.setCodePostal(CodePostal);
        }
            
            if (NumRue!=0)
        {
            ad.setNomRue(NomRue);
        }
            em.merge(ad);
        }
    }
    
 
    
    


}

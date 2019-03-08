/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Livrable;
<<<<<<< HEAD
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gabrielleite
=======
import Entites.Offre;
import Entites.Service;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author anastasia.salari
>>>>>>> origin/v3
 */
@Stateless
public class LivrableFacade extends AbstractFacade<Livrable> implements LivrableFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LivrableFacade() {
        super(Livrable.class);
    }
<<<<<<< HEAD
=======

    @Override
    public void creerLivrable(String nom, Service service) {
        Livrable liv = new Livrable();
        liv.setNomLivrable(nom);
        liv.setService(service);
        em.persist(liv);
    }

    @Override
    public void modifierLivrable(Livrable liv, String nom, Service service) {
        Livrable livrable = null;
        Query requete = em.createQuery("SELECT s from Livrable as s where s.id=:id");
        requete.setParameter("id",liv.getId());     
        List<Livrable> listeO =  requete.getResultList();
        if (!listeO.isEmpty()){
            livrable =   listeO.get(0);
            if (nom.equals(""))
                livrable.setNomLivrable(nom);
            if (service!=null)
                  livrable.setService(service);
            em.merge(livrable);
    } 
  }

    @Override
    public void supprimerLivrable(long id) {
        Livrable pm = null;
        Query requete = em.createQuery("SELECT p from Livrable as p where p.id=:id");
        requete.setParameter("id",id);     
        List<Livrable> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            pm  =  liste.get(0); 
            em.remove(pm);
        }
    }
    
    
    
    
    
    
    
    
>>>>>>> origin/v3
    
}

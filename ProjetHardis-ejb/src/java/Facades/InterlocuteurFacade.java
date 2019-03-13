/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;


import Entites.Entreprise;
import Entites.Interlocuteur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gabrielleite

import Entites.Entreprise;
import Entites.Interlocuteur;
import Entites.Livrable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author anastasia.salari

 */
@Stateless
public class InterlocuteurFacade extends AbstractFacade<Interlocuteur> implements InterlocuteurFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InterlocuteurFacade() {
        super(Interlocuteur.class);
    }

  

    @Override
    public Interlocuteur creerInterlocuteur(String nom, String prenom, String fonction, String tel, Entreprise entreprise) {
        Interlocuteur inter = new Interlocuteur();
        inter.setEntreprise(entreprise);
        inter.setFonctionInterlocuteur(fonction);
        inter.setNomInterlocuteur(fonction);
        inter.setPrenomInterlocuteur(prenom);
        inter.setTelInterlocuteur(tel);
        em.persist(inter);
        return inter;
    }

    @Override
    public void modifierInterlocuteur(Interlocuteur inter, String nom, String prenom, String fonction, String tel, Entreprise entreprise ) {
        Interlocuteur inte = null;
        Query requete = em.createQuery("SELECT s from Interlocuteur as s where s.id=:id");
        requete.setParameter("id",inter.getId());     
        List<Interlocuteur> listeI =  requete.getResultList();
        if (!listeI.isEmpty()){
            inte =   listeI.get(0);
            if (nom.equals(""))
                inte.setNomInterlocuteur(nom);
            if (entreprise!=null)
                  inte.setEntreprise(entreprise);
            if (prenom.equals(""))
                inte.setPrenomInterlocuteur(prenom);
            if (tel.equals(""))
                inte.setTelInterlocuteur(tel);
            if (fonction.equals(""))
                inte.setFonctionInterlocuteur(fonction);
            em.merge(inte);
    } 
    }

    @Override
    public void supprimerInterlocuteur(long id) {
         Interlocuteur pm = null;
        Query requete = em.createQuery("SELECT p from Interlocuteur as p where p.id=:id");
        requete.setParameter("id",id);     
        List<Interlocuteur> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            pm  =  liste.get(0); 
            em.remove(pm);
        }
    }

    @Override
    public Interlocuteur rechercheInterlocuteurParId(long id) {
        Interlocuteur s = null;
        Query requete = em.createQuery("SELECT s from Interlocuteur as s where s.id=:id");
        requete.setParameter("id",id);     
        List<Interlocuteur> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            s =  (Interlocuteur) liste.get(0); 
        }
        return s;
    }
    
    
    
    
    
    
    
    
    

    
}

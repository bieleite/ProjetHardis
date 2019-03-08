/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Disponibilite;

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

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author anastasia.salari

 */
@Stateless
public class DisponibiliteFacade extends AbstractFacade<Disponibilite> implements DisponibiliteFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    @Override
    public void creerDisponibilite( Date dateDebut, Date dateFin, String libelle, UtilisateurHardis utilisateur) {
        Disponibilite de = new Disponibilite();
        de.setDateDebut(dateDebut);
        de.setDateFin(dateFin);
        de.setLibelleActivite(libelle);
        de.setUtilisateurHardis(utilisateur);
        em.persist(de);
    }
    @Override
    public List<Disponibilite> listDisponibilite() {
        String txt="SELECT co FROM Disponibilite AS co ";
        Query req=getEntityManager().createQuery(txt);
        List<Disponibilite> result=req.getResultList();
        return result;
    }

    @Override
    public Disponibilite rechercheDisponibilite(long id) {
        Disponibilite co = null;        
        String txt = "SELECT co FROM Disponibilite AS ad WHERE co.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);  
        List<Disponibilite> res = req.getResultList();
        if (res.size() >= 1)
        {
              co = (Disponibilite) res.get(0);
        }
        return co;
    }

    @Override
    public  Disponibilite rechercheDisponibiliteParUtilisateur(UtilisateurHardis utilisateur) {
        Disponibilite di = null;        
        String txt = "SELECT di FROM Disponibilite AS di WHERE di.UtilisateurHardis=:utilisateur ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("utilisateur",utilisateur.getId() );
        List<Disponibilite> res = req.getResultList();
        if (res.size() >= 1)
        {
              di = (Disponibilite) res.get(0);
        }
        return di;
    }
    
    
    @Override
    public  void modifDisponibilite(Disponibilite entite, Date dateDebut, Date dateFin, String libelle, UtilisateurHardis utilisateur) {       
      if (entite!=null){
          
       
            if (dateDebut!=null)
        {
            entite.setDateDebut(dateDebut);
        }
            if (dateFin!=null)
        {
            entite.setDateFin(dateFin);
        }
            if (!"".equals(libelle))
        {
            entite.setLibelleActivite(libelle);
        }
            if (utilisateur!=null)
        {
            entite.setUtilisateurHardis(utilisateur);
        }
            
            em.merge(entite);
        }
    }

    public DisponibiliteFacade() {
        super(Disponibilite.class);
    }
    
}

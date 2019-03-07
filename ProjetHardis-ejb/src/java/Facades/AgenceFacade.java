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
    public void creerAgence(String NomAgence) {
        Agence ag = new Agence();
        ag.setNomAgence(NomAgence);
        em.persist(ag);
    }
    @Override
    public List<Agence> listAgence() {
        List<Agence> ad=null;
        String txt="SELECT ad FROM Agence AS ad ";
        Query req=getEntityManager().createQuery(txt);
        List<Agence> result=req.getResultList();
        return result;
    }

    @Override
    public Agence rechercheAgence(Long id) {
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
        String txt = "SELECT ag FROM Agence AS ag WHERE ag.NomAgence=:nomagence ";
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
    public  Agence modfiAgenceNom(Agence adresse, String NomAgence) {
        Agence ag = null;        
        String txt = "SELECT ag FROM Agence AS ag WHERE ag.NomAgence=:nomagence ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("nomagence", NomAgence);
        List<Agence> res = req.getResultList();
        if (res.size() >= 1)
        {
              ag = (Agence) res.get(0);
        }
        return ag;
    }
    
    
    public AgenceFacade() {
        super(Agence.class);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Communication;
import Entites.Devis;
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
 */
@Stateless
public class CommunicationFacade extends AbstractFacade<Communication> implements CommunicationFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

     @Override
    public void creerCommunication( Date date_comu, String message, Devis devis, UtilisateurHardis utilisateur ) {
        Communication co = new Communication();
        co.setDateHeure(date_comu);
        co.setMessage(message);
        co.setDevis(devis);
        co.setUtilisateurHardis(utilisateur);
        em.persist(co);
    }
    @Override
    public List<Communication> listCommunication() {
        List<Communication> co=null;
        String txt="SELECT co FROM Communication AS co ";
        Query req=getEntityManager().createQuery(txt);
        List<Communication> result=req.getResultList();
        return result;
    }

    @Override
    public Communication rechercheCommunication(Long id) {
        Communication co = null;        
        String txt = "SELECT co FROM Communication AS ad WHERE co.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);  
        List<Communication> res = req.getResultList();
        if (res.size() >= 1)
        {
              co = (Communication) res.get(0);
        }
        return co;
    }

    @Override
    public  Communication rechercheCommunicationParNom(String NomCommunication) {
        Communication co = null;        
        String txt = "SELECT co FROM Communication AS co WHERE co.NomCommunication=:nomcommunication ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("nomcommunication", NomCommunication);
        List<Communication> res = req.getResultList();
        if (res.size() >= 1)
        {
              co = (Communication) res.get(0);
        }
        return co;
    }
    
     @Override
    public  Communication modifCommunicationQR(Communication co, String Typeqr) {
               
        String txt = "SELECT co FROM Communication AS co WHERE co.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", co.getId());
        List<Communication> res = req.getResultList();
        if (res.size() >= 1)
        {
            co.setTypeQR(Typeqr);
            em.merge(co);
        }
        return co;
    }
    
    @Override
    public  Communication modifCommunicationNom(Communication co, int delai) {
               
        String txt = "SELECT co FROM Communication AS co WHERE co.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", co.getId());
        List<Communication> res = req.getResultList();
        if (res.size() >= 1)
        {
            co.setDelai(delai);
            em.merge(co);
        }
        return co;
    }
    public CommunicationFacade() {
        super(Communication.class);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Devis;
import Entites.HistoriqueTraitement;
import Entites.TypeUtilisateur;
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
public class HistoriqueTraitementFacade extends AbstractFacade<HistoriqueTraitement> implements HistoriqueTraitementFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void creerHistoriqueTraitement( Date datedebut, Date datefin,TypeUtilisateur utilisateurcourant, Devis devis,UtilisateurHardis consultant, UtilisateurHardis reflocal, UtilisateurHardis validateur ) {
        HistoriqueTraitement ht = new HistoriqueTraitement();
        ht.setDateDebut(datedebut);
        ht.setDateFin(datefin);
        ht.setUtilisateurCourant(utilisateurcourant);
        ht.setDevis(devis);
        ht.setConsultant(consultant);
        ht.setRefLocal(reflocal);
        ht.setValidateur(validateur);
        em.persist(ht);
    }
    @Override
    public List<HistoriqueTraitement> listHistoriqueTraitement() {
        List<HistoriqueTraitement> co=null;
        String txt="SELECT co FROM HistoriqueTraitement AS co ";
        Query req=getEntityManager().createQuery(txt);
        List<HistoriqueTraitement> result=req.getResultList();
        return result;
    }

    @Override
    public HistoriqueTraitement rechercheHistoriqueTraitement(Long id) {
        HistoriqueTraitement he = null;        
        String txt = "SELECT he FROM HistoriqueTraitement AS he WHERE he.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);  
        List<HistoriqueTraitement> res = req.getResultList();
        if (res.size() >= 1)
        {
              he = (HistoriqueTraitement) res.get(0);
        }
        return he;
    }

    @Override
    public  HistoriqueTraitement rechercheHistoriqueTraitementParDevis(Devis devis) {
        HistoriqueTraitement he = null;        
        String txt = "SELECT he FROM HistoriqueTraitement AS he WHERE he.devis=:devis ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("devis",devis.getId() );
        List<HistoriqueTraitement> res = req.getResultList();
        if (res.size() >= 1)
        {
              he = (HistoriqueTraitement) res.get(0);
        }
        return he;
    }
    
    @Override
    public  HistoriqueTraitement rechercheHistoriqueTraitementParConsultant(UtilisateurHardis consultant) {
        HistoriqueTraitement he = null;        
        String txt = "SELECT he FROM HistoriqueTraitement AS he WHERE he.consultant=:consultant ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("consultant",consultant.getId() );
        List<HistoriqueTraitement> res = req.getResultList();
        if (res.size() >= 1)
        {
              he = (HistoriqueTraitement) res.get(0);
        }
        return he;
    }
    
    @Override
    public  HistoriqueTraitement rechercheHistoriqueTraitementParValidateur(UtilisateurHardis validateur) {
        HistoriqueTraitement he = null;        
        String txt = "SELECT he FROM HistoriqueTraitement AS he WHERE he.validateur=:validateur ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("validateur",validateur.getId() );
        List<HistoriqueTraitement> res = req.getResultList();
        if (res.size() >= 1)
        {
              he = (HistoriqueTraitement) res.get(0);
        }
        return he;
    }
    
    @Override
    public  HistoriqueTraitement rechercheHistoriqueTraitementParUtilisateurCourant(UtilisateurHardis utilisateurCourant) {
        HistoriqueTraitement he = null;        
        String txt = "SELECT he FROM HistoriqueTraitement AS he WHERE he.utilisateurCourant=:utilisateurCourant ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("utilisateurCourant",utilisateurCourant.getId() );
        List<HistoriqueTraitement> res = req.getResultList();
        if (res.size() >= 1)
        {
              he = (HistoriqueTraitement) res.get(0);
        }
        return he;
    }
    
     @Override
    public  HistoriqueTraitement modifHistoriqueTraitement(HistoriqueTraitement ht, Date datedebut, Date datefin,TypeUtilisateur utilisateurcourant, Devis devis,UtilisateurHardis consultant, UtilisateurHardis reflocal, UtilisateurHardis validateur) {
               
        String txt = "SELECT ht FROM HistoriqueTraitement AS ht WHERE ht.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", ht.getId());
        List<HistoriqueTraitement> res = req.getResultList();
        if (res.size() >= 1)
        {
            ht.setDateDebut(datedebut);
            ht.setDateFin(datefin);
            ht.setUtilisateurCourant(utilisateurcourant);
            ht.setDevis(devis);
            ht.setConsultant(consultant);
            ht.setRefLocal(reflocal);
            ht.setValidateur(validateur);
            em.merge(ht);
        }
        return ht;
    }
    
    public HistoriqueTraitementFacade() {
        super(HistoriqueTraitement.class);
    }
    
}

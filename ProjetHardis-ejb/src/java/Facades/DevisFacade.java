/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;


import Entites.Agence;
import Entites.Client;
import Entites.Devis;
import Entites.Facturation;
import Entites.Facture;
import Entites.Statut;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gabrielleite

import Entites.Devis;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author anastasia.salari

 */
@Stateless
public class DevisFacade extends AbstractFacade<Devis> implements DevisFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    @Override
    public void creerDevis( Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence ag) {
        Devis de = new Devis();
        de.setDateDevis(date_devis);
        de.setDateIntervSouhaitee(date_intev_souh);
        de.setIndicateurFact(facturation);
        de.setMontantDevis(montantdevis);
        de.setMotifRefus(motifrefus);
        de.setSaisieLibre(saisielibre);
        de.setStatut(statut);
        de.setClient(client);
        de.setAgence(ag);
        de.setFactures(new ArrayList<>());
        em.persist(de);
    }
    @Override
    public List<Devis> listDevis() {
        String txt="SELECT co FROM Devis AS co ";
        Query req=getEntityManager().createQuery(txt);
        List<Devis> result=req.getResultList();
        return result;
    }

    @Override
    public Devis rechercheDevis(long id) {
        Devis co = null;        
        String txt = "SELECT co FROM Devis AS ad WHERE co.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);  
        List<Devis> res = req.getResultList();
        if (res.size() >= 1)
        {
              co = (Devis) res.get(0);
        }
        return co;
    }

    @Override
    public  Devis rechercheDevisParClient(Client client) {
        Devis de = null;        
        String txt = "SELECT de FROM Devis AS de WHERE de.Client=:client ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("client",client.getId() );
        List<Devis> res = req.getResultList();
        if (res.size() >= 1)
        {
              de = (Devis) res.get(0);
        }
        return de;
    }
   
    
     @Override
    public  void modifDevis(Devis entite, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence ag) {       
      
        if (entite!=null){

       
            if (date_devis!=null)
        {
            entite.setDateDevis(date_devis);
        }
            if (date_intev_souh!=null)
        {
            entite.setDateIntervSouhaitee(date_intev_souh);
        }
            if (facturation!=null)
        {
            entite.setIndicateurFact(facturation);
        }
            if (montantdevis!=0)
        {
            entite.setMontantDevis(montantdevis);
        }
            if (!"".equals(motifrefus))
        {
            entite.setMotifRefus(motifrefus);
        }
            if (!"".equals(saisielibre))
        {
            entite.setSaisieLibre(saisielibre);
        }
            if (statut!=null)
        {
            entite.setStatut(statut);
        }
            if (client!=null)
        {
            entite.setClient(client);
        }  
            if (ag!=null)
        {
            entite.setAgence(ag);
        }
            em.merge(entite);
        }
    }
    
    
    

    @Override
    public void supprimerDevis(Devis entite) {
        Query requete = em.createQuery("SELECT s from Devis as s where s.id=:id");
        requete.setParameter("id",entite.getId());     
        List<Devis> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            entite =   liste.get(0); 
            
            em.remove(entite);
        }
    }
    
    public DevisFacade() {
        super(Devis.class);
    }
    

    @Override
    public void majFact(Devis Devis, List<Entites.Facture> listeFact) {
        Devis.setFactures(listeFact);
        em.merge(Devis);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;


import Entites.Agence;
import Entites.Client;
import Entites.DevisNonStandard;
import Entites.Facturation;
import Entites.Statut;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gabrielleite

import Entites.DevisNonStandard;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author anastasia.salari

 */
@Stateless
public class DevisNonStandardFacade extends AbstractFacade<DevisNonStandard> implements DevisNonStandardFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    @Override
    public DevisNonStandard creerDevisNonStandard( Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence ag) {
        DevisNonStandard de = new DevisNonStandard();
        de.setDateDevis(date_devis);
        de.setDateIntervSouhaitee(date_intev_souh);
        de.setIndicateurFact(facturation);
        de.setMontantDevis(montantdevis);
        de.setMotifRefus(motifrefus);
        de.setSaisieLibre(saisielibre);
        de.setStatut(statut);
        de.setClient(client);
        de.setAgence(ag);
        em.persist(de);
        return de;
    }
    @Override
    public List<DevisNonStandard> listDevisNonStandard() {
        String txt="SELECT co FROM DevisNonStandard AS co ";
        Query req=getEntityManager().createQuery(txt);
        List<DevisNonStandard> result=req.getResultList();
        return result;
    }

    @Override
    public DevisNonStandard rechercheDevisNonStandard(long id) {
        DevisNonStandard co = null;        
        String txt = "SELECT co FROM DevisNonStandard AS ad WHERE co.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);  
        List<DevisNonStandard> res = req.getResultList();
        if (res.size() >= 1)
        {
              co = (DevisNonStandard) res.get(0);
        }
        return co;
    }

    @Override
    public  DevisNonStandard rechercheDevisNonStandardParClient(Client client) {
        DevisNonStandard de = null;        
        String txt = "SELECT de FROM DevisNonStandard AS de WHERE de.Client=:client ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("client",client.getId() );
        List<DevisNonStandard> res = req.getResultList();
        if (res.size() >= 1)
        {
              de = (DevisNonStandard) res.get(0);
        }
        return de;
    }
    
    
    @Override
    public  void modifDevis(DevisNonStandard entite, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence ag) {       
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
    public void supprimerDevisNonStandard(DevisNonStandard entite) {
        Query requete = em.createQuery("SELECT s from DevisNonStandard as s where s.id=:id");
        requete.setParameter("id",entite.getId());     
        List<DevisNonStandard> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            entite =   liste.get(0); 
            
            em.remove(entite);
        }
    }
    
    public DevisNonStandardFacade() {
        super(DevisNonStandard.class);
    }

    @Override
    public void accepterRefuserDevisNS(DevisNonStandard d, String choix) {
         if (choix.equals("a"))
            d.setStatut(Statut.Valide);
        else if (choix.equals("r"))
            d.setStatut(Statut.Refuse);
        em.merge(d);
    }
    
    
    
}

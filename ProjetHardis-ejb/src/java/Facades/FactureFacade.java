/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Devis;
import Entites.Entreprise;
import Entites.Facture;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *

 * @author gabrielleite

 * @author anastasia.salari

 */
@Stateless
public class FactureFacade extends AbstractFacade<Facture> implements FactureFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FactureFacade() {
        super(Facture.class);
    }

    @Override
    public Facture creerFacture(Date date, Devis devis, float montant, float montantDepass, String motifDepass) {
        Facture f = new Facture();
        f.setDateFacture(date);
        f.setDevis(devis);
        f.setMontant(montant);
        f.setMontantDepass(montantDepass);
        f.setMotifDepass(motifDepass);
        f.setPaye(false);
        em.persist(f);
        return f;
    }

    @Override
    public void modifFacture(Facture fact, Date date, Devis devis, float montant, long montantD, String motifD) {
        if (fact!=null){
            if (date!=null)
                fact.setDateFacture(date);
            if (devis!=null)
                fact.setDevis(devis);
            if(montant>=0)
                fact.setMontant(montant);
            if(montantD>=0)
                fact.setMontantDepass(montantD);
            if(!motifD.equals(""))
                fact.setMotifDepass(motifD);
            em.merge(fact);
        }
    }

    @Override
    public Facture rechercheFactParId(long id) {
        Facture entite = null;
        String txt = "SELECT entite FROM Facture AS entite WHERE entite.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);
        List<Facture> liste = req.getResultList();
        if (!liste.isEmpty()){
             entite =   liste.get(0);       
        }
      return entite;
    }

    @Override
    public List<Facture> rechercheFactParDevis(Devis d) {
        List<Facture> entite = null;
        String txt = "SELECT entite FROM Facture AS entite WHERE entite.devis=:d";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("d", d);
        List<Facture> liste = req.getResultList();
        if (!liste.isEmpty()){
             entite =   liste;    
        }
      return entite;
    }

    @Override
    public void payerFacture(Facture f) {
        f.setPaye(true);
        em.merge(f);
    }
    
    
    
    
    
    
    
    
    
}

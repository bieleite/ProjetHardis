/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Devis;
import Entites.Facture;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public void creerFacture(Date date, Devis devis, float montant, float montantDepass, String motifDepass) {
        Facture f = new Facture();
        f.setDateFacture(date);
        f.setDevis(devis);
        f.setMontant(montant);
        f.setMontantDepass(montantDepass);
        f.setMotifDepass(motifDepass);
        em.persist(f);
    }

    @Override
    public void modifFacture(Facture fact, Date date, Devis devis, float montant, long montantD, String motifD) {
        
    }
    
    
    
    
    
}

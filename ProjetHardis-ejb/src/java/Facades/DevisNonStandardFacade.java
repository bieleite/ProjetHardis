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
    public void creerDevisNonStandard( Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence ag) {
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
    }
    @Override
    public List<DevisNonStandard> listDevisNonStandard() {
        List<DevisNonStandard> co=null;
        String txt="SELECT co FROM DevisNonStandard AS co ";
        Query req=getEntityManager().createQuery(txt);
        List<DevisNonStandard> result=req.getResultList();
        return result;
    }

    @Override
    public DevisNonStandard rechercheDevisNonStandard(Long id) {
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
    public  DevisNonStandard modifDevisNonStandard(DevisNonStandard de, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence ag) {
               
        String txt = "SELECT co FROM DevisNonStandard AS co WHERE co.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", de.getId());
        List<DevisNonStandard> res = req.getResultList();
        if (res.size() >= 1)
        {
            de.setDateDevis(date_devis);
            de.setDateIntervSouhaitee(date_intev_souh);
            de.setIndicateurFact(facturation);
            de.setMontantDevis(montantdevis);
            de.setMotifRefus(motifrefus);
            de.setSaisieLibre(saisielibre);
            de.setStatut(statut);
            de.setClient(client);
            de.setAgence(ag);
            em.merge(de);
        }
        return de;
    }
    
    public DevisNonStandardFacade() {
        super(DevisNonStandard.class);
    }
    
}

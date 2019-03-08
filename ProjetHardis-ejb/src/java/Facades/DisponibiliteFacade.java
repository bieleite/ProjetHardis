/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Disponibilite;
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
public class DisponibiliteFacade extends AbstractFacade<Disponibilite> implements DisponibiliteFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void creerDisponibilite( Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence ag) {
        Disponibilite de = new Disponibilite();
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
    public List<Disponibilite> listDisponibilite() {
        List<Disponibilite> co=null;
        String txt="SELECT co FROM Disponibilite AS co ";
        Query req=getEntityManager().createQuery(txt);
        List<Disponibilite> result=req.getResultList();
        return result;
    }

    @Override
    public Disponibilite rechercheDisponibilite(Long id) {
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
    public  Disponibilite rechercheDisponibiliteParClient(Client client) {
        Disponibilite de = null;        
        String txt = "SELECT de FROM Disponibilite AS de WHERE de.Client=:client ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("client",client.getId() );
        List<Disponibilite> res = req.getResultList();
        if (res.size() >= 1)
        {
              de = (Disponibilite) res.get(0);
        }
        return de;
    }
    
     @Override
    public  Disponibilite modifDisponibilite(Disponibilite de, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence ag) {
               
        String txt = "SELECT co FROM Disponibilite AS co WHERE co.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", de.getId());
        List<Disponibilite> res = req.getResultList();
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
    
    public DisponibiliteFacade() {
        super(Disponibilite.class);
    }
    
}

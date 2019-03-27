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
import Entites.HistoriqueDevis;
import Entites.HistoriqueEtats;
import Entites.HistoriqueTraitement;
import Entites.Service;
import Entites.Statut;
import Entites.TypeService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
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
    public Devis creerDevis( TypeService type, Service service, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence ag) {
        Devis de = new Devis();
        de.setTypeDevis(type);
        de.setService(service);
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
        de.setHistoriqueDeviss(new ArrayList<>());
        de.setHistoriqueEtatss(new ArrayList<>());
        de.setHistoriqueTraitements(new ArrayList<>());
        de.setEchangeTels(new ArrayList<>());
        de.setCommunications(new ArrayList<>());
        de.setNbJoursPresta(0);
        em.persist(de);
        return de;
    }
    @Override
    public List<Devis> listDevis() {
        String txt="SELECT co FROM Devis AS co order by co.dateDevis desc ";
        Query req=getEntityManager().createQuery(txt);
        List<Devis> result=req.getResultList();
        return result;
    }

    @Override
    public Devis rechercheDevis(long id) {
        Devis co = null;        
        String txt = "SELECT co FROM Devis AS co WHERE co.id=:id";
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
    public  List<Devis> rechercheDevisParClient(Client client) {
        Devis de = null;        
        String txt = "SELECT de FROM Devis AS de WHERE de.client=:client ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("client",client);
        return req.getResultList();

    }
   @Override
    public  void modifDateFinDevis(Devis entite ,Date date_devis){
        if (entite!=null){
            if (date_devis!=null)
        {
            entite.setDateFinPresta(date_devis);
        }
        }
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

    @Override
    public void accepterRefuserDevis(Devis d, String choix) {
        if (choix.equals("a"))
            d.setStatut(Statut.Valide);
        else if (choix.equals("r"))
            d.setStatut(Statut.Refuse);
        em.merge(d);
    }
    
    
    
    @Override
    public void modifDateInterv(Devis d, Date date) {
        d.setDateIntervSouhaitee(date);
        em.merge(d);
    }

    @Override
    public List<Devis> afficherDevisClient(Client cli) {
        Query requete = em.createQuery("SELECT s from Devis as s where s.client=:cli order by s.dateDevis desc");
        requete.setParameter("cli",cli);     
        List<Devis> liste =  requete.getResultList();
        return liste;
    }

    @Override
    public List<Devis> afficherDevisStatut(Client cli, Statut statut) {
        Query requete = em.createQuery("SELECT s from Devis as s where s.client=:cli and s.statut=:statut");
        requete.setParameter("cli",cli);     
        requete.setParameter("statut",statut); 
        List<Devis> liste =  requete.getResultList();
        return liste;
    }

    @Override
    public void majHD(Devis d, HistoriqueDevis hd) {
        d.getHistoriqueDeviss().add(hd);
        em.merge(d);
    }

    @Override
    public void majHT(Devis d, HistoriqueTraitement ht) {
        d.getHistoriqueTraitements().add(ht);
        em.merge(d);
    }

    @Override
    public void majHE(Devis d, HistoriqueEtats he) {
        d.getHistoriqueEtatss().add(he);
        em.merge(d);
    }

    @Override
    public String rechercheDocDevis(Devis d) {
      return "";
    }

    @Override
    public void changeStatutPaye(String type, Devis d) {
        if (type.equals("1"))
        {
            d.setStatut(Statut.Acompte_regle);
        }
        else if (type.equals("2"))
             d.setStatut(Statut.Total_regle);
        em.merge(d);
    }

    @Override
    public void changerStatut(Devis d, String s) {
        if (s.equals("Rep_en_Cours"))
        {
            d.setStatut(Statut.Rep_en_Cours);
        }
        if (s.equals("Acompte_regle"))
        {
            d.setStatut(Statut.Acompte_regle);
        }
        if (s.equals("Total_regle"))
        {
            d.setStatut(Statut.Total_regle);
        }
        
         em.merge(d);
    }

    @Override
    public void majMontant(Devis d, float mont) {
        d.setMontantDevis(mont);
        em.merge(d);
    }

    @Override
    public void majMotifRefus(Devis d, String motif) {
        d.setMotifRefus(motif);
        em.merge(d);
    }

    @Override
    public void majDateDPresta(Devis d) {
        d.setDateDebutPresta(d.getDateIntervSouhaitee());
        em.merge(d);
    }

    @Override
    public void majNbJP(Devis d, float nbJ) {
        d.setNbJoursPresta(nbJ);
        em.merge(d);
    }



   
    
    
    
    
    
    
    
    
    
}

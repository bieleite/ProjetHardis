/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Adresse;
import Entites.Agence;
import Entites.Client;
import Entites.EchangeTel;
import Entites.Entreprise;
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
public class EntrepriseFacade extends AbstractFacade<Entreprise> implements EntrepriseFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntrepriseFacade() {
        super(Entreprise.class);
    }
    
    
    
    

    @Override
    public void majCertif(Entreprise entreprise) {
         if (entreprise!=null){
           
             entreprise.setCertifie(true);
             em.merge(entreprise);
         }
        
    }

    @Override
    public void creerEntreprise(String numero, Agence agence, String nom, List<Entites.Interlocuteur> interlocuteurs, String codeContrat, String mdpEntreprise, Adresse adresse, String lienJustif) {
   Entreprise e = new Entreprise();
   e.setAdresseFact(adresse);
   e.setAgence(agence);
   e.setCertifie(false);
   e.setCodeContrat(codeContrat);
   e.setInterlocuteurs(interlocuteurs);
   e.setMdpEntreprise(mdpEntreprise);
   e.setNumeroEntreprise(numero);
   e.setLienJustif(lienJustif);
   e.setNomEntreprise(nom);
   em.persist(e);
   
    }

    @Override
    public void modifEntreprise(Entreprise e, Agence agence,  String nom, List<Entites.Interlocuteur> interlocuteurs, String codeContrat, String mdpEntreprise, Adresse adresse, String lienJustif, String numeroEnt ) {
        if (e!=null){

            if (agence!=null)
               e.setAgence(agence);
            if (!codeContrat.equals(""))
                 e.setCodeContrat(codeContrat);
            if (adresse !=null)
                 e.setAdresseFact(adresse);
            if (!mdpEntreprise.equals(""))
                 e.setMdpEntreprise(mdpEntreprise);
            if(!numeroEnt.equals(""))
                 e.setNumeroEntreprise(numeroEnt);
            if(!lienJustif.equals(""))
             e.setLienJustif(lienJustif);
            if(!nom.equals(""))
             e.setNomEntreprise(nom);
            
            em.merge(e);
    }
    }

    @Override
    public Entreprise rechercheEntrepriseSiret(String siret) {
        Entreprise entreprise = null;
        String txt = "SELECT entite FROM Entreprise AS entite WHERE entite.numeroEntreprise=:siret";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("siret", siret);
        List<Entreprise> liste = req.getResultList();
        if (!liste.isEmpty()){
             entreprise =   liste.get(0);
           
        }
      return entreprise;
    }

    @Override
    public Entreprise rechercheEntrepriseParId(long id) {
      Entreprise entreprise = null;
        String txt = "SELECT entite FROM Entreprise AS entite WHERE entite.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);
        List<Entreprise> liste = req.getResultList();
        if (!liste.isEmpty()){
             entreprise =   liste.get(0);
           
        }
      return entreprise;
    }

    @Override
    public Entreprise rechercheEntrepriseParNom(String nom) {
         Entreprise entreprise = null;
        String txt = "SELECT entite FROM Entreprise AS entite WHERE entite.nomEntreprise=:nom";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("nom", nom);
        List<Entreprise> liste = req.getResultList();
        if (!liste.isEmpty()){
             entreprise =   liste.get(0);
           
        }
      return entreprise;
    }

    @Override
    public Entreprise rechercheEntrepriseParMDP(String mdp) {
         Entreprise entreprise = null;
        String txt = "SELECT entite FROM Entreprise AS entite WHERE entite.mdpEntreprise=:mdp";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("mdp", mdp);
        List<Entreprise> liste = req.getResultList();
        if (!liste.isEmpty()){
             entreprise =   liste.get(0);
           
        }
      return entreprise;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

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
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    public Entreprise creerEntreprise(String numero, String nom, List<Entites.Interlocuteur> interlocuteurs, Adresse adresse) {
   boolean b = false;
        Entreprise e = new Entreprise();
   
   e.setAdresseFact(adresse);
   e.setAgence(null);
   e.setCertifie(false);
   e.setInterlocuteurs(interlocuteurs);
   e.setNumeroEntreprise(numero);
   e.setLienJustif("");
   e.setNomEntreprise(nom);
   e.setVisible(true);
   em.persist(e);
   e.setCodeContrat(creerCodeContrat(e.getId()));
   em.merge(e);
   while (!b){
    String mdp = generateString(12);
    Entreprise ent = rechercheEntrepriseParMDP(mdp);
    if (ent==null)
    {
        e.setMdpEntreprise(mdp);
        em.merge(e);
        b = true;
        break;
    }
    }
return e;
   
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
    
   /* @Override
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
    }*/

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

    @Override
    public void supprimerEntreprise(Entreprise e) {
        e.setVisible(false);
        em.merge(e);
    }

    @Override
    public String generateString(int n) {
        
       byte[] array = new byte[256]; 
        new Random().nextBytes(array); 
  
        String randomString 
            = new String(array, Charset.forName("UTF-8")); 
  
        // Create a StringBuffer to store the result 
        StringBuffer r = new StringBuffer(); 
  
        // remove all spacial char 
        String  AlphaNumericString 
            = randomString 
                  .replaceAll("[^A-Za-z0-9]", ""); 
  
        // Append first 20 alphanumeric characters 
        // from the generated random String into the result 
        for (int k = 0; k < AlphaNumericString.length(); k++) { 
  
            if (Character.isLetter(AlphaNumericString.charAt(k)) 
                    && (n > 0) 
                || Character.isDigit(AlphaNumericString.charAt(k)) 
                       && (n > 0)) { 
  
                r.append(AlphaNumericString.charAt(k)); 
                n--; 
            } 
        } 
  
        // return the resultant string 
        return r.toString(); 
    } 

    @Override
    public String creerCodeContrat(long id) {
       return  String.format("%06d", id);  
       
    }

    @Override
    public Entreprise rechercheEntrepriseCodeMdp(String code, String mdp) {
       Entreprise entreprise = null;
        String txt = "SELECT entite FROM Entreprise AS entite WHERE entite.codeContrat=:code and entite.mdpEntreprise=:mdp";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("code", code);
              req = req.setParameter("mdp", mdp);
        List<Entreprise> liste = req.getResultList();
        if (!liste.isEmpty()){
             entreprise =   liste.get(0);
           
        }
      return entreprise; 
    }
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

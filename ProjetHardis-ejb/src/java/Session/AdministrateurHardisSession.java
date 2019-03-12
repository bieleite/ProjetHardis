/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entites.Action;
import Entites.Adresse;
import Entites.Agence;
import Entites.Atelier;
import Entites.Client;
import Entites.Communication;
import Entites.Devis;
import Entites.DevisNonStandard;
import Entites.Entreprise;
import Entites.Facturation;
import Entites.FacturationFrais;
import Entites.Facture;
import Entites.LieuIntervention;
import Entites.Offre;
import Entites.Service;
import Entites.Statut;
import Entites.TypeService;
import Entites.UtilisateurHardis;
import Facades.AdresseFacadeLocal;
import Facades.AgenceFacadeLocal;
import Facades.AtelierFacadeLocal;
import Facades.ClientFacadeLocal;
import Facades.CommunicationFacadeLocal;
import Facades.DevisFacadeLocal;
import Facades.DevisNonStandardFacadeLocal;
import Facades.EntrepriseFacadeLocal;
import Facades.LogsFacadeLocal;
import Facades.ServiceFacadeLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author gabrielleite
 */
@Stateless
public class AdministrateurHardisSession implements AdministrateurHardisSessionLocal {

    @EJB
    private DevisNonStandardFacadeLocal devisNonStandardFacade;

    @EJB
    private CommunicationFacadeLocal communicationFacade;

    @EJB
    private AtelierFacadeLocal atelierFacade;

    @EJB
    private AdresseFacadeLocal adresseFacade;

    @EJB
    private AgenceFacadeLocal agenceFacade;

    @EJB
    private DevisFacadeLocal devisFacade;

    @EJB
    private ServiceFacadeLocal serviceFacade;

    @EJB
    private EntrepriseFacadeLocal entrepriseFacade;

    @EJB
    private LogsFacadeLocal logsFacade;

    @EJB
    private ClientFacadeLocal clientFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    
    @Override
    public void creerAdresse(int numRue, String nomRue, String ville, String CP, UtilisateurHardis hardis) {
        Adresse ad = adresseFacade.creerAdresse(numRue, nomRue, ville, CP);
        logsFacade.creerLogCreate(hardis, ad);
    }

    @Override
    public void modifierAdresse(Adresse ad, int numRue, String nomRue, String ville, String CP, UtilisateurHardis hardis) {
        adresseFacade.modifAdresse(ad, numRue, nomRue, ville, CP);
        logsFacade.creerLogUpdate(hardis, ad);
    }
    
    @Override
    public void supprimerAdresse(Adresse ad, UtilisateurHardis hardis) {
        adresseFacade.supprimerAdresse(ad);
        logsFacade.creerLogDelete(hardis, ad);
    }
    
     @Override
    public Adresse rechercherAdresse(long id, UtilisateurHardis hardis) {
        Adresse ad = adresseFacade.rechercheAdresse(id);
        logsFacade.creerLogResearch(hardis, ad);
        return ad;
    }
    
    @Override
    public List<Adresse> rechercherAdresseParCP(String CP, UtilisateurHardis hardis) {
        List<Adresse> ad = adresseFacade.rechercheAdresseParCP(CP);
        logsFacade.creerLogResearch(hardis, CP);
        return ad;       
    }
    
    @Override
    public void creerAgence(String NomAgence, UtilisateurHardis hardis) {
        Agence ag =agenceFacade.creerAgence(NomAgence);
        logsFacade.creerLogCreate(hardis, ag);
    }

    @Override
    public void modifierAgence(Agence agence, String nomagence, UtilisateurHardis hardis) {
        agenceFacade.modifAgence(agence, nomagence);
        logsFacade.creerLogUpdate(hardis, agence);
    }
    
    @Override
    public void supprimerAgence(Agence agence, UtilisateurHardis hardis) {
        agenceFacade.supprimerAgence(agence);
        logsFacade.creerLogDelete(hardis, agence);
    }
    
     @Override
    public Agence rechercherAgence(long id, String nomAgence, UtilisateurHardis hardis) {
       Agence ag = null;
        if (!"".equals(nomAgence))
        {
            ag = agenceFacade.rechercheAgenceParNom(nomAgence);
            logsFacade.creerLogResearch(hardis, ag);
        }
        
        else  if (id!=0)
        {
            ag = agenceFacade.rechercheAgence(id);
            logsFacade.creerLogResearch(hardis, ag);
        }
        
        return ag;
    }
      
    @Override
    public void creerAtelier(String NomAtelier, UtilisateurHardis hardis) {
        Atelier at = atelierFacade.creerAtelier(NomAtelier);
        logsFacade.creerLogCreate(hardis, at);
    }

    @Override
    public void modifierAtelier(Atelier at, String nomatelier, UtilisateurHardis hardis) {
        atelierFacade.modifAtelier(at, nomatelier);
        logsFacade.creerLogUpdate(hardis, at);
    }
    
    @Override
    public void supprimerAtelier(Atelier at, UtilisateurHardis hardis) {
        atelierFacade.supprimerAtelier(at);
        logsFacade.creerLogDelete(hardis, at);
    }
    
     @Override
    public Atelier rechercherAtelier(long id, String nomAtelier, UtilisateurHardis hardis) {
       Atelier at = null;
        if (!"".equals(nomAtelier))
        {
            at = atelierFacade.rechercheAtelierParNom(nomAtelier);
            logsFacade.creerLogResearch(hardis, at);
        }
        
        else  if (id!=0)
        {
            at = atelierFacade.rechercheAtelier(id);
            logsFacade.creerLogResearch(hardis, at);
        }
        
        return at;
    }
    
    @Override
    public void affecterClient(Client client, Entreprise entreprise, UtilisateurHardis hardis ) {
        clientFacade.majEntrepriseClient(client, entreprise);
        logsFacade.creerLogUpdate(hardis, client);
        
    }

    @Override
    public void supprimerClient(Client client,UtilisateurHardis hardis) {
        clientFacade.supprimerClient(client);
        logsFacade.creerLogDelete(hardis, client);
    }

    @Override
    public void validerCompteClient(Client client,UtilisateurHardis hardis) {
        clientFacade.modfiClientVisible(client);
        logsFacade.creerLogUpdate(hardis, client);
    }

    @Override
    public Client rechercherClient(long id, String nomclient, String loginclient, UtilisateurHardis hardis) {
        Client cl = null;
        if (!"".equals(nomclient))
        {
            cl = clientFacade.rechercheClientParNom(nomclient);
            logsFacade.creerLogResearch(hardis, cl);
        }
        if (!"".equals(loginclient))
        {
            cl = clientFacade.rechercheClientParLogin(loginclient);
            logsFacade.creerLogResearch(hardis, cl);
        }
        
        else  if (id!=0)
        {
            cl = clientFacade.rechercheClient(id);
            logsFacade.creerLogResearch(hardis, cl);
        }
        
        return cl;
    }

    @Override
    public void certifieClient(Client client, UtilisateurHardis hardis) {
        clientFacade.majCertif(client);
        logsFacade.creerLogResearch(hardis, client);
    }
    
    @Override
    public void creerCommunicationHardis(String message, Devis devis, UtilisateurHardis utilisateur) {
        Client cl = clientFacade.rechercheClientParDevis(devis);
        Devis de = devisFacade.rechercheDevis(devis.getId());
        List<Communication> liste = communicationFacade.rechercheCommunicationParDevis(devis);
        Date date = new Date();
        String qr = "R";
        if(liste.isEmpty()){
            int delai = 0;
            Communication co =communicationFacade.creerCommunication(date, message, devis, utilisateur, qr, delai);
            logsFacade.creerLogCreate(utilisateur, co);
        }
        else{
            int delai = communicationFacade.calculerDelai(devis, date);
            Communication co =communicationFacade.creerCommunication(date, message, devis, utilisateur, qr, delai);
            logsFacade.creerLogCreate(utilisateur, co);
            
        }
    }

    @Override
    public void modifierCommunication(Communication entite, Date date_comu, String message, Devis devis, UtilisateurHardis utilisateur, UtilisateurHardis hardis) {
        communicationFacade.modifCommunication(entite, date_comu, message, devis, utilisateur);
        logsFacade.creerLogUpdate(hardis, entite);
    }
    
    @Override
    public void supprimerCommunication(Communication co, UtilisateurHardis hardis) {
        communicationFacade.supprimerCommunication(co);
        logsFacade.creerLogDelete(hardis, co);
    }
    
     @Override
    public List<Communication> rechercherCommunication(Devis devis,UtilisateurHardis utilisateur, UtilisateurHardis hardis) {
       List<Communication> co = null;
        if (utilisateur!=null)
        {
            co=communicationFacade.rechercheCommunicationParUtilisateurHardis(utilisateur);
            logsFacade.creerLogResearch(hardis, utilisateur);
        }
        else if (devis!=null)
        {
            co =communicationFacade.rechercheCommunicationParDevis(devis);
            logsFacade.creerLogResearch(hardis, devis);
        }
        return co;
    }
    
    @Override
    public Communication rechercherCommunicationID(long id, UtilisateurHardis hardis) {
        Communication co  =communicationFacade.rechercheCommunication(id);
        logsFacade.creerLogResearch(hardis, co);
        return co;
    }
    
    @Override
    public void supprimerDevis(Devis devis, UtilisateurHardis hardis) {
        devisFacade.supprimerDevis(devis);
        logsFacade.creerLogDelete(hardis, devis);
    }
    
     @Override
    public Devis rechercherDevis(long id, Client client, UtilisateurHardis hardis) {
       Devis de = null;
        if (client!=null)
        {
            de = devisFacade.rechercheDevisParClient(client);
            logsFacade.creerLogResearch(hardis, de);
        }
        
        else  if (id!=0)
        {
            de = devisFacade.rechercheDevis(id);
            logsFacade.creerLogResearch(hardis, de);
        }
        
        return de;
    }
    
    @Override
    public void modifieDevis(Devis entite, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence ag, UtilisateurHardis hardis) {
        devisFacade.modifDevis(entite, date_devis, date_intev_souh, facturation, montantdevis, motifrefus, saisielibre, statut, client, ag);
        logsFacade.creerLogUpdate(hardis, entite);
    }
    
    @Override
    public void devisFactures(Devis devis, List<Facture> listfacture, UtilisateurHardis hardis) {
        devisFacade.majFact(devis, listfacture);
        logsFacade.creerLogUpdate(hardis, devis);
    }
  
       
    @Override
    public void supprimerDevisNonStandard(DevisNonStandard devis, UtilisateurHardis hardis) {
        devisNonStandardFacade.supprimerDevisNonStandard(devis);
        logsFacade.creerLogDelete(hardis, devis);
    }
    
     @Override
    public DevisNonStandard rechercherDevisNonStandart(long id, Client client, UtilisateurHardis hardis) {
       DevisNonStandard de = null;
        if (client!=null)
        {
            de = devisNonStandardFacade.rechercheDevisNonStandardParClient(client);
            logsFacade.creerLogResearch(hardis, de);
        }
        
        else  if (id!=0)
        {
            de = devisNonStandardFacade.rechercheDevisNonStandard(id);
            logsFacade.creerLogResearch(hardis, de);
        }
        
        return de;
    }
    
    @Override
    public void modifieDevisNonStandard(DevisNonStandard entite, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence ag, UtilisateurHardis hardis) {
        devisNonStandardFacade.modifDevis(entite, date_devis, date_intev_souh, facturation, montantdevis, motifrefus, saisielibre, statut, client, ag);
        logsFacade.creerLogUpdate(hardis, entite);
    }
    
    @Override
    public void accepterdevisNonStandard(DevisNonStandard devis, String choix, UtilisateurHardis hardis) {
        devisNonStandardFacade.accepterRefuserDevisNS(devis, choix);
        logsFacade.creerLogUpdate(hardis, devis);
    }
  
    @Override
    public Entreprise rechercherEntreprise(long id, String siret, String nomentreprise, UtilisateurHardis hardis) {
        Entreprise en = null;
        if (!"".equals(siret))
        {
            en = entrepriseFacade.rechercheEntrepriseSiret(siret);
            logsFacade.creerLogResearch(hardis, en);
        }
        if (!"".equals(nomentreprise))
        {
            en = entrepriseFacade.rechercheEntrepriseParNom(nomentreprise);
            logsFacade.creerLogResearch(hardis, en);
        }
        
        else  if (id!=0)
        {
            en = entrepriseFacade.rechercheEntrepriseParId(id);
            logsFacade.creerLogResearch(hardis, en);
        }
        
        return en;
    }
     
    
    @Override
    public void certifieEntreprise(Entreprise entreprise, UtilisateurHardis hardis) {
        entrepriseFacade.majCertif(entreprise);
        logsFacade.creerLogUpdate(hardis, entreprise);
    }

    @Override
    public void modifieEntreprise(Entreprise entreprise, Agence agence,  String nom, List<Entites.Interlocuteur> interlocuteurs, String codeContrat, String mdpEntreprise, Adresse adresse, String lienJustif, String numeroEnt, UtilisateurHardis hardis) {
        entrepriseFacade.modifEntreprise(entreprise, agence, nom, interlocuteurs, codeContrat, mdpEntreprise, adresse, lienJustif, numeroEnt);
        logsFacade.creerLogUpdate(hardis, entreprise);
    }
    
    @Override
    public void modifieService(Service s, String nomService, String descriptionService, LieuIntervention lieuInterv, Offre offre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS, UtilisateurHardis hardis) {
        serviceFacade.modifierService(s, nomService, descriptionService, lieuInterv, offre, cout, facturation, listeCond, delai, typeS);
        logsFacade.creerLogUpdate(hardis, s);
    }
    
     @Override
    public void supprimerService(Service s ,UtilisateurHardis hardis) {
        serviceFacade.supprimerService(s.getId());
        logsFacade.creerLogCreate(hardis, s);
    }
    
    @Override
    public List<Service> rechercherService(Offre of, UtilisateurHardis hardis) {
        List<Service> se = new ArrayList<Service>();
        se = serviceFacade.rechercheServiceParOffre(of);
        logsFacade.creerLogResearch(hardis, of);
        return se;       
    }
    
    @Override
    public Service rechercherServiceId(long id, UtilisateurHardis hardis) {
       Service se = serviceFacade.rechercheServiceParId(id);
        logsFacade.creerLogResearch(hardis, se);
        return se;
    }
    
    
    
    
    
    
    
    
}

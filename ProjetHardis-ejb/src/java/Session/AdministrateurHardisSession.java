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
        Date date = new Date();
        String libelle = "Ajouté factures au devis id: " + ad.getId() +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Create, date, libelle, hardis);
    }

    @Override
    public void modifierAdresse(Adresse ad, int numRue, String nomRue, String ville, String CP, UtilisateurHardis hardis) {
        adresseFacade.modifAdresse(ad, numRue, nomRue, ville, CP);
        Date date = new Date();
        String libelle = "Ajouté factures au devis id: " + ad.getId() +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Update, date, libelle, hardis);
    }
    
    @Override
    public void supprimerAdresse(Adresse ad, UtilisateurHardis hardis) {
        adresseFacade.supprimerAdresse(ad);
        Date date = new Date();
        String libelle = "Supprimer agence id: " + ad.getId() +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Delete, date, libelle, hardis);
    }
    
     @Override
    public Adresse rechercherAdresse(long id, UtilisateurHardis hardis) {
        Adresse ad = adresseFacade.rechercheAdresse(id);
        Date date = new Date();
        String libelle = "Rechercher lagence id: "+ ad.getId() +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Research, date, libelle, hardis);
        return ad;
    }
    
    @Override
    public List<Adresse> rechercherAdresseParCP(String CP, UtilisateurHardis hardis) {
        List<Adresse> ad = adresseFacade.rechercheAdresseParCP(CP);
        Date date = new Date();
        String libelle = "Rechercher adresse par le CP: " + CP +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Research, date, libelle, hardis);
        return ad;       
    }
    
    @Override
    public void creerAgence(String NomAgence, UtilisateurHardis hardis) {
        agenceFacade.creerAgence(NomAgence);
        Agence ag = agenceFacade.rechercheAgenceParNom(NomAgence);
        Date date = new Date();
        String libelle = "Ajouté factures au devis id: " + ag.getId() +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Create, date, libelle, hardis);
    }

    @Override
    public void modifierAgence(Agence agence, String nomagence, UtilisateurHardis hardis) {
        agenceFacade.modifAgence(agence, nomagence);
        Date date = new Date();
        String libelle = "Ajouté factures au devis id: " + agence.getId() +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Update, date, libelle, hardis);
    }
    
    @Override
    public void supprimerAgence(Agence agence, UtilisateurHardis hardis) {
        agenceFacade.supprimerAgence(agence);
        Date date = new Date();
        String libelle = "Supprimer agence id: " + agence.getId() +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Delete, date, libelle, hardis);
    }
    
     @Override
    public Agence rechercherAgence(long id, String nomAgence, UtilisateurHardis hardis) {
       Agence ag = null;
        if (!"".equals(nomAgence))
        {
            ag = agenceFacade.rechercheAgenceParNom(nomAgence);
            Date date = new Date();
            String libelle = "Rechercher lagence id: " + ag.getId() + "nom: "+ag.getNomAgence() +" par lutilisateur: "+ hardis.getId() ;
            logsFacade.creerLog(Action.Research, date, libelle, hardis);
        }
        
        else  if (id!=0)
        {
            ag = agenceFacade.rechercheAgence(id);
            Date date = new Date();
            String libelle = "Rechercher lagence id: "+ ag.getId() + "nom: "+ag.getNomAgence() +" par lutilisateur: "+ hardis.getId() ;
            logsFacade.creerLog(Action.Research, date, libelle, hardis);
        }
        
        return ag;
    }
      
    @Override
    public void creerAtelier(String NomAtelier, UtilisateurHardis hardis) {
        Atelier at = atelierFacade.creerAtelier(NomAtelier);
        Date date = new Date();
        String libelle = "Ajouté atelier: " + at.getId() +" nom: "+at.getNomAtelier()+" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Create, date, libelle, hardis);
    }

    @Override
    public void modifierAtelier(Atelier at, String nomatelier, UtilisateurHardis hardis) {
        atelierFacade.creerAtelier(nomatelier);
        Date date = new Date();
        String libelle = "Modifier atelier id: " + at.getId() +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Update, date, libelle, hardis);
    }
    
    @Override
    public void supprimerAtelier(Atelier at, UtilisateurHardis hardis) {
        atelierFacade.supprimerAtelier(at);
        Date date = new Date();
        String libelle = "Supprimer agence id: " + at.getId() +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Delete, date, libelle, hardis);
    }
    
     @Override
    public Atelier rechercherAtelier(long id, String nomAtelier, UtilisateurHardis hardis) {
       Atelier at = null;
        if (!"".equals(nomAtelier))
        {
            at = atelierFacade.rechercheAtelierParNom(nomAtelier);
            Date date = new Date();
            String libelle = "Rechercher latelier id: " + at.getId() + "nom: "+at.getNomAtelier() +" par lutilisateur: "+ hardis.getId() ;
            logsFacade.creerLog(Action.Research, date, libelle, hardis);
        }
        
        else  if (id!=0)
        {
            at = atelierFacade.rechercheAtelier(id);
            Date date = new Date();
            String libelle = "Rechercher latelier id: " + at.getId() + "nom: "+at.getNomAtelier() +" par lutilisateur: "+ hardis.getId() ;
            logsFacade.creerLog(Action.Research, date, libelle, hardis);
        }
        
        return at;
    }
    
    @Override
    public void affecterClient(Client client, Entreprise entreprise, UtilisateurHardis hardis ) {
        clientFacade.majEntrepriseClient(client, entreprise);
        Date date = new Date();
        String libelle = "Affectation client: "+client.getNom()+" dans l'entreprise: "+ entreprise.getNomEntreprise();
        logsFacade.creerLog(Action.Update, date, libelle, hardis);
        
    }

    @Override
    public void supprimerClient(Client client,UtilisateurHardis hardis) {
        clientFacade.supprimerClient(client);
        Date date = new Date();
        String libelle = "Suppression du client id: " + client.getId() + " nom:  " + client.getNom() +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Delete, date, libelle, hardis);
    }

    @Override
    public void validerCompteClient(Client client,UtilisateurHardis hardis) {
        clientFacade.modfiClientVisible(client);
        Date date = new Date();
        String libelle = "Set visible le client id: " + client.getId() + " nom:  " + client.getNom() +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Update, date, libelle, hardis);
    }

    @Override
    public Client rechercherClient(long id, String nomclient, String loginclient, UtilisateurHardis hardis) {
        Client cl = null;
        if (!"".equals(nomclient))
        {
            cl = clientFacade.rechercheClientParNom(nomclient);
            Date date = new Date();
            String libelle = "Research le client id: " + cl.getId() + " nom:  " + cl.getNom() +" par lutilisateur: "+ hardis.getId() ;
            logsFacade.creerLog(Action.Update, date, libelle, hardis);
        }
        if (!"".equals(loginclient))
        {
            cl = clientFacade.rechercheClientParLogin(loginclient);
            Date date = new Date();
            String libelle = "Research le client id: " + cl.getId() + " nom:  " + cl.getNom() +" par lutilisateur: "+ hardis.getId() ;
            logsFacade.creerLog(Action.Update, date, libelle, hardis);
        }
        
        else  if (id!=0)
        {
            cl = clientFacade.rechercheClient(id);
            Date date = new Date();
            String libelle = "Research le client id: " + cl.getId() + " nom:  " + cl.getNom() +" par lutilisateur: "+ hardis.getId() ;
            logsFacade.creerLog(Action.Update, date, libelle, hardis);
        }
        
        return cl;
    }

    @Override
    public void certifieClient(Client client, UtilisateurHardis hardis) {
        clientFacade.majCertif(client);
        Date date = new Date();
        String libelle = "Set visible le client id: " + client.getId() + " nom:  " + client.getNom() +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Update, date, libelle, hardis);
    }
    
    @Override
    public Entreprise rechercherEntreprise(long id, String siret, String nomentreprise, UtilisateurHardis hardis) {
        Entreprise en = null;
        if (!"".equals(siret))
        {
            en = entrepriseFacade.rechercheEntrepriseSiret(siret);
            Date date = new Date();
            String libelle = "Rechercher lentreprise id: " + en.getId() + " nom:  " + en.getNomEntreprise() +" par lutilisateur: "+ hardis.getId() ;
            logsFacade.creerLog(Action.Research, date, libelle, hardis);
        }
        if (!"".equals(nomentreprise))
        {
            en = entrepriseFacade.rechercheEntrepriseParNom(nomentreprise);
            Date date = new Date();
            String libelle = "Rechercher lentreprise id: " + en.getId() + " nom:  " + en.getNomEntreprise() +" par lutilisateur: "+ hardis.getId() ;
            logsFacade.creerLog(Action.Research, date, libelle, hardis);
        }
        
        else  if (id!=0)
        {
            en = entrepriseFacade.rechercheEntrepriseParId(id);
            Date date = new Date();
            String libelle = "Rechercher lentreprise id: " + en.getId() + " nom:  " + en.getNomEntreprise() +" par lutilisateur: "+ hardis.getId() ;
            logsFacade.creerLog(Action.Research, date, libelle, hardis);
        }
        
        return en;
    }
    
    @Override
    public void certifieEntreprise(Entreprise entreprise, UtilisateurHardis hardis) {
        entrepriseFacade.majCertif(entreprise);
        Date date = new Date();
        String libelle = "lutilisateur: "+ hardis.getId() +"certifie l'entreprise id: " + entreprise.getId() + " nom:  " + entreprise.getNomEntreprise() ;
        logsFacade.creerLog(Action.Update, date, libelle, hardis);
    }

    @Override
    public void modifieEntreprise(Entreprise entreprise, Agence agence,  String nom, List<Entites.Interlocuteur> interlocuteurs, String codeContrat, String mdpEntreprise, Adresse adresse, String lienJustif, String numeroEnt, UtilisateurHardis hardis) {
        entrepriseFacade.modifEntreprise(entreprise, agence, nom, interlocuteurs, codeContrat, mdpEntreprise, adresse, lienJustif, numeroEnt);
        Date date = new Date();
        String libelle = "lutilisateur: "+ hardis.getId() +"modifie l'entreprise id: " + entreprise.getId() + " nom:  " + entreprise.getNomEntreprise() ;
        logsFacade.creerLog(Action.Update, date, libelle, hardis);
    }
    
    @Override
    public void modifieService(Service s, String nomService, String descriptionService, LieuIntervention lieuInterv, Offre offre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS, UtilisateurHardis hardis) {
        serviceFacade.modifierService(s, nomService, descriptionService, lieuInterv, offre, cout, facturation, listeCond, delai, typeS);
        Date date = new Date();
        String libelle = "lutilisateur: "+ hardis.getId() +"modifie l'entreprise id: " + s.getId() + " nom:  " + s.getNomService() ;
        logsFacade.creerLog(Action.Update, date, libelle, hardis);
    }
    
     @Override
    public void supprimerService(Service s ,UtilisateurHardis hardis) {
        serviceFacade.supprimerService(s.getId());
        Date date = new Date();
        String libelle = "Suppression du service id: " + s.getId() + " nom:  " + s.getNomService() +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Delete, date, libelle, hardis);
    }
    
    @Override
    public List<Service> rechercherService(Offre of, UtilisateurHardis hardis) {
        List<Service> se = new ArrayList<Service>();
        se = serviceFacade.rechercheServiceParOffre(of);
        Date date = new Date();
        String libelle = "Rechercher loffre nom:  " + of.getId() +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Research, date, libelle, hardis);
        return se;       
    }
    
    @Override
    public Service rechercherServiceId(long id, UtilisateurHardis hardis) {
       Service se = serviceFacade.rechercheServiceParId(id);
        Date date = new Date();
        String libelle = "Rechercher service id: " + se.getId() + " nom:  " + se.getNomService() +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Research, date, libelle, hardis);
        return se;
    }
    
    @Override
    public void supprimerDevis(Devis devis, UtilisateurHardis hardis) {
        devisFacade.supprimerDevis(devis);
        Date date = new Date();
        String libelle = "Suppression du devis id: " + devis.getId() + "par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Delete, date, libelle, hardis);
    }
    
     @Override
    public Devis rechercherService(long id, Client client, UtilisateurHardis hardis) {
       Devis de = null;
        if (client!=null)
        {
            de = devisFacade.rechercheDevisParClient(client);
            Date date = new Date();
            String libelle = "Rechercher le devis id: " + de.getId() + " par lutilisateur: "+ hardis.getId() ;
            logsFacade.creerLog(Action.Research, date, libelle, hardis);
        }
        
        else  if (id!=0)
        {
            de = devisFacade.rechercheDevis(id);
            Date date = new Date();
            String libelle = "Rechercher lentreprise id: " + de.getId() + " par lutilisateur: "+ hardis.getId() ;
            logsFacade.creerLog(Action.Research, date, libelle, hardis);
        }
        
        return de;
    }
    
    @Override
    public void modifieDevis(Devis entite, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence ag, UtilisateurHardis hardis) {
        devisFacade.modifDevis(entite, date_devis, date_intev_souh, facturation, montantdevis, motifrefus, saisielibre, statut, client, ag);
        Date date = new Date();
        String libelle = "lutilisateur: "+ hardis.getId() +"modifie le devis id: " + entite.getId() ;
        logsFacade.creerLog(Action.Update, date, libelle, hardis);
    }
    
    @Override
    public void devisFactures(Devis devis, List<Facture> listfacture, UtilisateurHardis hardis) {
        devisFacade.majFact(devis, listfacture);
        Date date = new Date();
        String libelle = "Ajouté factures au devis id: " + devis.getId() +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Update, date, libelle, hardis);
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
            communicationFacade.creerCommunication(date, message, devis, utilisateur, qr, delai);
            String libelle = "Ajouté atelier: " + at.getId() +" nom: "+at.getNomAtelier()+" par lutilisateur: "+ hardis.getId() ;
            logsFacade.creerLog(Action.Create, date, libelle, hardis);
        }
        else{
            
            
        }
    }

    @Override
    public void modifierCommunication(Atelier at, String nomatelier, UtilisateurHardis hardis) {
        atelierFacade.creerAtelier(nomatelier);
        Date date = new Date();
        String libelle = "Modifier atelier id: " + at.getId() +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Update, date, libelle, hardis);
    }
    
    @Override
    public void supprimerCommunication(Atelier at, UtilisateurHardis hardis) {
        atelierFacade.supprimerAtelier(at);
        Date date = new Date();
        String libelle = "Supprimer agence id: " + at.getId() +" par lutilisateur: "+ hardis.getId() ;
        logsFacade.creerLog(Action.Delete, date, libelle, hardis);
    }
    
     @Override
    public Atelier rechercherCommunication(long id, String nomAtelier, UtilisateurHardis hardis) {
       Atelier at = null;
        if (!"".equals(nomAtelier))
        {
            at = atelierFacade.rechercheAtelierParNom(nomAtelier);
            Date date = new Date();
            String libelle = "Rechercher latelier id: " + at.getId() + "nom: "+at.getNomAtelier() +" par lutilisateur: "+ hardis.getId() ;
            logsFacade.creerLog(Action.Research, date, libelle, hardis);
        }
        
        else  if (id!=0)
        {
            at = atelierFacade.rechercheAtelier(id);
            Date date = new Date();
            String libelle = "Rechercher latelier id: " + at.getId() + "nom: "+at.getNomAtelier() +" par lutilisateur: "+ hardis.getId() ;
            logsFacade.creerLog(Action.Research, date, libelle, hardis);
        }
        
        return at;
    }
    
    
    
    
    
    
    
    
    
}

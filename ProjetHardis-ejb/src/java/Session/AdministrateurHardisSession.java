/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entites.Action;
import Entites.*;
import Entites.Agence;
import Entites.Atelier;
import Entites.Client;
import Entites.Communication;
import Entites.Devis;
import Entites.DevisNonStandard;
import Entites.Disponibilite;
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
import Facades.DisponibiliteFacadeLocal;
import Facades.DocumentFacadeLocal;
import Facades.EchangeTelFacadeLocal;
import Facades.EntrepriseFacadeLocal;
import Facades.FactureFacadeLocal;
import Facades.HistoriqueDevisFacadeLocal;
import Facades.HistoriqueEtatsFacadeLocal;
import Facades.HistoriqueTraitementFacadeLocal;
import Facades.InterlocuteurFacadeLocal;
import Facades.LivrableFacadeLocal;
import Facades.LogsFacadeLocal;
import Facades.NotificationFacadeLocal;
import Facades.OffreFacadeLocal;
import Facades.Offre_Profil_Util_CVFacadeLocal;
import Facades.ProfilMetierFacadeLocal;
import Facades.ServiceFacadeLocal;
import Facades.ServiceStandardFacadeLocal;
import Facades.UtilisateurHardisFacadeLocal;
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
    private NotificationFacadeLocal notificationFacade;

    @EJB
    private ServiceStandardFacadeLocal serviceStandardFacade;

    @EJB
    private ProfilMetierFacadeLocal profilMetierFacade;

    @EJB
    private Offre_Profil_Util_CVFacadeLocal offre_Profil_Util_CVFacade;

    @EJB
    private LivrableFacadeLocal livrableFacade;

    @EJB
    private HistoriqueTraitementFacadeLocal historiqueTraitementFacade;

    @EJB
    private HistoriqueEtatsFacadeLocal historiqueEtatsFacade;

    @EJB
    private OffreFacadeLocal offreFacade;

    @EJB
    private InterlocuteurFacadeLocal interlocuteurFacade;

    @EJB
    private HistoriqueDevisFacadeLocal historiqueDevisFacade;

    @EJB
    private FactureFacadeLocal factureFacade;

    @EJB
    private UtilisateurHardisFacadeLocal utilisateurHardisFacade;

    @EJB
    private EchangeTelFacadeLocal echangeTelFacade;

    @EJB
    private DocumentFacadeLocal documentFacade;

    @EJB
    private DisponibiliteFacadeLocal disponibiliteFacade;

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
    public Adresse creerAdresse(int numRue, String nomRue, String ville, String CP, UtilisateurHardis hardis) {
        Adresse ad = adresseFacade.creerAdresse(numRue, nomRue, ville, CP);
        logsFacade.creerLogCreate(hardis, ad);
        return ad;
    }

    @Override
    public void modifierAdresse(long idad, int numRue, String nomRue, String ville, String CP, UtilisateurHardis hardis) {
        Adresse ad = adresseFacade.rechercheAdresse(idad);
        adresseFacade.modifAdresse(ad, numRue, nomRue, ville, CP);
        logsFacade.creerLogUpdate(hardis, ad);
    }
    
    @Override
    public void supprimerAdresse(long idad, UtilisateurHardis hardis) {
        Adresse ad = adresseFacade.rechercheAdresse(idad);
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
    public Agence creerAgence(String NomAgence, UtilisateurHardis hardis) {
        Agence ag =agenceFacade.creerAgence(NomAgence);
        logsFacade.creerLogCreate(hardis, ag);
        return ag;
    }

    @Override
    public void modifierAgence(long idagence, String nomagence, UtilisateurHardis hardis) {
        Agence agence = agenceFacade.rechercheAgence(idagence);
        agenceFacade.modifAgence(agence, nomagence);
        logsFacade.creerLogUpdate(hardis, agence);
    }
    
    @Override
    public void supprimerAgence(long idagence, UtilisateurHardis hardis) {
        Agence agence = agenceFacade.rechercheAgence(idagence);
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
    public Atelier creerAtelier(String NomAtelier, UtilisateurHardis hardis) {
        Atelier at = atelierFacade.creerAtelier(NomAtelier);
        logsFacade.creerLogCreate(hardis, at);
        return at;
    }

    @Override
    public void modifierAtelier(long idat, String nomatelier, UtilisateurHardis hardis) {
        Atelier at = atelierFacade.rechercheAtelier(idat);
        atelierFacade.modifAtelier(at, nomatelier);
        logsFacade.creerLogUpdate(hardis, at);
    }
    
    @Override
    public void supprimerAtelier(long idat, UtilisateurHardis hardis) {
        Atelier at = atelierFacade.rechercheAtelier(idat);
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
    public void affecterClient(long idclient, long identreprise, UtilisateurHardis hardis ) {
        Client client = clientFacade.rechercheClient(idclient);
        Entreprise entreprise = entrepriseFacade.rechercheEntrepriseParId(identreprise);
        clientFacade.majEntrepriseClient(client, entreprise);
        logsFacade.creerLogUpdate(hardis, client);
        
    }
    
    @Override
    public void modifierClient(long idclient, String Nom,String Prenom, int RGPD, Date dateRDGP, long identreprise, UtilisateurHardis hardis ) {
        Client client = clientFacade.rechercheClient(idclient);
        Entreprise entreprise = entrepriseFacade.rechercheEntrepriseParId(identreprise);
        clientFacade.modifClient(client, Nom, Prenom, RGPD, dateRDGP, entreprise);
        logsFacade.creerLogUpdate(hardis, client);
        
    }

    @Override
    public void modifierAgenceClient(long idclient,long idagence, UtilisateurHardis hardis ) {
        Client client = clientFacade.rechercheClient(idclient);
        Agence agence = agenceFacade.rechercheAgence(idagence);
        clientFacade.majAgenceClient(client, agence);
        logsFacade.creerLogUpdate(hardis, client);
        
    }
    
    @Override
    public void supprimerClient(long idclient,UtilisateurHardis hardis) {
        Client client = clientFacade.rechercheClient(idclient);
        clientFacade.supprimerClient(client);
        logsFacade.creerLogDelete(hardis, client);
    }

    @Override
    public void validerCompteClient(long idclient,UtilisateurHardis hardis) {
        Client client = clientFacade.rechercheClient(idclient);
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
    public void certifieClient(long idclient, UtilisateurHardis hardis) {
        Client client = clientFacade.rechercheClient(idclient);
        clientFacade.majCertif(client);
        logsFacade.creerLogResearch(hardis, client);
    }
    
    @Override
    public Communication creerCommunicationHardis(String message, long iddevis, UtilisateurHardis utilisateur) {
        Communication co = null;
        Devis devis = devisFacade.rechercheDevis(iddevis);
        List<Communication> liste = communicationFacade.rechercheCommunicationParDevis(devis);
        Date date = new Date();
        String qr = "R";
        if(liste.isEmpty()){
            int delai = 0;
            co =communicationFacade.creerCommunication(date, message, devis, utilisateur, qr, delai);
            logsFacade.creerLogCreate(utilisateur, co);
        }
        else{
            int delai = communicationFacade.calculerDelai(devis, date);
            co =communicationFacade.creerCommunication(date, message, devis, utilisateur, qr, delai);
            logsFacade.creerLogCreate(utilisateur, co);
            
        }
        return co;
    }

    @Override
    public void modifierCommunication(long idcommunication, Date date_comu, String message, long iddevis, long idutilisateur, UtilisateurHardis hardis) {
        Communication co = communicationFacade.rechercheCommunication(idcommunication);
        Devis devis = devisFacade.rechercheDevis(iddevis);
        UtilisateurHardis utilisateur = utilisateurHardisFacade.rechercheUtilisateurParId(idutilisateur);
        communicationFacade.modifCommunication(co, date_comu, message, devis, utilisateur);
        logsFacade.creerLogUpdate(hardis, co);
    }
    
    @Override
    public void supprimerCommunication(long idcommunication, UtilisateurHardis hardis) {
        Communication co = communicationFacade.rechercheCommunication(idcommunication);
        communicationFacade.supprimerCommunication(co);
        logsFacade.creerLogDelete(hardis, co);
    }
    
     @Override
    public List<Communication> rechercherCommunication(long iddevis,long idutilisateur, UtilisateurHardis hardis) {
        Devis devis = devisFacade.rechercheDevis(iddevis);
        UtilisateurHardis utilisateur = utilisateurHardisFacade.rechercheUtilisateurParId(idutilisateur);
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
    public void supprimerDevis(long iddevis, UtilisateurHardis hardis) {
        Devis devis = devisFacade.rechercheDevis(iddevis);
        devisFacade.supprimerDevis(devis);
        logsFacade.creerLogDelete(hardis, devis);
    }
    
     @Override
    public Devis rechercherDevis(long id, long idclient, UtilisateurHardis hardis) {
       Devis de = null;
       Client client = clientFacade.rechercheClient(idclient);
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
    public List<Devis> listDevis( ) {
        List<Devis> devis = devisFacade.listDevis();
        return devis;       
    }
    
    @Override
    public void modifieDevis(long iddevis, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,long idclient, long idagence, UtilisateurHardis hardis) {
        Devis devis = devisFacade.rechercheDevis(iddevis);
        Client client = clientFacade.rechercheClient(idclient);
        Agence ag = agenceFacade.rechercheAgence(idagence);
        devisFacade.modifDevis(devis, date_devis, date_intev_souh, facturation, montantdevis, motifrefus, saisielibre, statut, client, ag);
        logsFacade.creerLogUpdate(hardis, devis);
    }
    
    @Override
    public void devisFactures(long iddevis, String[] listidfacture, UtilisateurHardis hardis) {
        Devis devis = devisFacade.rechercheDevis(iddevis);
        List<Facture> listfacture = new ArrayList<>();
        for (String factures: listidfacture){
            Long idfacture = Long.valueOf(factures);
            Facture fact= factureFacade.rechercheFactParId(idfacture);
            listfacture.add(fact);
        }
        devisFacade.majFact(devis, listfacture);
        logsFacade.creerLogUpdate(hardis, devis);
    }
  
       
    @Override
    public void supprimerDevisNonStandard(long iddevis, UtilisateurHardis hardis) {
        DevisNonStandard devis = devisNonStandardFacade.rechercheDevisNonStandard(iddevis);
        devisNonStandardFacade.supprimerDevisNonStandard(devis);
        logsFacade.creerLogDelete(hardis, devis);
    }
    
     @Override
    public DevisNonStandard rechercherDevisNonStandart(long id, long idclient, UtilisateurHardis hardis) {
       Client client = clientFacade.rechercheClient(id);
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
    public void modifieDevisNonStandard(long iddevis, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,long idclient, long idagence, UtilisateurHardis hardis) {
        DevisNonStandard devis = devisNonStandardFacade.rechercheDevisNonStandard(iddevis);
        Client client = clientFacade.rechercheClient(idclient);
        Agence agence = agenceFacade.rechercheAgence(idagence);
        devisNonStandardFacade.modifDevis(devis, date_devis, date_intev_souh, facturation, montantdevis, motifrefus, saisielibre, statut, client, agence);
        logsFacade.creerLogUpdate(hardis, devis);
    }
    
    @Override
    public void accepterdevisNonStandard(long iddevis, String choix, UtilisateurHardis hardis) {
        DevisNonStandard devis = devisNonStandardFacade.rechercheDevisNonStandard(iddevis);
        devisNonStandardFacade.accepterRefuserDevisNS(devis, choix);
        logsFacade.creerLogUpdate(hardis, devis);
    }
  
    @Override
    public Disponibilite creerDisponibilite(Date dateDebut, Date dateFin, String libelle, UtilisateurHardis hardis) {
        Disponibilite di = disponibiliteFacade.creerDisponibilite(dateDebut, dateFin, libelle, hardis);
        logsFacade.creerLogCreate(hardis, di);
        return di;
    }

    @Override
    public void modifierDisponibilite(long iddisponibilite, Date dateDebut, Date dateFin, String libelle, UtilisateurHardis hardis) {
        Disponibilite disponibilite = disponibiliteFacade.rechercheDisponibilite(iddisponibilite);
        disponibiliteFacade.modifDisponibilite(disponibilite, dateDebut, dateFin, libelle, hardis);
        logsFacade.creerLogUpdate(hardis, disponibilite);
    }
    
    @Override
    public void supprimerDisponibilite(long iddisponibilite, UtilisateurHardis hardis) {
        Disponibilite disponibilite = disponibiliteFacade.rechercheDisponibilite(iddisponibilite);
        disponibiliteFacade.supprimerDisponibilite(disponibilite);
        logsFacade.creerLogDelete(hardis, disponibilite);
    }
    
     @Override
    public Disponibilite rechercherDisponibilite(long id, UtilisateurHardis hardis) {
        Disponibilite di = disponibiliteFacade.rechercheDisponibilite(id);
        logsFacade.creerLogResearch(hardis, di);
        return di;
    }
    
    @Override
    public List<Disponibilite> rechercherDisponibiliteParUtilisateur(long idutilisateur, UtilisateurHardis hardis) {
        UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurParId(idutilisateur);
        List<Disponibilite> di = disponibiliteFacade.rechercheDisponibiliteParUtilisateur(hardis);
        logsFacade.creerLogResearch(hardis, uh);
        return di;       
    }
    
    @Override
    public Document creerDocument(String descriptif, String liendoc, long idhistoriquedevis, UtilisateurHardis hardis) {
        HistoriqueDevis historiquedevis = historiqueDevisFacade.rechercheHistoriqueDevis(idhistoriquedevis);
        Document doc = documentFacade.creerDocument(descriptif, liendoc, historiquedevis);
        logsFacade.creerLogCreate(hardis, doc);
        return doc;
    }

    @Override
    public void modifierDocument(long iddocument, String descriptif, String liendoc, long idhistoriquedevis, UtilisateurHardis hardis) {
        Document entite = documentFacade.rechercheDocument(iddocument);
        HistoriqueDevis historiquedevis = historiqueDevisFacade.rechercheHistoriqueDevis(idhistoriquedevis);
        documentFacade.modifDocument(entite, descriptif, liendoc, historiquedevis);
        logsFacade.creerLogUpdate(hardis, entite);
    }
    
    @Override
    public void supprimerDocument(long iddocument, UtilisateurHardis hardis) {
        Document entite = documentFacade.rechercheDocument(iddocument);
        documentFacade.remove(entite);
        logsFacade.creerLogDelete(hardis, entite);
    }
    
     @Override
    public Document rechercherDocument(long id, UtilisateurHardis hardis) {
        Document doc = documentFacade.rechercheDocument(id);
        logsFacade.creerLogResearch(hardis, doc);
        return doc;
    }
    
    @Override
    public List<Document> rechercherDocumentParHistoriqueDevis(long idhistoriquedevis, UtilisateurHardis hardis) {
        HistoriqueDevis historiquedevis = historiqueDevisFacade.rechercheHistoriqueDevis(idhistoriquedevis);
        List<Document> doc = documentFacade.rechercheDocumentParHistorique(historiquedevis);
        logsFacade.creerLogResearch(hardis, historiquedevis);
        return doc;       
    }
    
    @Override
    public EchangeTel creerEchangeTel(String text, long iddevis, UtilisateurHardis hardis) {
        Devis devis = devisFacade.rechercheDevis(iddevis);
        EchangeTel et = echangeTelFacade.creerEchangeTel(text, devis, hardis);
        logsFacade.creerLogCreate(hardis, et);
        return et;
    }

    @Override
    public void modifierEchangeTel(long idechangetel, String text, long iddevis, UtilisateurHardis hardis) {
        EchangeTel entite = echangeTelFacade.rechercheEchangeTel(idechangetel);
        Devis devis = devisFacade.rechercheDevis(iddevis);
        echangeTelFacade.modifEchangeTel(entite, text, devis, hardis);
        logsFacade.creerLogUpdate(hardis, entite);
    }
    
    @Override
    public void supprimerEchangeTel(long idechangetel, UtilisateurHardis hardis) {
        EchangeTel entite = echangeTelFacade.rechercheEchangeTel(idechangetel);
        echangeTelFacade.remove(entite);
        logsFacade.creerLogDelete(hardis, entite);
    }
    
     @Override
    public EchangeTel rechercherEchangeTel(long id, UtilisateurHardis hardis) {
        EchangeTel et = echangeTelFacade.rechercheEchangeTel(id);
        logsFacade.creerLogResearch(hardis, et);
        return et;
    }
    
    @Override
    public List<EchangeTel> rechercherEchangeTelParUtilisateur(long idutilisateur, UtilisateurHardis hardis) {
        UtilisateurHardis interlocuteur = utilisateurHardisFacade.rechercheUtilisateurParId(idutilisateur);
        List<EchangeTel> et = echangeTelFacade.rechercheEchangeTelParUtilisateur(interlocuteur);
        logsFacade.creerLogResearch(hardis, interlocuteur);
        return et;       
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
    public Entreprise certifieEntreprise(long identreprise, UtilisateurHardis hardis) {
        Entreprise entreprise = entrepriseFacade.rechercheEntrepriseParId(identreprise);
        entrepriseFacade.majCertif(entreprise);
        logsFacade.creerLogUpdate(hardis, entreprise);
        return entreprise;
    }

    @Override
    public void modifieEntreprise(long identreprise, long idagence,  String nom, String[] listidinterlocuteurs , String codeContrat, String mdpEntreprise, long idadresse, String lienJustif, String numeroEnt, UtilisateurHardis hardis) {
        Entreprise entreprise = entrepriseFacade.rechercheEntrepriseParId(identreprise);
        Agence agence = agenceFacade.rechercheAgence(idagence);
        List<Entites.Interlocuteur> listinterlocuteurs= new ArrayList<>();
        for (String interlocuteur: listidinterlocuteurs){
            Long idinterlocuteurs = Long.valueOf(interlocuteur);
            Interlocuteur interloc= interlocuteurFacade.rechercheInterlocuteurParId(idinterlocuteurs);
            listinterlocuteurs.add(interloc);
        }
        Adresse adresse = adresseFacade.rechercheAdresse(idadresse);
        entrepriseFacade.modifEntreprise(entreprise, agence, nom, listinterlocuteurs, codeContrat, mdpEntreprise, adresse, lienJustif, numeroEnt);
        logsFacade.creerLogUpdate(hardis, entreprise);
    }
    
    @Override
    public Facture creerFacture(Date date, long iddevis, float montant, float montantDepass, String motifDepass, UtilisateurHardis hardis) {
        Devis devis = devisFacade.rechercheDevis(iddevis);
        Facture facture = factureFacade.creerFacture(date, devis, montant, montantDepass, motifDepass);
        logsFacade.creerLogCreate(hardis, facture);
        return facture;
    }

    @Override
    public void modifierFacture(long idfacture, Date date, long iddevis, float montant, long montantD, String motifD, UtilisateurHardis hardis) {
        Facture facture  = factureFacade.rechercheFactParId(idfacture);
        Devis devis = devisFacade.rechercheDevis(iddevis);
        factureFacade.modifFacture(facture, date, devis, montant, montantD, motifD);
        logsFacade.creerLogUpdate(hardis, facture);
    }
    
    @Override
    public void supprimerFacture(long idfacture, UtilisateurHardis hardis) {
        Facture facture  = factureFacade.rechercheFactParId(idfacture);
        factureFacade.remove(facture);
        logsFacade.creerLogDelete(hardis, facture);
    }
    
     @Override
    public Facture rechercherFacture(long id, UtilisateurHardis hardis) {
        Facture facture = factureFacade.rechercheFactParId(id);
        logsFacade.creerLogResearch(hardis, facture);
        return facture;
    }
    
    @Override
    public List<Facture> rechercherFactureParDevis(long iddevis, UtilisateurHardis hardis) {
        Devis devis = devisFacade.rechercheDevis(iddevis);
        List<Facture> facture = factureFacade.rechercheFactParDevis(devis);
        logsFacade.creerLogResearch(hardis, devis);
        return facture;       
    }
   
    @Override
    public Statut rechercherStatutParDevis(long iddevis, UtilisateurHardis hardis) {
        Devis devis = devisFacade.rechercheDevis(iddevis);
        Statut statut = devis.getStatut();
        logsFacade.creerLogResearch(hardis, devis);
        return statut;       
    }
    
    @Override
    public HistoriqueDevis creerHistoriqueDevis(long iddevis, Date datedebut, Date datefin, int numpropo, long idutilisateur,String[] listiddocument, UtilisateurHardis hardis) {
        Devis devis = devisFacade.rechercheDevis(iddevis);
        UtilisateurHardis utilisateur =utilisateurHardisFacade.rechercheUtilisateurParId(idutilisateur);
        List<Document> listdocuments= new ArrayList<>();
        for (String document: listiddocument){
            Long iddocument = Long.valueOf(document);
            Document docu= documentFacade.rechercheDocument(iddocument);
            listdocuments.add(docu);
        }
        HistoriqueDevis historiqueDevis = historiqueDevisFacade.creerHistoriqueDevis(devis, datedebut, datefin, numpropo, utilisateur, listdocuments);
        logsFacade.creerLogCreate(hardis, historiqueDevis);
        return historiqueDevis;
    }

    @Override
    public void modifierHistoriqueDevis(long idhistorique, Date datedebut, Date datefin, int numpropo, long idutilisateur,String[] listiddocument, UtilisateurHardis hardis) {
        HistoriqueDevis historiqueDevis  = historiqueDevisFacade.rechercheHistoriqueDevis(idhistorique);
        UtilisateurHardis utilisateur =utilisateurHardisFacade.rechercheUtilisateurParId(idutilisateur);
        List<Document> listdocuments= new ArrayList<>();
        for (String document: listiddocument){
            Long iddocument = Long.valueOf(document);
            Document docu= documentFacade.rechercheDocument(iddocument);
            listdocuments.add(docu);
        }
        historiqueDevisFacade.modifHistoriqueDevis(historiqueDevis, datedebut, datefin, numpropo, utilisateur, listdocuments);
        logsFacade.creerLogUpdate(hardis, historiqueDevis);
    }
    
    @Override
    public void supprimerHistoriqueDevis(long idhistorique, UtilisateurHardis hardis) {
        HistoriqueDevis historiqueDevis  = historiqueDevisFacade.rechercheHistoriqueDevis(idhistorique);
        historiqueDevisFacade.remove(historiqueDevis);
        logsFacade.creerLogDelete(hardis, historiqueDevis);
    }
    
     @Override
    public HistoriqueDevis rechercherHistoriqueDevis(long idhistorique, UtilisateurHardis hardis) {
        HistoriqueDevis historiqueDevis = historiqueDevisFacade.rechercheHistoriqueDevis(idhistorique);
        logsFacade.creerLogResearch(hardis, historiqueDevis);
        return historiqueDevis;
    }
    
    @Override
    public List<HistoriqueDevis> rechercherHistoriqueDevisParUtilisateur(long idutilisateur, UtilisateurHardis hardis) {
        UtilisateurHardis utilisateur =utilisateurHardisFacade.rechercheUtilisateurParId(idutilisateur);
        List<HistoriqueDevis> historiqueDevis = historiqueDevisFacade.rechercheHistoriqueDevisParUtilisateur(utilisateur);
        logsFacade.creerLogResearch(hardis, historiqueDevis);
        return historiqueDevis;       
    }
   
    @Override
    public List<HistoriqueDevis> listHistoriqueDevis( ) {
        List<HistoriqueDevis> historiqueDevis = historiqueDevisFacade.listHistoriqueDevis();
        return historiqueDevis;       
    }
    
    @Override
    public HistoriqueEtats creerHistoriqueEtats( Statut statut, long iddevis, UtilisateurHardis hardis) {
        Devis devis = devisFacade.rechercheDevis(iddevis);
        HistoriqueEtats historiqueEtats = historiqueEtatsFacade.creerHistoriqueEtats( statut, devis);
        logsFacade.creerLogCreate(hardis, historiqueEtats);
        return historiqueEtats;
    }

    @Override
    public void modifierHistoriqueEtats(long idhistorique, Date datemaj, Statut statut, long iddevis, UtilisateurHardis hardis) {
        HistoriqueEtats historiqueEtats  = historiqueEtatsFacade.rechercheHistoriqueEtats(idhistorique);
        Devis devis = devisFacade.rechercheDevis(iddevis);
        historiqueEtatsFacade.modifHistoriqueEtats(historiqueEtats, datemaj, statut, devis);
        logsFacade.creerLogUpdate(hardis, historiqueEtats);
    }
    
    @Override
    public void supprimerHistoriqueEtats(long idhistorique, UtilisateurHardis hardis) {
        HistoriqueEtats historiqueEtats  = historiqueEtatsFacade.rechercheHistoriqueEtats(idhistorique);
        historiqueEtatsFacade.remove(historiqueEtats);
        logsFacade.creerLogDelete(hardis, historiqueEtats);
    }
    
     @Override
    public HistoriqueEtats rechercherHistoriqueEtats(long idhistorique, UtilisateurHardis hardis) {
        HistoriqueEtats historiqueEtats  = historiqueEtatsFacade.rechercheHistoriqueEtats(idhistorique);
        logsFacade.creerLogResearch(hardis, historiqueEtats);
        return historiqueEtats;
    }
    
    @Override
    public List<HistoriqueEtats> rechercherHistoriqueEtatsParDevis(long iddevis, UtilisateurHardis hardis) {
        Devis devis = devisFacade.rechercheDevis(iddevis);
        List<HistoriqueEtats> historiqueEtats=historiqueEtatsFacade.rechercheHistoriqueEtatsParDevis(devis);
        logsFacade.creerLogResearch(hardis, historiqueEtats);
        return historiqueEtats;       
    }
    
    @Override
    public List<HistoriqueEtats> rechercherHistoriqueEtatsParService(long idservice, UtilisateurHardis hardis) {
        Service service =serviceFacade.rechercheServiceParId(idservice);
        List<HistoriqueEtats> historiqueEtats = historiqueEtatsFacade.rechercheHistoriqueEtatsParService(service);
        logsFacade.creerLogResearch(hardis, historiqueEtats);
        return historiqueEtats;       
    }
   
    @Override
    public List<HistoriqueEtats> listHistoriqueEtats( UtilisateurHardis hardis) {
        List<HistoriqueEtats> historiqueEtats = historiqueEtatsFacade.listHistoriqueEtats();
        logsFacade.creerLogResearch(hardis, historiqueEtats);
        return historiqueEtats;       
    }
    
    @Override
    public void creerHistoriqueTraitement(Date datedebut, Date datefin,TypeUtilisateur utilisateurcourant, long iddevis,long idconsultant, long idreflocal, long idvalidateur, UtilisateurHardis hardis) {
        Devis devis = devisFacade.rechercheDevis(iddevis);
        UtilisateurHardis consultant =utilisateurHardisFacade.rechercheUtilisateurParId(idconsultant);
        UtilisateurHardis reflocal =utilisateurHardisFacade.rechercheUtilisateurParId(idreflocal);
        UtilisateurHardis validateur =utilisateurHardisFacade.rechercheUtilisateurParId(idvalidateur);
        HistoriqueTraitement historiqueTraitement = historiqueTraitementFacade.creerHistoriqueTraitement(datedebut, datefin, utilisateurcourant, devis, consultant, reflocal, validateur);
        logsFacade.creerLogCreate(hardis, historiqueTraitement);
    }

    @Override
    public void modifierHistoriqueTraitement(long idhistorique, Date datedebut, Date datefin,TypeUtilisateur utilisateurcourant, long iddevis,long idconsultant, long idreflocal, long idvalidateur, UtilisateurHardis hardis) {
        HistoriqueTraitement historiqueTraitement  = historiqueTraitementFacade.rechercheHistoriqueTraitement(idhistorique);
        Devis devis = devisFacade.rechercheDevis(iddevis);
        UtilisateurHardis consultant =utilisateurHardisFacade.rechercheUtilisateurParId(idconsultant);
        UtilisateurHardis reflocal =utilisateurHardisFacade.rechercheUtilisateurParId(idreflocal);
        UtilisateurHardis validateur =utilisateurHardisFacade.rechercheUtilisateurParId(idvalidateur);
        historiqueTraitementFacade.modifHistoriqueTraitement(historiqueTraitement, datedebut, datefin, utilisateurcourant, devis, consultant, reflocal, validateur);
        logsFacade.creerLogUpdate(hardis, historiqueTraitement);
    }
    
    @Override
    public void supprimerHistoriqueTraitement(long idhistorique, UtilisateurHardis hardis) {
        HistoriqueTraitement historiqueTraitement  = historiqueTraitementFacade.rechercheHistoriqueTraitement(idhistorique);
        historiqueTraitementFacade.remove(historiqueTraitement);
        logsFacade.creerLogDelete(hardis, historiqueTraitement);
    }
    
     @Override
    public HistoriqueTraitement rechercherHistoriqueTraitement(long idhistorique, UtilisateurHardis hardis) {
        HistoriqueTraitement historiqueTraitement  = historiqueTraitementFacade.rechercheHistoriqueTraitement(idhistorique);
        logsFacade.creerLogResearch(hardis, historiqueTraitement);
        return historiqueTraitement;
    }
    
    @Override
    public List<HistoriqueTraitement> rechercherHistoriqueTraitementParDevis(long iddevis, UtilisateurHardis hardis) {
        Devis devis = devisFacade.rechercheDevis(iddevis);
        List<HistoriqueTraitement> historiqueTraitements = historiqueTraitementFacade.rechercheHistoriqueTraitementParDevis(devis);
        logsFacade.creerLogResearch(hardis, historiqueTraitements);
        return historiqueTraitements;       
    }
    
    @Override
    public List<HistoriqueTraitement> rechercherHistoriqueTraitementParConsultant(long idconsultant, UtilisateurHardis hardis) {
        UtilisateurHardis consultant =utilisateurHardisFacade.rechercheUtilisateurParId(idconsultant);
        List<HistoriqueTraitement> historiqueTraitements = historiqueTraitementFacade.rechercheHistoriqueTraitementParConsultant(consultant);
        logsFacade.creerLogResearch(hardis, historiqueTraitements);
        return historiqueTraitements;       
    }
   
    @Override
    public List<HistoriqueTraitement> rechercherHistoriqueTraitementParValidateur(long idvalidateur, UtilisateurHardis hardis) {
        UtilisateurHardis validateur =utilisateurHardisFacade.rechercheUtilisateurParId(idvalidateur);
        List<HistoriqueTraitement> historiqueTraitements = historiqueTraitementFacade.rechercheHistoriqueTraitementParValidateur(validateur);
        logsFacade.creerLogResearch(hardis, historiqueTraitements);
        return historiqueTraitements;       
    }
    
    @Override
    public List<HistoriqueTraitement> rechercherHistoriqueTraitementParUtilisateurCourant(UtilisateurHardis hardis) {
        List<HistoriqueTraitement> historiqueTraitements = historiqueTraitementFacade.rechercheHistoriqueTraitementParUtilisateurCourant(hardis);
        logsFacade.creerLogResearch(hardis, historiqueTraitements);
        return historiqueTraitements;       
    }
    
    @Override
    public List<HistoriqueTraitement> listHistoriqueTraitementEtats( UtilisateurHardis hardis) {
        List<HistoriqueTraitement> historiqueTraitements = historiqueTraitementFacade.listHistoriqueTraitement();
        logsFacade.creerLogResearch(hardis, historiqueTraitements);
        return historiqueTraitements;       
    }
    
    @Override
    public void creerInterlocuteur(String nom, String prenom, String fonction, String tel, long identreprise, UtilisateurHardis hardis) {
        Entreprise entreprise = entrepriseFacade.rechercheEntrepriseParId(identreprise);
        Interlocuteur interlocuteur = interlocuteurFacade.creerInterlocuteur(nom, prenom, fonction, tel, entreprise);
        logsFacade.creerLogCreate(hardis, interlocuteur);
    }

    @Override
    public void modifierInterlocuteur(long idinter, String nom, String prenom, String fonction, String tel, long identreprise, UtilisateurHardis hardis) {
        Interlocuteur interlocuteur = interlocuteurFacade.rechercheInterlocuteurParId(idinter);
        Entreprise entreprise = entrepriseFacade.rechercheEntrepriseParId(identreprise);
        interlocuteurFacade.modifierInterlocuteur(interlocuteur, nom, prenom, fonction, tel, entreprise);
        logsFacade.creerLogUpdate(hardis, interlocuteur);
    }
    
    @Override
    public void supprimerInterlocuteur(long idinter, UtilisateurHardis hardis) {
        Interlocuteur interlocuteur = interlocuteurFacade.rechercheInterlocuteurParId(idinter);
        interlocuteurFacade.supprimerInterlocuteur(idinter);
        logsFacade.creerLogDelete(hardis, interlocuteur);
    }
    
     @Override
    public Interlocuteur rechercherInterlocuteur(long idinter, UtilisateurHardis hardis) {
        Interlocuteur interlocuteur = interlocuteurFacade.rechercheInterlocuteurParId(idinter);
        logsFacade.creerLogResearch(hardis, interlocuteur);
        return interlocuteur;
    }
    
    @Override
    public Livrable creerLivrable(String nom, long idservice, UtilisateurHardis hardis) {
        Service service = serviceFacade.rechercheServiceParId(idservice);
        Livrable livrable = livrableFacade.creerLivrable(nom, service);
        logsFacade.creerLogCreate(hardis, livrable);
        return livrable;
    }

    @Override
    public void modifierLivrable(long idLivrable, String nom, long idservice, UtilisateurHardis hardis) {
        Service service = serviceFacade.rechercheServiceParId(idservice);
        Livrable livrable = livrableFacade.rechercheLivrableParId(idLivrable);
        livrableFacade.modifierLivrable(livrable, nom, service);
        logsFacade.creerLogUpdate(hardis, livrable);
    }
    
    @Override
    public void supprimerLivrable(long idLivrable, UtilisateurHardis hardis) {
        Livrable livrable = livrableFacade.rechercheLivrableParId(idLivrable);
        livrableFacade.supprimerLivrable(livrable);
        logsFacade.creerLogDelete(hardis, livrable);
    }
    
     @Override
    public Livrable rechercherLivrable(long idLivrable, UtilisateurHardis hardis) {
        Livrable livrable = livrableFacade.rechercheLivrableParId(idLivrable);
        logsFacade.creerLogResearch(hardis, livrable);
        return livrable;
    }
    
    @Override
    public Offre creerOffre(String lib,  UtilisateurHardis hardis) {
        Offre offre = offreFacade.creerOffre(lib);
        logsFacade.creerLogCreate(hardis, offre);
        return offre;
    }

    @Override
    public void modifierOffre(long idOffre, String lib, String[] listedesid, UtilisateurHardis hardis) {
        Offre offre = offreFacade.rechercheOffreParId(idOffre);
        List<Offre_Profil_Util_CV> listoffres_profils_cvs= new ArrayList<>();
        for (String offre_profil_cv: listedesid){
            Long idoffre_profil_cv = Long.valueOf(offre_profil_cv);
            Offre_Profil_Util_CV o_p_c= offre_Profil_Util_CVFacade.rechercheOPUCParId(idoffre_profil_cv);
            listoffres_profils_cvs.add(o_p_c);
        }
        offreFacade.modifierOffre(offre, listoffres_profils_cvs, lib);
        logsFacade.creerLogUpdate(hardis, offre);
    }
    
    @Override
    public void supprimerOffre(long idOffre, UtilisateurHardis hardis) {
        Offre offre = offreFacade.rechercheOffreParId(idOffre);
        offreFacade.supprimerOffre(idOffre);;
        logsFacade.creerLogDelete(hardis, offre);
    }
    
     @Override
    public Offre rechercherOffre(long idOffre, UtilisateurHardis hardis) {
        Offre offre = offreFacade.rechercheOffreParId(idOffre);
        logsFacade.creerLogResearch(hardis, offre);
        return offre;
    }
    
    @Override
    public Offre rechercherOffreLibelle(String libelleoffre, UtilisateurHardis hardis) {
        Offre offre = offreFacade.rechercheOffreParLibelle(libelleoffre);
        logsFacade.creerLogResearch(hardis, offre);
        return offre;
    }
    
    @Override
    public Offre_Profil_Util_CV creerOffre_Profil_Util_CV ( long idoffre, long idPM, long idutilisateur, String lienCV, UtilisateurHardis hardis) {
        Offre offre = offreFacade.rechercheOffreParId(idoffre);
        ProfilMetier profilmetier = profilMetierFacade.recherchePMParId(idPM);
        UtilisateurHardis utilisateur =utilisateurHardisFacade.rechercheUtilisateurParId(idPM);
        Offre_Profil_Util_CV offre_Profil_Util_CV = offre_Profil_Util_CVFacade.creerOPUC(offre, profilmetier, utilisateur, lienCV);
        logsFacade.creerLogCreate(hardis, offre_Profil_Util_CV);
        return offre_Profil_Util_CV;
    }

    @Override
    public void modifierOffre_Profil_Util_CV(long idoffre_Profil_Util_CV, long idoffre, long idPM, long idutilisateur, String lienCV, UtilisateurHardis hardis) {
        Offre_Profil_Util_CV offre_Profil_Util_CV = offre_Profil_Util_CVFacade.rechercheOPUCParId(idoffre_Profil_Util_CV);
        Offre offre = offreFacade.rechercheOffreParId(idoffre);
        ProfilMetier profilmetier = profilMetierFacade.recherchePMParId(idPM);
        UtilisateurHardis utilisateur =utilisateurHardisFacade.rechercheUtilisateurParId(idutilisateur);
        offre_Profil_Util_CVFacade.modifierOPUC(offre_Profil_Util_CV, offre, profilmetier, utilisateur, lienCV);
        logsFacade.creerLogUpdate(hardis, offre_Profil_Util_CV);
    }
    
    @Override
    public void supprimerOffre_Profil_Util_CV(long idoffre_Profil_Util_CV, UtilisateurHardis hardis) {
        Offre_Profil_Util_CV offre_Profil_Util_CV = offre_Profil_Util_CVFacade.rechercheOPUCParId(idoffre_Profil_Util_CV);
        offre_Profil_Util_CVFacade.supprimerOPUC(offre_Profil_Util_CV);
        logsFacade.creerLogDelete(hardis, offre_Profil_Util_CV);
    }
    
     @Override
    public Offre_Profil_Util_CV rechercherOffre_Profil_Util_CV(long idoffre_Profil_Util_CV, UtilisateurHardis hardis) {
        Offre_Profil_Util_CV offre_Profil_Util_CV = offre_Profil_Util_CVFacade.rechercheOPUCParId(idoffre_Profil_Util_CV);
        logsFacade.creerLogResearch(hardis, offre_Profil_Util_CV);
        return offre_Profil_Util_CV;
    }
    
    @Override
    public List<Offre_Profil_Util_CV> rechercherOffre_Profil_Util_CVParUtilisateur(long idutilisateur, UtilisateurHardis hardis) {
        UtilisateurHardis utilisateur =utilisateurHardisFacade.rechercheUtilisateurParId(idutilisateur);
        List<Offre_Profil_Util_CV> offre_Profil_Util_CV = offre_Profil_Util_CVFacade.rechercheOPUCParUtilisateur(utilisateur);
        logsFacade.creerLogResearch(hardis, offre_Profil_Util_CV);
        return offre_Profil_Util_CV;       
    }
    
    @Override
    public List<Offre_Profil_Util_CV> rechercherOffre_Profil_Util_CVParProfilMetier(long idprofilmetier, UtilisateurHardis hardis) {
        ProfilMetier profilmetier = profilMetierFacade.recherchePMParId(idprofilmetier);
        List<Offre_Profil_Util_CV> offre_Profil_Util_CV = offre_Profil_Util_CVFacade.rechercheOPUCParPM(profilmetier);
        logsFacade.creerLogResearch(hardis, offre_Profil_Util_CV);
        return offre_Profil_Util_CV;       
    }
    
    @Override
    public List<Offre_Profil_Util_CV> listHistoriqueOffre_Profil_Util_CV( UtilisateurHardis hardis) {
        List<Offre_Profil_Util_CV> offre_Profil_Util_CV = offre_Profil_Util_CVFacade.findAll();
        logsFacade.creerLogResearch(hardis, offre_Profil_Util_CV);
        return offre_Profil_Util_CV;       
    }
    
    @Override
    public ProfilMetier creerProfilMetier( NiveauHabilitation niveau, Expertise expertise, float plafond, String[] listedesid, UtilisateurHardis hardis) {
        List<Offre_Profil_Util_CV> listoffres_profils_cvs= new ArrayList<>();
        for (String offre_profil_cv: listedesid){
            Long idoffre_profil_cv = Long.valueOf(offre_profil_cv);
            Offre_Profil_Util_CV o_p_c= offre_Profil_Util_CVFacade.rechercheOPUCParId(idoffre_profil_cv);
            listoffres_profils_cvs.add(o_p_c);
        }
        ProfilMetier profilmetier = profilMetierFacade.creerProfilMetier(niveau, expertise, plafond, listoffres_profils_cvs);
        logsFacade.creerLogCreate(hardis, profilmetier);
        return profilmetier;
    }

    @Override
    public void modifierProfilMetier(long idPM, NiveauHabilitation niveau, Expertise expertise, float plafond, String[] listedesid, UtilisateurHardis hardis) {
        ProfilMetier profilmetier = profilMetierFacade.recherchePMParId(idPM);
        List<Offre_Profil_Util_CV> listoffres_profils_cvs= new ArrayList<>();
        for (String offre_profil_cv: listedesid){
            Long idoffre_profil_cv = Long.valueOf(offre_profil_cv);
            Offre_Profil_Util_CV o_p_c= offre_Profil_Util_CVFacade.rechercheOPUCParId(idoffre_profil_cv);
            listoffres_profils_cvs.add(o_p_c);
        }
        profilMetierFacade.modifierProfilMetier(profilmetier, niveau, expertise, plafond, listoffres_profils_cvs);
        logsFacade.creerLogUpdate(hardis, profilmetier);
    }
    
    @Override
    public void supprimerProfilMetier(long idPM, UtilisateurHardis hardis) {
        ProfilMetier profilmetier = profilMetierFacade.recherchePMParId(idPM);
        profilMetierFacade.supprimerProfilMetier(profilmetier);
        logsFacade.creerLogDelete(hardis, profilmetier);
    }
    
     @Override
    public ProfilMetier rechercherProfilMetier(long idPM, UtilisateurHardis hardis) {
        ProfilMetier profilmetier = profilMetierFacade.recherchePMParId(idPM);
        logsFacade.creerLogResearch(hardis, profilmetier);
        return profilmetier;
    }
    
    @Override
    public List<ProfilMetier> rechercherProfilMetierParExpertise(Expertise expertise, UtilisateurHardis hardis) {
        List<ProfilMetier> offre_Profil_Util_CV = profilMetierFacade.recherchePMParExpertise(expertise);
        logsFacade.creerLogResearch(hardis, offre_Profil_Util_CV);
        return offre_Profil_Util_CV;       
    }
    
    @Override
    public List<ProfilMetier> rechercherProfilMetierParHabilitation(NiveauHabilitation niveauhabilitation, UtilisateurHardis hardis) {
        List<ProfilMetier> offre_Profil_Util_CV = profilMetierFacade.recherchePMParHabilitation(niveauhabilitation);
        logsFacade.creerLogResearch(hardis, offre_Profil_Util_CV);
        return offre_Profil_Util_CV;       
    }
    
    @Override
    public List<ProfilMetier> listProfilMetier( UtilisateurHardis hardis) {
        List<ProfilMetier> profilMetier = profilMetierFacade.findAll();
        logsFacade.creerLogResearch(hardis, profilMetier);
        return profilMetier;       
    }
    
    @Override
    public Service creerService( String nomService, String descriptionService, LieuIntervention lieuInterv, long idoffre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS, UtilisateurHardis hardis) {
        Offre offre = offreFacade.rechercheOffreParId(idoffre);
        Service service = serviceFacade.creerService(nomService, descriptionService, lieuInterv, offre, cout, facturation, listeCond, delai, typeS);
        logsFacade.creerLogCreate(hardis, service);
        return service;
    }
    
    @Override
    public void modifieService(long idservice, String nomService, String descriptionService, LieuIntervention lieuInterv, long idoffre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS, UtilisateurHardis hardis) {
        Service service = serviceFacade.rechercheServiceParId(idservice);
        Offre offre = offreFacade.rechercheOffreParId(idoffre);
        serviceFacade.modifierService(service, nomService, descriptionService, lieuInterv, offre, cout, facturation, listeCond, delai, typeS);
        logsFacade.creerLogUpdate(hardis, service);
    }
    
     @Override
    public void supprimerService(long idservice ,UtilisateurHardis hardis) {
        Service service = serviceFacade.rechercheServiceParId(idservice);
        serviceFacade.supprimerService(service);
        logsFacade.creerLogCreate(hardis,service );
    }
    
    @Override
    public List<Service> rechercherServiceParOffre(long idoffre, UtilisateurHardis hardis) {
        Offre of = offreFacade.rechercheOffreParId(idoffre);
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
    
    @Override
    public List<Service> listService( ) {
        List<Service> service = serviceFacade.listServices();
        return service;       
    }
    
    @Override
    public ServiceStandard creerServiceStandard( String nomService, String descriptionService, LieuIntervention lieuInterv, long idoffre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS, String descPresta, float nbJS, float nbJC, float nbJJ, float nbHA, String[] listidlivrable, String[] listeidAtelier, float nbHS, UtilisateurHardis hardis) {
        List<Livrable> listlivrables= new ArrayList<>();
        for (String livrable: listidlivrable){
            Long idlivrable = Long.valueOf(livrable);
            Livrable livrebles= livrableFacade.rechercheLivrableParId(idlivrable);
            listlivrables.add(livrebles);
        }
        List<Atelier> listateliers= new ArrayList<>();
        for (String atelier: listeidAtelier){
            Long idatelier = Long.valueOf(atelier);
            Atelier ateliers= atelierFacade.rechercheAtelier(idatelier);
            listateliers.add(ateliers);
        }
        Offre offre = offreFacade.rechercheOffreParId(idoffre);
        ServiceStandard service = serviceStandardFacade.creerServiceStandard(nomService, descriptionService, lieuInterv, offre, cout, facturation, listeCond, delai, typeS, descPresta, nbJS, nbJC, nbJJ, nbHA, listlivrables, listateliers, nbHS);
        logsFacade.creerLogCreate(hardis, service);
        return service;
    }
    
    @Override
    public void modifieServiceStandard(long idServiceStandard, String nomService, String descriptionService, LieuIntervention lieuInterv, long idoffre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS, String descPresta, float nbJS, float nbJC, float nbJJ, float nbHA,String[] listidlivrable, String[] listeidAtelier, float nbHS, UtilisateurHardis hardis) {
        ServiceStandard service = serviceStandardFacade.rechercheServiceSParId(idServiceStandard);
        List<Livrable> listlivrables= new ArrayList<>();
        for (String livrable: listidlivrable){
            Long idlivrable = Long.valueOf(livrable);
            Livrable livrebles= livrableFacade.rechercheLivrableParId(idlivrable);
            listlivrables.add(livrebles);
        }
        List<Atelier> listateliers= new ArrayList<>();
        for (String atelier: listeidAtelier){
            Long idatelier = Long.valueOf(atelier);
            Atelier ateliers= atelierFacade.rechercheAtelier(idatelier);
            listateliers.add(ateliers);
        }
        Offre offre = offreFacade.rechercheOffreParId(idoffre);
        serviceStandardFacade.modifierServiceStandard(service, nomService, descriptionService, lieuInterv, offre, cout, facturation, listeCond, delai, typeS, descPresta, nbJS, nbJC, nbJJ, nbHA, listlivrables, listateliers, nbHS);
        logsFacade.creerLogUpdate(hardis, service);
    }
    
     @Override
    public void supprimerServiceStandard(long idServiceStandard ,UtilisateurHardis hardis) {
        ServiceStandard serviceStandard = serviceStandardFacade.rechercheServiceSParId(idServiceStandard);
        serviceStandardFacade.supprimerServiceStandard(serviceStandard);
        logsFacade.creerLogCreate(hardis,serviceStandard );
    }
    
    @Override
    public List<ServiceStandard> rechercherServiceStandardParOffre(long idoffre, UtilisateurHardis hardis) {
        Offre of = offreFacade.rechercheOffreParId(idoffre);
        List<ServiceStandard> se = serviceStandardFacade.rechercheServiceStandardParOffre(of);
        logsFacade.creerLogResearch(hardis, of);
        return se;       
    }
    
    @Override
    public ServiceStandard rechercherServiceStandardId(long id, UtilisateurHardis hardis) {
       ServiceStandard se = serviceStandardFacade.rechercheServiceSParId(id);
        logsFacade.creerLogResearch(hardis, se);
        return se;
    }
    
    @Override
    public List<ServiceStandard> listServiceStandard() {
        List<ServiceStandard> ServiceStandard = serviceStandardFacade.listServStandard();
        return ServiceStandard;       
    }
    
    @Override
    public UtilisateurHardis creerUtilisateurHardis( String nom, String prenom, String login, String mdp, ProfilTechnique profil, long idagence, UtilisateurHardis hardis) {
        Agence agence = agenceFacade.rechercheAgence(idagence);
        UtilisateurHardis utilisateur = utilisateurHardisFacade.creerUtilisateurH(nom, prenom, login, mdp, profil,agence);
        logsFacade.creerLogCreate(hardis, utilisateur);
        return utilisateur;
    }
    
    @Override
    public void modifieUtilisateurHardisMDP(long idutilisateur, String MDP, UtilisateurHardis hardis) {
        UtilisateurHardis utilisateur = utilisateurHardisFacade.rechercheUtilisateurParId(idutilisateur);
        utilisateurHardisFacade.modfiUtilisateurMDP(hardis, MDP);
        logsFacade.creerLogUpdate(hardis, utilisateur);
    }
    
    @Override
    public void modifieUtilisateurHardisQSRS(long idutilisateur,String QS, String RS, UtilisateurHardis hardis) {
        UtilisateurHardis utilisateur = utilisateurHardisFacade.rechercheUtilisateurParId(idutilisateur);
        utilisateurHardisFacade.modfiUtilisateurQSRS(hardis, QS, RS);
        logsFacade.creerLogUpdate(hardis, utilisateur);
    }
    
    @Override
    public void modifieUtilisateurHardis(long idutilisateur,String nom, String prenom, String login, String mdp, String questSecrete, String repSecrete, Date dateRGPD, int rgpd, ProfilTechnique profil, StatutUtilisateur statut, String lienCV, long idagence, UtilisateurHardis hardis) {
        UtilisateurHardis utilisateur = utilisateurHardisFacade.rechercheUtilisateurParId(idutilisateur);
        Agence agence = agenceFacade.rechercheAgence(idagence);
        utilisateurHardisFacade.modifUtilisateurHardis(hardis, nom, prenom, dateRGPD, rgpd, profil, statut, lienCV, agence);
        logsFacade.creerLogUpdate(hardis, utilisateur);
    }
    
     @Override
    public void supprimerUtilisateurHardis(long idutilisateur ,UtilisateurHardis hardis) {
        UtilisateurHardis utilisateur = utilisateurHardisFacade.rechercheUtilisateurParId(idutilisateur);
        utilisateurHardisFacade.SuppressionUtilisateur(utilisateur);
        logsFacade.creerLogCreate(hardis,utilisateur );
    }
    
    @Override
    public List<UtilisateurHardis> rechercherUtilisateurHardisParAgence(long idagence, UtilisateurHardis hardis) {
        Agence agence = agenceFacade.rechercheAgence(idagence);
        List<UtilisateurHardis> utilisateurHardis = utilisateurHardisFacade.rechercheUtilisateurHParAgence(agence);
        logsFacade.creerLogResearch(hardis, agence);
        return utilisateurHardis;       
    }
    
    @Override
    public UtilisateurHardis rechercherUtilisateurHardisParId(long id, UtilisateurHardis hardis) {
        UtilisateurHardis utilisateur = utilisateurHardisFacade.rechercheUtilisateurParId(id);
        logsFacade.creerLogResearch(hardis, utilisateur);
        return utilisateur;
    }
    
    @Override
    public UtilisateurHardis authentificationUtilisateurHardis(String login, String mdp) {
        UtilisateurHardis utilisateur = utilisateurHardisFacade.authentificationHardis(login, mdp);
        logsFacade.creerLogResearch(utilisateur, utilisateur);
        return utilisateur;
    }
    
    @Override
    public UtilisateurHardis rechercherUtilisateurHardisParLogin(String login, UtilisateurHardis hardis) {
        UtilisateurHardis utilisateur = utilisateurHardisFacade.rechercheUtilisateurParLogin(login);
        logsFacade.creerLogResearch(hardis, utilisateur);
        return utilisateur;
    }
    
    @Override
    public List<UtilisateurHardis> listUtilisateurHardis( UtilisateurHardis hardis) {
        List<UtilisateurHardis> utilisateur = utilisateurHardisFacade.listUHardis();
        logsFacade.creerLogResearch(hardis, hardis);
        return utilisateur;       
    }
    
    @Override
    public List<Agence> listAgence() {
        List<Agence> ad = agenceFacade.findAll();
        return ad;       
    }
    
    @Override
    public List<Offre> listOffre() {
        List<Offre> l = offreFacade.findAll();
        return l;       
    }
    
    @Override
    public List<Client> listClient() {
        List<Client> l = clientFacade.findAll();
        return l;       
    }
    
    @Override
    public List<Atelier> listAtelier() {
        List<Atelier> l = atelierFacade.findAll();
        return l;       
    }
    
    @Override
    public List<Livrable> listLivrable() {
        List<Livrable> l = livrableFacade.findAll();
        return l;       
    }
    
    @Override
    public List<Notification> getNotifsAdmin(UtilisateurHardis utlisateur) {
            return notificationFacade.rechercheNotifUtilisateur(utlisateur);
    }
    
}

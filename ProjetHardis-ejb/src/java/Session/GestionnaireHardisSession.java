/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entites.Agence;
import Entites.Client;
import Entites.Communication;
import Entites.Devis;

import Entites.Disponibilite;
import Entites.Facturation;
import Entites.Facture;
import Entites.Statut;
import Entites.UtilisateurHardis;
import Facades.AgenceFacadeLocal;
import Facades.ClientFacadeLocal;
import Facades.CommunicationFacadeLocal;
import Facades.ContactMailFacadeLocal;
import Facades.DevisFacadeLocal;

import Facades.DisponibiliteFacadeLocal;
import Facades.DocumentFacadeLocal;
import Facades.FactureFacadeLocal;
import Facades.LogsFacadeLocal;
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
public class GestionnaireHardisSession implements GestionnaireHardisSessionLocal {

    @EJB
    private FactureFacadeLocal factureFacade;

    @EJB
    private AgenceFacadeLocal agenceFacade;

    @EJB
    private ClientFacadeLocal clientFacade;

    @EJB
    private LogsFacadeLocal logsFacade;

    @EJB
    private UtilisateurHardisFacadeLocal utilisateurHardisFacade;

    @EJB
    private DocumentFacadeLocal documentFacade;

    @EJB
    private DisponibiliteFacadeLocal disponibiliteFacade;



    @EJB
    private DevisFacadeLocal devisFacade;

    @EJB
    private ContactMailFacadeLocal contactMailFacade;

    @EJB
    private CommunicationFacadeLocal communicationFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    @Override
    public void creerCommunicationHardis(String message, long iddevis, UtilisateurHardis utilisateur) {
        Devis devis = devisFacade.rechercheDevis(iddevis);
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
    public List<Devis> rechercherDevis(long id, long idclient, UtilisateurHardis hardis) {
       List<Devis> de = new ArrayList<>();
       Client client = clientFacade.rechercheClient(idclient);
        if (client!=null)
        {
            de = devisFacade.rechercheDevisParClient(client);
            logsFacade.creerLogResearch(hardis, de);
        }
        
      
        return de;
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
    public void creerDisponibilite(Date dateDebut, Date dateFin, String libelle, UtilisateurHardis hardis) {
        Disponibilite di = disponibiliteFacade.creerDisponibilite(dateDebut, dateFin, libelle, hardis);
        logsFacade.creerLogCreate(hardis, di);
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
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entites.Adresse;
import Entites.Agence;
import Entites.Atelier;
import Entites.Client;
import Entites.Communication;
import Entites.Devis;
import Entites.DevisNonStandard;
import Entites.Disponibilite;
import Entites.Document;
import Entites.EchangeTel;
import Entites.Entreprise;
import Entites.Facturation;
import Entites.FacturationFrais;
import Entites.Facture;
import Entites.HistoriqueDevis;
import Entites.HistoriqueEtats;
import Entites.LieuIntervention;
import Entites.Offre;
import Entites.Service;
import Entites.Statut;
import Entites.TypeService;
import Entites.UtilisateurHardis;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
 */
@Local
public interface AdministrateurHardisSessionLocal {
        
    void creerAdresse(int numRue, String nomRue, String ville, String CP, UtilisateurHardis hardis);
    
    void modifierAdresse(long idad, int numRue, String nomRue, String ville, String CP, UtilisateurHardis hardis);
    
    void supprimerAdresse(long idad, UtilisateurHardis hardis);
    
    Adresse rechercherAdresse(long id, UtilisateurHardis hardis);
    
    List<Adresse> rechercherAdresseParCP(String CP, UtilisateurHardis hardis);
    
    void creerAgence(String NomAgence, UtilisateurHardis hardis);

    void modifierAgence(long idagence, String nomagence, UtilisateurHardis hardis);
    
    void supprimerAgence(long idagence, UtilisateurHardis hardis);
    
    Agence rechercherAgence(long id, String nomAgence, UtilisateurHardis hardis);
        
    void creerAtelier(String NomAtelier, UtilisateurHardis hardis);
    
    void modifierAtelier(long idat, String nomatelier, UtilisateurHardis hardis);
    
    void supprimerAtelier(long idat, UtilisateurHardis hardis);
    
    Atelier rechercherAtelier(long id, String nomAtelier, UtilisateurHardis hardis);

    void affecterClient(long iidclient, long identreprise, UtilisateurHardis hardis);

    void supprimerClient(long idclient,UtilisateurHardis hardis);

    void validerCompteClient(long idclient,UtilisateurHardis hardis);
    //criar afecter agence au client
    Client rechercherClient(long id, String nomclient, String loginclient, UtilisateurHardis hardis);

    void certifieClient(long idclient, UtilisateurHardis hardis);
    
    void creerCommunicationHardis(String message, long iddevis, UtilisateurHardis utilisateur);

    void modifierCommunication(long idcommunication, Date date_comu, String message, long iddevis, long idutilisateur, UtilisateurHardis hardis);
    
    void supprimerCommunication(long idcommunication, UtilisateurHardis hardis);
    
    List<Communication> rechercherCommunication(long iddevis,long idutilisateur, UtilisateurHardis hardis);
    
    Communication rechercherCommunicationID(long id, UtilisateurHardis hardis);
        
    void supprimerDevis(long iddevis, UtilisateurHardis hardis);
    
    Devis rechercherDevis(long id, long idclient, UtilisateurHardis hardis);
    
    void modifieDevis(long iddevis, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,long idclient, long idagence, UtilisateurHardis hardis);
    
    void devisFactures(long iddevis, String[] listidfacture, UtilisateurHardis hardis);
    
    void accepterdevisNonStandard(long iddevis, String choix, UtilisateurHardis hardis);
    
    DevisNonStandard rechercherDevisNonStandart(long id, long idclient, UtilisateurHardis hardis);
    
    void supprimerDevisNonStandard(long iddevis, UtilisateurHardis hardis);
    
    void modifieDevisNonStandard(long iddevis, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,long idclient, long idagence, UtilisateurHardis hardis);
    
    void creerDisponibilite(Date dateDebut, Date dateFin, String libelle, UtilisateurHardis hardis);
    
    void modifierDisponibilite(long iddisponibilite, Date dateDebut, Date dateFin, String libelle, UtilisateurHardis hardis);
    
    void supprimerDisponibilite(long iddisponibilite, UtilisateurHardis hardis);
    
    Disponibilite rechercherDisponibilite(long id, UtilisateurHardis hardis);
    
    List<Disponibilite> rechercherDisponibiliteParUtilisateur(long idutilisateur, UtilisateurHardis hardis);
    
    void creerDocument(String descriptif, String liendoc, long idhistoriquedevis, UtilisateurHardis hardis);
    
    void modifierDocument(long iddocument, String descriptif, String liendoc, long idhistoriquedevis, UtilisateurHardis hardis);
    
    void supprimerDocument(long iddocument, UtilisateurHardis hardis);
    
    Document rechercherDocument(long id, UtilisateurHardis hardis);
    
    List<Document> rechercherDocumentParHistoriqueDevis(long idhistoriquedevis, UtilisateurHardis hardis);
    
    void creerEchangeTel(String text, long iddevis, long idutilisateur, UtilisateurHardis hardis);
    
    void modifierEchangeTel(long idechangetel, String text, long iddevis, UtilisateurHardis hardis);
    
    void supprimerEchangeTel(long idechangetel, UtilisateurHardis hardis);
    
    EchangeTel rechercherEchangeTel(long id, UtilisateurHardis hardis);
    
    List<EchangeTel> rechercherEchangeTelParUtilisateur(long utilisateur, UtilisateurHardis hardis);
    
    Entreprise rechercherEntreprise(long id, String siret, String nomentreprise, UtilisateurHardis hardis);
    
    void certifieEntreprise(long identreprise, UtilisateurHardis hardis);

    void modifieEntreprise(long identreprise,long aidgence,  String nom, String[] listidinterlocuteurs, String codeContrat, String mdpEntreprise, long idadresse, String lienJustif, String numeroEnt, UtilisateurHardis hardis);

    void creerFacture(Date date, long iddevis, float montant, float montantDepass, String motifDepass, UtilisateurHardis hardis);
    
    void modifierFacture(long idfacture, Date date, long iddevis, float montant, long montantD, String motifD, UtilisateurHardis hardis);
    
    void supprimerFacture(long idfacture, UtilisateurHardis hardis);
    
    Facture rechercherFacture(long id, UtilisateurHardis hardis);
    
    List<Facture> rechercherFactureParDevis(long iddevis, UtilisateurHardis hardis);
    
    void creerHistoriqueDevis(long iddevis, Date datedebut, Date datefin, int numpropo, long idutilisateur,String[] listiddocument, UtilisateurHardis hardis);
    
    void modifierHistoriqueDevis(long idhistorique, Date datedebut, Date datefin, int numpropo, long idutilisateur,String[] listiddocument, UtilisateurHardis hardis);
    
    void supprimerHistoriqueDevis(long idhistorique, UtilisateurHardis hardis);
    
    HistoriqueDevis rechercherHistoriqueDevis(long idhistorique, UtilisateurHardis hardis);
    
    List<HistoriqueDevis> rechercherHistoriqueDevisParUtilisateur(long idutilisateur, UtilisateurHardis hardis);
    
    List<HistoriqueDevis> listHistoriqueDevis( UtilisateurHardis hardis);
    
    void creerHistoriqueEtats(Date datemaj, Statut statut, long iddevis, UtilisateurHardis hardis);
    
    void modifierHistoriqueEtats(long idhistorique, Date datemaj, Statut statut, long iddevis, UtilisateurHardis hardis);
    
    void supprimerHistoriqueEtats(long idhistorique, UtilisateurHardis hardis);
    
    HistoriqueEtats rechercherHistoriqueEtats(long idhistorique, UtilisateurHardis hardis);
    
    List<HistoriqueEtats> rechercherHistoriqueEtatsParDevis(long iddevis, UtilisateurHardis hardis);
    
    List<HistoriqueEtats> rechercherHistoriqueEtatsParService(long idservice, UtilisateurHardis hardis);
    
    List<HistoriqueEtats> listHistoriqueEtats( UtilisateurHardis hardis);
     
    void modifieService(long idservice, String nomService, String descriptionService, LieuIntervention idlieuInterv, long idoffre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS, UtilisateurHardis hardis);
        
    void supprimerService(long idservice ,UtilisateurHardis hardis);
    
    List<Service> rechercherService(long idoffre, UtilisateurHardis hardis);
    
    Service rechercherServiceId(long id, UtilisateurHardis hardis);

}

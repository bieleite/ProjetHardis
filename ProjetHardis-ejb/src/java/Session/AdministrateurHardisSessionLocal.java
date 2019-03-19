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
import Entites.Expertise;
import Entites.Facturation;
import Entites.FacturationFrais;
import Entites.Facture;
import Entites.HistoriqueDevis;
import Entites.HistoriqueEtats;
import Entites.HistoriqueTraitement;
import Entites.Interlocuteur;
import Entites.LieuIntervention;
import Entites.Livrable;
import Entites.NiveauHabilitation;
import Entites.Notification;
import Entites.Offre;
import Entites.Offre_Profil_Util_CV;
import Entites.ProfilMetier;
import Entites.ProfilTechnique;
import Entites.Service;
import Entites.ServiceStandard;
import Entites.Statut;
import Entites.StatutUtilisateur;
import Entites.TypeService;
import Entites.TypeUtilisateur;
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
        
    Adresse creerAdresse(int numRue, String nomRue, String ville, String CP, UtilisateurHardis hardis);
    
    void modifierAdresse(long idad, int numRue, String nomRue, String ville, String CP, UtilisateurHardis hardis);
    
    void supprimerAdresse(long idad, UtilisateurHardis hardis);
    
    Adresse rechercherAdresse(long id, UtilisateurHardis hardis);
    
    List<Adresse> rechercherAdresseParCP(String CP, UtilisateurHardis hardis);
    
    Agence creerAgence(String NomAgence, UtilisateurHardis hardis);

    void modifierAgence(long idagence, String nomagence, UtilisateurHardis hardis);
    
    void supprimerAgence(long idagence, UtilisateurHardis hardis);
    
    Agence rechercherAgenceParId(long id);
    
    Agence rechercherAgence(long id, String nomAgence, UtilisateurHardis hardis);
        
    Atelier creerAtelier(String NomAtelier, UtilisateurHardis hardis);
    
    void modifierAtelier(long idat, String nomatelier, UtilisateurHardis hardis);
    
    void supprimerAtelier(long idat, UtilisateurHardis hardis);
    
    Atelier rechercherAtelier(long id, String nomAtelier, UtilisateurHardis hardis);

    void affecterClient(long iidclient, long identreprise, UtilisateurHardis hardis);

    void modifierClient(long idclient, String Nom,String Prenom, int RGPD, Date dateRDGP, long identreprise, UtilisateurHardis hardis );
    
    void modifierAgenceClient(long idclient,long idagence, UtilisateurHardis hardis );
    
    void supprimerClient(long idclient,UtilisateurHardis hardis);

    void validerCompteClient(long idclient,UtilisateurHardis hardis);

    Client rechercherClient(long id, String nomclient, String loginclient, UtilisateurHardis hardis);

    void certifieClient(long idclient, UtilisateurHardis hardis);
    
    Communication creerCommunicationHardis(String message, long iddevis, UtilisateurHardis utilisateur);

    void modifierCommunication(long idcommunication, Date date_comu, String message, long iddevis, long idutilisateur, UtilisateurHardis hardis);
    
    void supprimerCommunication(long idcommunication, UtilisateurHardis hardis);
    
    List<Communication> rechercherCommunication(long iddevis,long idutilisateur, UtilisateurHardis hardis);
    
    Communication rechercherCommunicationID(long id, UtilisateurHardis hardis);
        
    Statut rechercherStatutParDevis(long iddevis, UtilisateurHardis hardis);
    
    List<Devis> listDevis( );
    
    void supprimerDevis(long iddevis, UtilisateurHardis hardis);
    
    Devis rechercherDevis(long id, long idclient, UtilisateurHardis hardis);
    
    void modifieDevis(long iddevis, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,long idclient, long idagence, UtilisateurHardis hardis);
    
    void devisFactures(long iddevis, String[] listidfacture, UtilisateurHardis hardis);
    
    void accepterdevisNonStandard(long iddevis, String choix, UtilisateurHardis hardis);
    
    DevisNonStandard rechercherDevisNonStandart(long id, long idclient, UtilisateurHardis hardis);
    
    void supprimerDevisNonStandard(long iddevis, UtilisateurHardis hardis);
    
    void modifieDevisNonStandard(long iddevis, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,long idclient, long idagence, UtilisateurHardis hardis);
    
    Disponibilite creerDisponibilite(Date dateDebut, Date dateFin, String libelle, UtilisateurHardis hardis);
    
    void modifierDisponibilite(long iddisponibilite, Date dateDebut, Date dateFin, String libelle, UtilisateurHardis hardis);
    
    void supprimerDisponibilite(long iddisponibilite, UtilisateurHardis hardis);
    
    Disponibilite rechercherDisponibilite(long id, UtilisateurHardis hardis);
    
    List<Disponibilite> rechercherDisponibiliteParUtilisateur(long idutilisateur, UtilisateurHardis hardis);
    
    Document creerDocument(String descriptif, String liendoc, long idhistoriquedevis, UtilisateurHardis hardis, String typeD);
    
    void modifierDocument(long iddocument, String descriptif, String liendoc, long idhistoriquedevis, UtilisateurHardis hardis);
    
    void supprimerDocument(long iddocument, UtilisateurHardis hardis);
    
    Document rechercherDocument(long id, UtilisateurHardis hardis);
    
    List<Document> rechercherDocumentParHistoriqueDevis(long idhistoriquedevis, UtilisateurHardis hardis);
    
    EchangeTel creerEchangeTel(String text, long iddevis, UtilisateurHardis hardis);
    
    void modifierEchangeTel(long idechangetel, String text, long iddevis, UtilisateurHardis hardis);
    
    void supprimerEchangeTel(long idechangetel, UtilisateurHardis hardis);
    
    EchangeTel rechercherEchangeTel(long id, UtilisateurHardis hardis);
    
    List<EchangeTel> rechercherEchangeTelParUtilisateur(long utilisateur, UtilisateurHardis hardis);
    
    Entreprise rechercherEntreprise(long id, String siret, String nomentreprise, UtilisateurHardis hardis);
    
    Entreprise certifieEntreprise(long identreprise, UtilisateurHardis hardis);

    void modifieEntreprise(long identreprise,long aidgence,  String nom, String[] listidinterlocuteurs, String codeContrat, String mdpEntreprise, long idadresse, String lienJustif, String numeroEnt, UtilisateurHardis hardis);

    Facture creerFacture(Date date, long iddevis, float montant, float montantDepass, String motifDepass, UtilisateurHardis hardis);
    
    void modifierFacture(long idfacture, Date date, long iddevis, float montant, long montantD, String motifD, UtilisateurHardis hardis);
    
    void supprimerFacture(long idfacture, UtilisateurHardis hardis);
    
    Facture rechercherFacture(long id, UtilisateurHardis hardis);
    
    List<Facture> rechercherFactureParDevis(long iddevis, UtilisateurHardis hardis);
    
    HistoriqueDevis creerHistoriqueDevis(long iddevis, Date datedebut, Date datefin, int numpropo, long idutilisateur,String[] listiddocument, UtilisateurHardis hardis);
    
    void modifierHistoriqueDevis(long idhistorique, Date datedebut, Date datefin, int numpropo, long idutilisateur,String[] listiddocument, UtilisateurHardis hardis);
    
    void supprimerHistoriqueDevis(long idhistorique, UtilisateurHardis hardis);
    
    HistoriqueDevis rechercherHistoriqueDevis(long idhistorique, UtilisateurHardis hardis);
    
    List<HistoriqueDevis> rechercherHistoriqueDevisParUtilisateur(long idutilisateur, UtilisateurHardis hardis);
    
    List<HistoriqueDevis> listHistoriqueDevis( );
    
    HistoriqueEtats creerHistoriqueEtats( Statut statut, long iddevis, UtilisateurHardis hardis);
    
    void modifierHistoriqueEtats(long idhistorique, Date datemaj, Statut statut, long iddevis, UtilisateurHardis hardis);
    
    void supprimerHistoriqueEtats(long idhistorique, UtilisateurHardis hardis);
    
    HistoriqueEtats rechercherHistoriqueEtats(long idhistorique, UtilisateurHardis hardis);
    
    List<HistoriqueEtats> rechercherHistoriqueEtatsParDevis(long iddevis, UtilisateurHardis hardis);
    
    List<HistoriqueEtats> rechercherHistoriqueEtatsParService(long idservice, UtilisateurHardis hardis);
    
    List<HistoriqueEtats> listHistoriqueEtats( UtilisateurHardis hardis);
    
    void creerHistoriqueTraitement(Date datedebut, Date datefin,TypeUtilisateur utilisateurcourant, long iddevis,long idconsultant, long idreflocal, long idvalidateur, UtilisateurHardis hardis);
    
    void modifierHistoriqueTraitement(long idhistorique, Date datedebut, Date datefin,TypeUtilisateur utilisateurcourant, long iddevis,long idconsultant, long idreflocal, long idvalidateur, UtilisateurHardis hardis);
    
    void supprimerHistoriqueTraitement(long idhistorique, UtilisateurHardis hardis);
    
    HistoriqueTraitement rechercherHistoriqueTraitement(long idhistorique, UtilisateurHardis hardis);
    
    List<HistoriqueTraitement> rechercherHistoriqueTraitementParDevis(long iddevis, UtilisateurHardis hardis);
    
    List<HistoriqueTraitement> rechercherHistoriqueTraitementParConsultant(long idconsultant, UtilisateurHardis hardis);
    
    List<HistoriqueTraitement> rechercherHistoriqueTraitementParValidateur(long idvalidateur, UtilisateurHardis hardis);
    
    List<HistoriqueTraitement> rechercherHistoriqueTraitementParUtilisateurCourant(UtilisateurHardis hardis);
    
    List<HistoriqueTraitement> listHistoriqueTraitementEtats( UtilisateurHardis hardis);
    
    void creerInterlocuteur(String nom, String prenom, String fonction, String tel,  String email, long identreprise, UtilisateurHardis hardis);
    
    void modifierInterlocuteur(long idinter, String nom, String prenom, String fonction, String tel, long identreprise, UtilisateurHardis hardis);
    
    void supprimerInterlocuteur(long idinter, UtilisateurHardis hardis);
    
    Interlocuteur rechercherInterlocuteur(long idinter, UtilisateurHardis hardis);
    
    Livrable creerLivrable(String nom, UtilisateurHardis hardis);
    
    void modifierLivrable(long idLivrable, String nom, long idservice, UtilisateurHardis hardis);
    
    void supprimerLivrable(long idLivrable, UtilisateurHardis hardis);
    
    Livrable rechercherLivrable(long idLivrable, UtilisateurHardis hardis);
    
    Offre creerOffre(String lib, UtilisateurHardis hardis);
    
    void modifierOffre(long idOffre, String lib, String[] listedesid, UtilisateurHardis hardis);
    
    void supprimerOffre(long idOffre, UtilisateurHardis hardis);
    
    Offre rechercherOffre(long idOffre, UtilisateurHardis hardis);
    
    Offre rechercherOffreLibelle(String libelleoffre, UtilisateurHardis hardis);
    
    Offre_Profil_Util_CV creerOffre_Profil_Util_CV ( long idoffre, long idPM, long idutilisateur, String lienCV, UtilisateurHardis hardis, float prix);
    
    void modifierOffre_Profil_Util_CV(long idoffre_Profil_Util_CV, long idoffre, long idPM, long idutilisateur, String lienCV, UtilisateurHardis hardis);
    
    void supprimerOffre_Profil_Util_CV(long idoffre_Profil_Util_CV, UtilisateurHardis hardis);
    
    Offre_Profil_Util_CV rechercherOffre_Profil_Util_CV(long idoffre_Profil_Util_CV, UtilisateurHardis hardis);
    
    List<Offre_Profil_Util_CV> rechercherOffre_Profil_Util_CVParUtilisateur(long idutilisateur, UtilisateurHardis hardis);
    
    List<Offre_Profil_Util_CV> rechercherOffre_Profil_Util_CVParProfilMetier(long idprofilmetier, UtilisateurHardis hardis);
    
    List<Offre_Profil_Util_CV> listHistoriqueOffre_Profil_Util_CV( UtilisateurHardis hardis);
    
    ProfilMetier creerProfilMetier( NiveauHabilitation niveau, Expertise expertise, float plafond, String[] listedesid, UtilisateurHardis hardis);
    
    void modifierProfilMetier(long idPM, NiveauHabilitation niveau, Expertise expertise, float plafond, String[] listedesid, UtilisateurHardis hardis);
    
    void supprimerProfilMetier(long idPM, UtilisateurHardis hardis);
    
    ProfilMetier rechercherProfilMetier(long idPM, UtilisateurHardis hardis);
    
    List<ProfilMetier> rechercherProfilMetierParExpertise(Expertise expertise, UtilisateurHardis hardis);
    
    List<ProfilMetier> rechercherProfilMetierParHabilitation(NiveauHabilitation niveauhabilitation, UtilisateurHardis hardis);
        
    List<ProfilMetier> listProfilMetier( UtilisateurHardis hardis);
    
    Service creerService( String nomService, String descriptionService, LieuIntervention lieuInterv, long idoffre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS, UtilisateurHardis hardis);    
    
    void modifieService(long idservice, String nomService, String descriptionService, LieuIntervention idlieuInterv, long idoffre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS, UtilisateurHardis hardis);
        
    void supprimerService(long idservice ,UtilisateurHardis hardis);
    
    List<Service> rechercherServiceParOffre(long idoffre, UtilisateurHardis hardis);
    
    Service rechercherServiceId(long id, UtilisateurHardis hardis);
    
    List<Service> listService( );
    
    ServiceStandard creerServiceStandard( String nomService, String descriptionService, LieuIntervention lieuInterv, long idoffre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS, String descPresta, float nbJS, float nbJC, float nbJJ, float nbHA, String[] listidlivrable, String[] listeidAtelier, float nbHS, UtilisateurHardis hardis);
    
    void modifieServiceStandard(long idServiceStandard, String nomService, String descriptionService, LieuIntervention lieuInterv, long idoffre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS, String descPresta, float nbJS, float nbJC, float nbJJ, float nbHA,String[] listidlivrable, String[] listeidAtelier, float nbHS, UtilisateurHardis hardis);
        
    void supprimerServiceStandard(long idServiceStandard ,UtilisateurHardis hardis);
    
    List<ServiceStandard> rechercherServiceStandardParOffre(long idoffre, UtilisateurHardis hardis);
    
    ServiceStandard rechercherServiceStandardId(long id, UtilisateurHardis hardis);
    
    List<ServiceStandard> listServiceStandard( );
    
    UtilisateurHardis creerUtilisateurHardis( String nom, String prenom, String login, String mdp, ProfilTechnique profil, long idagence, UtilisateurHardis hardis);

    UtilisateurHardis modifieUtilisateurHardisMDP(UtilisateurHardis idutilisateur, String MDP);
    
    void modifieUtilisateurHardisQSRS(long idutilisateur,String QS, String RS, UtilisateurHardis hardis);
    
    void modifieUtilisateurHardis(long idutilisateur,String nom, String prenom, String login, String mdp, String questSecrete, String repSecrete, Date dateRGPD, int rgpd, ProfilTechnique profil, StatutUtilisateur statut, String lienCV, long idagence, UtilisateurHardis hardis);
       
    void supprimerUtilisateurHardis(long idutilisateur ,UtilisateurHardis hardis);
    
    List<UtilisateurHardis> rechercherUtilisateurHardisParAgence(long idagence, UtilisateurHardis hardis);
    
    UtilisateurHardis rechercherUtilisateurHardisParId(long id, UtilisateurHardis hardis);
    
    UtilisateurHardis authentificationUtilisateurHardis(String login, String mdp);
    
    UtilisateurHardis rechercherUtilisateurHardisParLogin(String login);
    
    List<UtilisateurHardis> listUtilisateurHardis( );
    
    List<Client> listClient();
    
    List<Client> listClientVisibles();
    
    List<Client> listClientNonVisibles();
    
    List<Client> listClientNonCertifies();
    
    List<Agence> listAgence();
    
    List<Offre> listOffre();
    
    List<Livrable> listLivrable();
    
    List<Atelier> listAtelier();
    
    List<Adresse> listAdresse() ;
    
    List<Notification> getNotifsAdmin(UtilisateurHardis utlisateur);
    
    UtilisateurHardis recupererUtilisateurHardisQSRS(String QS, String RS);

    Offre rechercheOffreParId(long id);
    
     List<Service> listServiceNonStandard( );
}

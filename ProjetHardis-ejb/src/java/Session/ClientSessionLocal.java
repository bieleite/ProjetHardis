/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entites.Adresse;
import Entites.Client;
import Entites.Communication;
import Entites.Devis;
import Entites.DevisNonStandard;
import Entites.Entreprise;
import Entites.Facture;
import Entites.Notification;
import Entites.Service;
import Entites.Statut;
import Entites.UtilisateurHardis;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
 */
@Local
public interface ClientSessionLocal {

    Client authentificationClient(String login, String mdp);

   void majEntreprise(long idCli, long idEnt);

    void demandeDevis(Date dateDevis, Date dateInterv, String infosC, long  idServ, String typeServ, long idCli);

    void accepterDevis(long idCli, long idDevis);



    void refuserDevis(long idCli, long idDevis);



    void poserQuestion(long idCli, long idDevis, String text);

    void payerFacture(long idF);

    void modifDateInterv(Date date, long idD, long idCli);

    List<Devis> afficherDevisClient(long idCli);

    List<Devis> afficherDevisStatut(long idCli, String statut);

    Client rechercheCliParLogin(String log);

    Client creerClient(String nom, String prenom, String login, String mdp, String cp, String qs, String rs);

    void deconnexion(long id);

    Entreprise rechercheEntrepriseParCodeEtMdp(String siret, String mdp);

    Entreprise rechercheEntrepriseParSiret(String siret);

    Entreprise creerEntreprise(String nom, String siret, int nrRue, String nomR, String ville, String cp);

    Adresse creerAdresse(int nrRue, String nomR, String ville, String cp);

    void creerNotif(long idU, String mess);

    List<Notification> getNotifsClient(long id);

    List<Service> recupServices();

    Service rechercheServiceParId(long id);

    Devis recupDevis(long id);

    List<Communication> rechercheCommDev(long id);

    void creerComm(String mess, long idD);

    String rechercheDocDevis(long id);

    Facture creerFacture(long id);

    void payerFactureFinale(long id);

    List<UtilisateurHardis> rechercheConsultantsOffre(long id);


    
}

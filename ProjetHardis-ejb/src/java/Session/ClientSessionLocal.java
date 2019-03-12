/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entites.Client;
import Entites.Devis;
import Entites.DevisNonStandard;
import Entites.Entreprise;
import Entites.Facture;
import Entites.Service;
import Entites.Statut;
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

    
}

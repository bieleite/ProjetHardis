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

   void majEntreprise(Client cli, Entreprise ent);

    void demandeDevis(Date dateDevis, Date dateInterv, String infosC, Service serv, Client cli);

    void accepterDevis(Client cli, Devis d);



    void refuserDevis(Client cli, Devis d);



    void poserQuestion(Client cli, String text, Devis d);

    void payerFacture(Facture f);

    void modifDateInterv(Date date, Devis d, Client cli);

    List<Devis> afficherDevisClient(Client cli);

    List<Devis> afficherDevisStatut(Client cli, Statut s);

    
}

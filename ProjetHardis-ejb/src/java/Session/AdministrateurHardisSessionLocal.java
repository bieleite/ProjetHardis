/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entites.Client;
import Entites.Entreprise;
import Entites.UtilisateurHardis;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
 */
@Local
public interface AdministrateurHardisSessionLocal {

    void affecterClient(Client client, Entreprise entreprise, UtilisateurHardis hardis);

    void effacerClient(Client client);
    
}

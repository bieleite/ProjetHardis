/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entites.Client;
import Entites.Devis;
import Entites.Service;
import Entites.ServiceStandard;
import Entites.UtilisateurHardis;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
 */
@Local
public interface VisualisateurHardisSessionLocal {

    List<Service> afficherServices();

    List<Client> afficherClients();

    List<Devis> afficherDevis();

    List<UtilisateurHardis> afficherUHardis();

    List<ServiceStandard> afficherServiceStandard();
    
}

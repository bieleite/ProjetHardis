/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entites.Action;
import Entites.Client;
import Entites.Entreprise;
import Entites.UtilisateurHardis;
import Facades.ClientFacadeLocal;
import Facades.LogsFacadeLocal;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author gabrielleite
 */
@Stateless
public class AdministrateurHardisSession implements AdministrateurHardisSessionLocal {

    @EJB
    private LogsFacadeLocal logsFacade;

    @EJB
    private ClientFacadeLocal clientFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void affecterClient(Client client, Entreprise entreprise, UtilisateurHardis hardis ) {
        clientFacade.majEntrepriseClient(client, entreprise);
        Date date = new Date();
        String libelle = "Affectation client: "+client+" dans l'entreprise: "+ entreprise;
        
        logsFacade.creerLog(Action.Create, date, libelle, hardis);
        
    }

    @Override
    public void effacerClient(Client client) {
        clientFacade.supprimerClient(client);
    }
    
    
    
    
    
    
    
}

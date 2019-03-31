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
import Facades.ClientFacadeLocal;
import Facades.DevisFacadeLocal;
import Facades.ServiceFacadeLocal;
import Facades.ServiceStandardFacadeLocal;
import Facades.UtilisateurHardisFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author gabrielleite
 */
@Stateless
public class VisualisateurHardisSession implements VisualisateurHardisSessionLocal {

    @EJB
    private ServiceStandardFacadeLocal serviceStandardFacade;

    @EJB
    private ServiceFacadeLocal serviceFacade;

    @EJB
    private UtilisateurHardisFacadeLocal utilisateurHardisFacade;

    @EJB
    private DevisFacadeLocal devisFacade;

    @EJB
    private ClientFacadeLocal clientFacade;

    @Override
    public List<Service> afficherServices() {
        return serviceFacade.listServices();
    }

    @Override
    public List<Client> afficherClientsActifs() {
        return clientFacade.afficherClientsActifs();
    }

    @Override
    public List<Devis> afficherDevis() {
        return devisFacade.listDevis();
    }

    @Override
    public List<UtilisateurHardis> afficherUHardis() {
        return utilisateurHardisFacade.listUHardis();
    }

    @Override
    public List<ServiceStandard> afficherServiceStandard() {
        return serviceStandardFacade.listServStandard();
    }

    @Override
    public UtilisateurHardis rechercheUParEmailHache(String email) {
        return utilisateurHardisFacade.rechercheParEmailHache(email);
    }

    @Override
    public void majInfosProfil(long id, String mdp, String qs, String rs) {
        UtilisateurHardis u = utilisateurHardisFacade.rechercheUtilisateurParId(id);
        utilisateurHardisFacade.modfiUtilisateurMDP(u, mdp);
        utilisateurHardisFacade.modfiUtilisateurQSRS(u, qs, rs);
    }

}

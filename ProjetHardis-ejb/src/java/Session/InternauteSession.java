/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entites.Adresse;
import Entites.Agence;
import Entites.Client;
import Entites.Entreprise;
import Entites.Offre;
import Entites.Service;
import Entites.ServiceStandard;
import Facades.AdresseFacadeLocal;
import Facades.ClientFacadeLocal;
import Facades.ContactMailFacadeLocal;
import Facades.EntrepriseFacadeLocal;
import Facades.OffreFacadeLocal;
import Facades.ServiceFacadeLocal;
import Facades.ServiceStandardFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author gabrielleite
 */
@Stateless
public class InternauteSession implements InternauteSessionLocal {

    @EJB
    private ServiceStandardFacadeLocal serviceStandardFacade;

    @EJB
    private ServiceFacadeLocal serviceFacade;

    @EJB
    private OffreFacadeLocal offreFacade;

    @EJB
    private ContactMailFacadeLocal contactMailFacade;

    @EJB
    private AdresseFacadeLocal adresseFacade;

    @EJB
    private EntrepriseFacadeLocal entrepriseFacade;

    @EJB
    private ClientFacadeLocal clientFacade;
    
    
    

    @Override
    public void CreerCompteInternaute(String Nom,String Prenom, String Login, String MDP, String QuestionSecrete, String ReponseSecrete, int RGPD, Date dateRDGP,  String cp) {

      clientFacade.creerClient(Nom, Prenom, Login, MDP, QuestionSecrete, ReponseSecrete, RGPD, dateRDGP, null, null, cp);

    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void ajouterEntrepriseAuClient(long idCli, long idEnt) {
        
        Client client = clientFacade.rechercheClient(idCli);
        Entreprise entreprise = entrepriseFacade.rechercheEntrepriseParId(idEnt);
        
        if(entreprise!=null){
            clientFacade.majEntrepriseClient(client, entreprise); // methode pour affecter entreprise au client
        }      
    }


    
    @Override
    public void ajouterEntrepriseAuClientParCode(Client client, String code) {
        Entreprise en = entrepriseFacade.rechercheEntrepriseParMDP(code);//modifier pour le bon methode
        if(en!=null){
            clientFacade.majEntrepriseClient(client, en);// methode pour affecter entreprise au client
        }
        else{
            System.out.println("Login existant");
        }
    }

    @Override
    public void contacterHardis(String mess, String email, String nom, String prenom, String tel, String sujet, String societe) {
        contactMailFacade.creerContactMail(nom, prenom, email, tel, sujet, mess,societe );
    }

    @Override
    public List<Offre> afficheOffres() {
      return   offreFacade.listOffres();
    }

    @Override
    public List<ServiceStandard> recupServicesSOffre(long idO) {
        Offre o = offreFacade.rechercheOffreParId(idO);
   
        return serviceStandardFacade.rechercheServiceStandardParOffre(o);
        
    }

    @Override
    public List<Service> recupServiceNSOffre(long idO) {
         Offre o = offreFacade.rechercheOffreParId(idO);
        return serviceFacade.rechercheServiceParOffre(o);
    }
    


    
    
    

    
    
}

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
import Facades.AdresseFacadeLocal;
import Facades.ClientFacadeLocal;
import Facades.ContactMailFacadeLocal;
import Facades.EntrepriseFacadeLocal;
import Facades.OffreFacadeLocal;
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
    public void creerEntreprise(String numero,String nom, List<Entites.Interlocuteur> interlocuteurs, String codeContrat, String mdpEntreprise, int nrRue, String nomR, String cp, String ville) {

      Adresse adresse =  adresseFacade.creerAdresse(nrRue, nomR, ville, cp);
      entrepriseFacade.creerEntreprise(numero, null, nom, interlocuteurs, codeContrat, mdpEntreprise, adresse, "");   
      
      
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
    public void contacterHardis(String mess, String email, String nom, String prenom, String tel, String sujet) {
        contactMailFacade.creerContactMail(nom, prenom, email, tel, sujet, mess);
    }

    @Override
    public List<Offre> afficheOffres() {
      return   offreFacade.listOffres();
    }


    
    

    
    
}

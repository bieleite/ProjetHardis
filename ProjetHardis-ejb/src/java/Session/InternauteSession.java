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
import Facades.AdresseFacadeLocal;
import Facades.ClientFacadeLocal;
import Facades.EntrepriseFacadeLocal;
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
    private AdresseFacadeLocal adresseFacade;

    @EJB
    private EntrepriseFacadeLocal entrepriseFacade;

    @EJB
    private ClientFacadeLocal clientFacade;
    
    
    

    @Override
    public void CreerCompteInternaute(String Nom,String Prenom, String Login, String MDP, String QuestionSecrete, String ReponseSecrete, int RGPD, Date dateRDGP,  String cp) {
        Client cv = clientFacade.rechercheClientParLogin(Login);
        if(cv!=null){
            System.out.println("Login existant");
        }
        else{

            clientFacade.creerClient(Nom, Prenom, Login, MDP, QuestionSecrete, ReponseSecrete, RGPD, dateRDGP, null, null, cp);
        }
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void ajouterEntrepriseAuClient(Client client, Entreprise entreprise) {
        Entreprise en = entrepriseFacade.rechercheEntrepriseParId(entreprise.getId());//modifier pour le bon methode
        if(en!=null){
            clientFacade.majEntrepriseClient(client, en); // methode pour affecter entreprise au client
        }
        else{
            System.out.println("Login existant");
        }
    }

    @Override
    public void creerEntreprise(String numero, Agence agence, String nom, List<Entites.Interlocuteur> interlocuteurs, String codeContrat, String mdpEntreprise, Adresse adresse, String lienJustif) {
        Entreprise en = entrepriseFacade.find(this);//
        if(en!=null){
            System.out.println("Login existant");
        }
        else{
            entrepriseFacade.creerEntreprise(numero, agence, nom, interlocuteurs, codeContrat, mdpEntreprise, adresse, lienJustif);
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
    
    
    
}

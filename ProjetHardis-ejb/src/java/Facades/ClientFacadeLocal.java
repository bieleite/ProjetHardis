/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Client;
import Entites.Entreprise;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
 */
@Local
public interface ClientFacadeLocal {

    void create(Client client);

    void edit(Client client);

    void remove(Client client);

    Client find(Object id);

    List<Client> findAll();

    List<Client> findRange(int[] range);

    int count();
    
    void creerClient(String Nom,String Prenom, String Login,String MDP,String QuestionSecrete, String ReponseSecrete,int RGPD, Date RDGP, Entreprise entreprise);
    
    List<Client> listClient();

    Client rechercheClient(Long id);
    
    Client rechercheClientParNom(String nom);
    
    Client rechercheClientParLogin(String nom);
    
    Client modfiClientNomPrenom(Client client, String Nom, String Prenom);
    
    Client modfiClientMDP(Client cl, String MDP);
    
    Client modfiClientQSRS(Client cl, String QS, String RS);
    
    Client SuppressionClient(Client cl);
    
    
}

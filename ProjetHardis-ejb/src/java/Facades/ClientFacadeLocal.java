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

 * @author anastasia.salari

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
    
    void modifClient(Client entite, String Nom,String Prenom, String Login, String MDP, String QuestionSecrete, String ReponseSecrete, int RGPD, Date dateRDGP, Entreprise entreprise);

    void creerClient(String Nom,String Prenom, String Login,String MDP,String QuestionSecrete, String ReponseSecrete,int RGPD, Date RDGP, Entreprise entreprise);
    
    List<Client> listClient();

    Client rechercheClient(Long id);
    
    Client rechercheClientParNom(String nom);
    
    Client rechercheClientParLogin(String nom);
    
    void modfiClientNomPrenom(Client client, String Nom, String Prenom);
    
    void modfiClientMDP(Client cl, String MDP);
    
    void modfiClientQSRS(Client cl, String QS, String RS);
    
    void SuppressionClient(Client cl);
    
    void supprimerClient(Client entite);


}

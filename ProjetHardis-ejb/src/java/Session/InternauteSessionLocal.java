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
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
 */
@Local
public interface InternauteSessionLocal {

    void CreerCompteInternaute(String Nom,String Prenom, String Login, String MDP, String QuestionSecrete, String ReponseSecrete, int RGPD, Date dateRDGP, String cp);

    
    void ajouterEntrepriseAuClient(long idCli, long idEnt);
    
    void ajouterEntrepriseAuClientParCode(Client client, String code);

    void contacterHardis(String mess, String email, String nom, String prenom, String tel, String sujet);

    List<Offre> afficheOffres();
    
}

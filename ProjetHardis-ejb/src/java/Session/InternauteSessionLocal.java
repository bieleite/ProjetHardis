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
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
 */
@Local
public interface InternauteSessionLocal {

    void CreerCompteInternaute(String Nom,String Prenom, String Login, String MDP, String QuestionSecrete, String ReponseSecrete, int RGPD, Date dateRDGP);

    void ajouterEntrepriseAuClient(Client client, String codesecrete);

    void creerEntreprise(String numero, Agence agence, String nom, List<Entites.Interlocuteur> interlocuteurs, String codeContrat, String mdpEntreprise, Adresse adresse, String lienJustif);
    
}

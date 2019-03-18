/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;


import Entites.Agence;
import Entites.Client;
import Entites.UtilisateurHardis;

import Entites.ProfilTechnique;
import Entites.StatutUtilisateur;
import Entites.UtilisateurHardis;
import java.util.Date;

import java.util.List;
import javax.ejb.Local;

/**
 *

 * @author gabrielleite

 * @author anastasia.salari

 */
@Local
public interface UtilisateurHardisFacadeLocal {

    void create(UtilisateurHardis utilisateurHardis);

    void edit(UtilisateurHardis utilisateurHardis);

    void remove(UtilisateurHardis utilisateurHardis);

    UtilisateurHardis find(Object id);

    List<UtilisateurHardis> findAll();

    List<UtilisateurHardis> findRange(int[] range);

    int count();

    UtilisateurHardis creerUtilisateurH(String nom, String prenom, String login, String mdp, ProfilTechnique profil, Agence agence);
    
    UtilisateurHardis rechercheUtilisateurParId(Long id);
       
    UtilisateurHardis rechercheUtilisateurParLogin(String login);
    
    UtilisateurHardis  modfiUtilisateurMDP(UtilisateurHardis cl, String MDP);
    
    void modfiUtilisateurQSRS(UtilisateurHardis cl, String QS, String RS);
    
    void modifUtilisateurHardis(UtilisateurHardis entite, String nom, String prenom, Date dateRGPD, int rgpd, ProfilTechnique profil, StatutUtilisateur statut, String lienCV,Agence agence);    
   
    void SuppressionUtilisateur(UtilisateurHardis utilisateur);

    UtilisateurHardis authentificationHardis(String log, String mdp);

    List<UtilisateurHardis> rechercheUtilisateurHParAgence(Agence agence);

    List<UtilisateurHardis> listUHardis();
    
    UtilisateurHardis rechercheUtilisateurParQS(String QS, String RS);


    
}

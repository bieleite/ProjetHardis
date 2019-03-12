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



    void creerUtilisateurH(String nom, String prenom, String login, String mdp, String questSecrete, String repSecrete, Date dateRGPD, int rgpd, ProfilTechnique profil, StatutUtilisateur statut, String lientCV, Agence agence);

    
    UtilisateurHardis rechercheUtilisateurParId(Long id);
       
    UtilisateurHardis rechercheUtilisateurParLogin(String login);
    
    void  modfiUtilisateurMDP(UtilisateurHardis cl, String MDP);
    
    void modfiUtilisateurQSRS(UtilisateurHardis cl, String QS, String RS);
    
void modifClient(UtilisateurHardis entite1, String nom, String prenom, Date dateRGPD, int rgpd, ProfilTechnique profil, StatutUtilisateur statut, String lienCV);    
   
void SuppressionUtilisateur(Long id);

    UtilisateurHardis authentificationHardis(String log, String mdp);

    List<UtilisateurHardis> rechercheUtilisateurHParAgence(Agence agence);

    List<UtilisateurHardis> listUHardis();
    
}

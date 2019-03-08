/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.ProfilTechnique;
import Entites.StatutUtilisateur;
import Entites.UtilisateurHardis;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
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

    void creerUtilisateurH(String nom, String prenom, String login, String mdp, String questSecrete, String repSecrete, Date dateRGPD, int rgpd, ProfilTechnique profil, StatutUtilisateur statut, String lientCV);
    
}

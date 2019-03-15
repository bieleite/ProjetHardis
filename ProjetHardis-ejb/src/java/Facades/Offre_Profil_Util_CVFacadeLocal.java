/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Offre;
import Entites.Offre_Profil_Util_CV;
import Entites.ProfilMetier;
import Entites.UtilisateurHardis;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author anastasia.salari
 */
@Local
public interface Offre_Profil_Util_CVFacadeLocal {

    void create(Offre_Profil_Util_CV offre_Profil_Util_CV);

    void edit(Offre_Profil_Util_CV offre_Profil_Util_CV);

    void remove(Offre_Profil_Util_CV offre_Profil_Util_CV);

    Offre_Profil_Util_CV find(Object id);

    List<Offre_Profil_Util_CV> findAll();

    List<Offre_Profil_Util_CV> findRange(int[] range);

    int count();

    Offre_Profil_Util_CV creerOPUC(Offre offre, ProfilMetier PM, UtilisateurHardis utilisateur, String lienCV);

    void modifierOPUC(Offre_Profil_Util_CV ob,Offre offre, ProfilMetier PM, UtilisateurHardis utilisateur, String lienCV);

    void supprimerOPUC(Offre_Profil_Util_CV opc);

    Offre_Profil_Util_CV rechercheOPUCParId(long id);

    List<Offre_Profil_Util_CV> rechercheOPUCParUtilisateur(UtilisateurHardis u);

    List<Offre_Profil_Util_CV> rechercheOPUCParPM(ProfilMetier pm);

    List<Offre_Profil_Util_CV> rechercheOPUCParOffre(Offre o);
    
}

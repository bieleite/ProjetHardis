/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

<<<<<<< HEAD
=======
import Entites.Expertise;
import Entites.NiveauHabilitation;
import Entites.Offre_Profil_Util_CV;
>>>>>>> origin/v3
import Entites.ProfilMetier;
import java.util.List;
import javax.ejb.Local;

/**
 *
<<<<<<< HEAD
 * @author gabrielleite
=======
 * @author anastasia.salari
>>>>>>> origin/v3
 */
@Local
public interface ProfilMetierFacadeLocal {

    void create(ProfilMetier profilMetier);

    void edit(ProfilMetier profilMetier);

    void remove(ProfilMetier profilMetier);

    ProfilMetier find(Object id);

    List<ProfilMetier> findAll();

    List<ProfilMetier> findRange(int[] range);

    int count();
<<<<<<< HEAD
=======

    void creerProfilMetier(NiveauHabilitation niveau, Expertise expertise, float plafond, List<Offre_Profil_Util_CV> offre_p_util_cv);

    void modifierProfilMetier(ProfilMetier PM,NiveauHabilitation niveau, Expertise expertise, float plafond, List<Offre_Profil_Util_CV> offre_p_util_cv );

    void supprimerProfilMetier(long id);

    ProfilMetier recherchePMParId(long id);
>>>>>>> origin/v3
    
}

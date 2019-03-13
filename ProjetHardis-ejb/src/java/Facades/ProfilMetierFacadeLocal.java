/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;



import Entites.Expertise;
import Entites.NiveauHabilitation;
import Entites.Offre_Profil_Util_CV;

import Entites.ProfilMetier;
import java.util.List;
import javax.ejb.Local;

/**
 *

 * @author gabrielleite

 * @author anastasia.salari

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

    ProfilMetier creerProfilMetier(NiveauHabilitation niveau, Expertise expertise, float plafond, List<Offre_Profil_Util_CV> offre_p_util_cv);

    void modifierProfilMetier(ProfilMetier PM,NiveauHabilitation niveau, Expertise expertise, float plafond, List<Offre_Profil_Util_CV> offre_p_util_cv );

    void supprimerProfilMetier(ProfilMetier profilMetier);

    ProfilMetier recherchePMParId(long id);

    List<ProfilMetier> recherchePMParExpertise(Expertise exp);

    List<ProfilMetier> recherchePMParHabilitation(NiveauHabilitation habi);

    
}

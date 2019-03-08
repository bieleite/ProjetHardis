/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Offre;
<<<<<<< HEAD
=======
import Entites.Offre_Profil_Util_CV;
>>>>>>> origin/v3
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
public interface OffreFacadeLocal {

    void create(Offre offre);

    void edit(Offre offre);

    void remove(Offre offre);

    Offre find(Object id);

    List<Offre> findAll();

    List<Offre> findRange(int[] range);

    int count();
<<<<<<< HEAD
=======

    void creerOffre(String lib, List<Offre_Profil_Util_CV> liste);

    void modifierOffre(Offre offre ,  List<Offre_Profil_Util_CV> liste, String lib );

    void supprimerOffre(long id);

    Offre rechercheOffreParId(long id);
>>>>>>> origin/v3
    
}

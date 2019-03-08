/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

<<<<<<< HEAD
=======
import Entites.Entreprise;
>>>>>>> origin/v3
import Entites.Interlocuteur;
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
public interface InterlocuteurFacadeLocal {

    void create(Interlocuteur interlocuteur);

    void edit(Interlocuteur interlocuteur);

    void remove(Interlocuteur interlocuteur);

    Interlocuteur find(Object id);

    List<Interlocuteur> findAll();

    List<Interlocuteur> findRange(int[] range);

    int count();
<<<<<<< HEAD
=======

    void creerInterlocuteur(String nom, String prenom, String fonction, String tel, Entreprise entreprise);

    void modifierInterlocuteur(Interlocuteur inter, String nom, String prenom, String fonction, String tel, Entreprise entreprise);
>>>>>>> origin/v3
    
}

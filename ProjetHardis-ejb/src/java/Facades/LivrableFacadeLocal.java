/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Livrable;
<<<<<<< HEAD
=======
import Entites.Service;
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
public interface LivrableFacadeLocal {

    void create(Livrable livrable);

    void edit(Livrable livrable);

    void remove(Livrable livrable);

    Livrable find(Object id);

    List<Livrable> findAll();

    List<Livrable> findRange(int[] range);

    int count();
<<<<<<< HEAD
=======

    void creerLivrable(String nom, Service service);

    void modifierLivrable(Livrable liv, String nom, Service service);
>>>>>>> origin/v3
    
}

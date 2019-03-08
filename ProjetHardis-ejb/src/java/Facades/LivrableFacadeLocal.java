/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Livrable;
import Entites.Service;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author anastasia.salari
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

    void creerLivrable(String nom, Service service);

    void modifierLivrable(Livrable liv, String nom, Service service);
    
}

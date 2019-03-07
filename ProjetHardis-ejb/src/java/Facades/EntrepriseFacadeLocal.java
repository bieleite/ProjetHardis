/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Entreprise;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
 */
@Local
public interface EntrepriseFacadeLocal {

    void create(Entreprise entreprise);

    void edit(Entreprise entreprise);

    void remove(Entreprise entreprise);

    Entreprise find(Object id);

    List<Entreprise> findAll();

    List<Entreprise> findRange(int[] range);

    int count();
    
}

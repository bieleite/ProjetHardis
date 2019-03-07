/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.UtilisateurHardis;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
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
    
}

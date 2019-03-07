/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.ProfilMetier;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
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
    
}

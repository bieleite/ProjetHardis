/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Offre_Profil_Util;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
 */
@Local
public interface Offre_Profil_UtilFacadeLocal {

    void create(Offre_Profil_Util offre_Profil_Util);

    void edit(Offre_Profil_Util offre_Profil_Util);

    void remove(Offre_Profil_Util offre_Profil_Util);

    Offre_Profil_Util find(Object id);

    List<Offre_Profil_Util> findAll();

    List<Offre_Profil_Util> findRange(int[] range);

    int count();
    
}

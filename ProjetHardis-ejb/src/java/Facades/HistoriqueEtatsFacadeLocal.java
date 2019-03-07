/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.HistoriqueEtats;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
 */
@Local
public interface HistoriqueEtatsFacadeLocal {

    void create(HistoriqueEtats historiqueEtats);

    void edit(HistoriqueEtats historiqueEtats);

    void remove(HistoriqueEtats historiqueEtats);

    HistoriqueEtats find(Object id);

    List<HistoriqueEtats> findAll();

    List<HistoriqueEtats> findRange(int[] range);

    int count();
    
}

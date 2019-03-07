/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.HistoriqueDevis;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
 */
@Local
public interface HistoriqueDevisFacadeLocal {

    void create(HistoriqueDevis historiqueDevis);

    void edit(HistoriqueDevis historiqueDevis);

    void remove(HistoriqueDevis historiqueDevis);

    HistoriqueDevis find(Object id);

    List<HistoriqueDevis> findAll();

    List<HistoriqueDevis> findRange(int[] range);

    int count();
    
}

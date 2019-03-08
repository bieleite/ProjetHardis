/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.HistoriqueTraitement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author anastasia.salari
 */
@Local
public interface HistoriqueTraitementFacadeLocal {

    void create(HistoriqueTraitement historiqueTraitement);

    void edit(HistoriqueTraitement historiqueTraitement);

    void remove(HistoriqueTraitement historiqueTraitement);

    HistoriqueTraitement find(Object id);

    List<HistoriqueTraitement> findAll();

    List<HistoriqueTraitement> findRange(int[] range);

    int count();
    
}

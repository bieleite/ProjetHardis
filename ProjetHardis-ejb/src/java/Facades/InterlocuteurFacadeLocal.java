/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Interlocuteur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
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
    
}

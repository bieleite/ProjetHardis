/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Communication;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author anastasia.salari
 */
@Local
public interface CommunicationFacadeLocal {

    void create(Communication communication);

    void edit(Communication communication);

    void remove(Communication communication);

    Communication find(Object id);

    List<Communication> findAll();

    List<Communication> findRange(int[] range);

    int count();
    
}

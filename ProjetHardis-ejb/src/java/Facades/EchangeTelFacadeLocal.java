/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.EchangeTel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author anastasia.salari
 */
@Local
public interface EchangeTelFacadeLocal {

    void create(EchangeTel echangeTel);

    void edit(EchangeTel echangeTel);

    void remove(EchangeTel echangeTel);

    EchangeTel find(Object id);

    List<EchangeTel> findAll();

    List<EchangeTel> findRange(int[] range);

    int count();
    
}

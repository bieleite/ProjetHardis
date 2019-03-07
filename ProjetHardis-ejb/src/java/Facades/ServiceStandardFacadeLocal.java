/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.ServiceStandard;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
 */
@Local
public interface ServiceStandardFacadeLocal {

    void create(ServiceStandard serviceStandard);

    void edit(ServiceStandard serviceStandard);

    void remove(ServiceStandard serviceStandard);

    ServiceStandard find(Object id);

    List<ServiceStandard> findAll();

    List<ServiceStandard> findRange(int[] range);

    int count();
    
}

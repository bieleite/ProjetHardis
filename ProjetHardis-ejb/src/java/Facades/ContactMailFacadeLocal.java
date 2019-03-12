/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.ContactMail;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 6170361
 */
@Local
public interface ContactMailFacadeLocal {

    void create(ContactMail contactMail);

    void edit(ContactMail contactMail);

    void remove(ContactMail contactMail);

    ContactMail find(Object id);

    List<ContactMail> findAll();

    List<ContactMail> findRange(int[] range);

    int count();
    
}

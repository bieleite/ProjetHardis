/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Client;
import Entites.Notification;
import Entites.Utilisateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 6170361
 */
@Local
public interface NotificationFacadeLocal {

    void create(Notification notification);

    void edit(Notification notification);

    void remove(Notification notification);

    Notification find(Object id);

    List<Notification> findAll();

    List<Notification> findRange(int[] range);

    int count();

    Notification creerNotif(Object uti, String mess);

    List<Notification> rechercheNotif(Client u);
    
}

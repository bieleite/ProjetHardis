/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

<<<<<<< HEAD
import Entites.Logs;
=======
import Entites.Action;
import Entites.Logs;
import Entites.UtilisateurHardis;
import java.util.Date;
>>>>>>> origin/v3
import java.util.List;
import javax.ejb.Local;

/**
 *
<<<<<<< HEAD
 * @author gabrielleite
=======
 * @author anastasia.salari
>>>>>>> origin/v3
 */
@Local
public interface LogsFacadeLocal {

    void create(Logs logs);

    void edit(Logs logs);

    void remove(Logs logs);

    Logs find(Object id);

    List<Logs> findAll();

    List<Logs> findRange(int[] range);

    int count();
<<<<<<< HEAD
=======

    void creerLog(Action action, Date date, String libelle, UtilisateurHardis util);
>>>>>>> origin/v3
    
}

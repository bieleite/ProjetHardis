/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;


import Entites.Logs;

import Entites.Action;
import Entites.Logs;
import Entites.Utilisateur;
import Entites.UtilisateurHardis;
import java.util.Date;

import java.util.List;
import javax.ejb.Local;

/**
 *

 * @author gabrielleite

 * @author anastasia.salari

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

    void creerLogResearch(Utilisateur util, Object o);
    
    void creerLogUpdate(Utilisateur util, Object o);
    
    void creerLogDelete(Utilisateur util, Object o);
    
    void creerLogCreate( Utilisateur util, Object o);

    void creerLog(Action action, Date date, String libelle, Utilisateur util);

    List<Logs> rechercheLogsParUtilisateur(Utilisateur u);

    List<Logs> rechercheLogsParAction(Action action);

    
}

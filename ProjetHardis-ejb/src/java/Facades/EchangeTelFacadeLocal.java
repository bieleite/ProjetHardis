/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;


import Entites.Devis;
import Entites.EchangeTel;
import Entites.UtilisateurHardis;

import Entites.EchangeTel;

import java.util.List;
import javax.ejb.Local;

/**
 *

 * @author gabrielleite

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
    

    void creerEchangeTel( String text, Devis devis, UtilisateurHardis interlocuteur);
    
    List<EchangeTel> listEchangeTel();
    
    EchangeTel rechercheEchangeTel(Long id);
    
    EchangeTel rechercheEchangeTelParUtilisateur(UtilisateurHardis utilisateur);
    
    EchangeTel modificationEchangeTel(EchangeTel et, String text, Devis devis, UtilisateurHardis interlocuteur);
    
    void modifEchangeTel(EchangeTel entite, String text, Devis devis, UtilisateurHardis interlocuteur);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Disponibilite;

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
public interface DisponibiliteFacadeLocal {

    void create(Disponibilite disponibilite);

    void edit(Disponibilite disponibilite);

    void remove(Disponibilite disponibilite);

    Disponibilite find(Object id);

    List<Disponibilite> findAll();

    List<Disponibilite> findRange(int[] range);

    int count();
    

    void creerDisponibilite( Date dateDebut, Date dateFin, String libelle, UtilisateurHardis utilisateur);
    
    List<Disponibilite> listDisponibilite();

    Disponibilite rechercheDisponibilite(Long id);
    
    Disponibilite rechercheDisponibiliteParUtilisateur(UtilisateurHardis utilisateur);
    
    Disponibilite modifDisponibilite(Disponibilite di, Date dateDebut, Date dateFin, String libelle, UtilisateurHardis utilisateur);
    
    


}

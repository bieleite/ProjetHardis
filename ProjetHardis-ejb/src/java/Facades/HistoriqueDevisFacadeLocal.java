/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Document;
import Entites.HistoriqueDevis;

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
public interface HistoriqueDevisFacadeLocal {

    void create(HistoriqueDevis historiqueDevis);

    void edit(HistoriqueDevis historiqueDevis);

    void remove(HistoriqueDevis historiqueDevis);

    HistoriqueDevis find(Object id);

    List<HistoriqueDevis> findAll();

    List<HistoriqueDevis> findRange(int[] range);

    int count();
   
    void creerHistoriqueDevis( Date datedebut, Date datefin, int numpropo, UtilisateurHardis utilisateur,List<Document> doc);
    
    List<HistoriqueDevis> listHistoriqueDevis();
    
    HistoriqueDevis rechercheHistoriqueDevis(Long id);
    
    List<HistoriqueDevis> rechercheHistoriqueDevisParUtilisateur(UtilisateurHardis utilisateur);
    
        
    void modifHistoriqueDevis(HistoriqueDevis entite, Date datedebut, Date datefin, int numpropo, UtilisateurHardis utilisateur,List<Document> doc);



}

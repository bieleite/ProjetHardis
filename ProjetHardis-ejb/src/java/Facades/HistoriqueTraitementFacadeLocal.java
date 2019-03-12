/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;


import Entites.Devis;
import Entites.HistoriqueTraitement;
import Entites.TypeUtilisateur;
import Entites.UtilisateurHardis;
import java.util.Date;

import Entites.HistoriqueTraitement;

import java.util.List;
import javax.ejb.Local;

/**
 *

 * @author gabrielleite

 * @author anastasia.salari

 */
@Local
public interface HistoriqueTraitementFacadeLocal {

    void create(HistoriqueTraitement historiqueTraitement);

    void edit(HistoriqueTraitement historiqueTraitement);

    void remove(HistoriqueTraitement historiqueTraitement);

    HistoriqueTraitement find(Object id);

    List<HistoriqueTraitement> findAll();

    List<HistoriqueTraitement> findRange(int[] range);

    int count();
    
    HistoriqueTraitement creerHistoriqueTraitement( Date datedebut, Date datefin,TypeUtilisateur utilisateurcourant, Devis devis,UtilisateurHardis consultant, UtilisateurHardis reflocal, UtilisateurHardis validateur );
    
    List<HistoriqueTraitement> listHistoriqueTraitement();
    
    HistoriqueTraitement rechercheHistoriqueTraitement(Long id);
    
    List<HistoriqueTraitement> rechercheHistoriqueTraitementParDevis(Devis devis);
    
     List<HistoriqueTraitement> rechercheHistoriqueTraitementParConsultant(UtilisateurHardis consultant);
    
     List<HistoriqueTraitement> rechercheHistoriqueTraitementParValidateur(UtilisateurHardis validateur);
    
     List<HistoriqueTraitement> rechercheHistoriqueTraitementParUtilisateurCourant(UtilisateurHardis utilisateurCourant);
    
    void modifHistoriqueTraitement(HistoriqueTraitement entite, Date datedebut, Date datefin,TypeUtilisateur utilisateurcourant, Devis devis,UtilisateurHardis consultant, UtilisateurHardis reflocal, UtilisateurHardis validateur);
}

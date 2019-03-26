/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Communication;

import Entites.Devis;
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
public interface CommunicationFacadeLocal {

    void create(Communication communication);

    void edit(Communication communication);

    void remove(Communication communication);

    Communication find(Object id);

    List<Communication> findAll();

    List<Communication> findRange(int[] range);

    int count();
    
    void modifCommunication(Communication entite, Date date_comu, String message, Devis devis, UtilisateurHardis utilisateur);

    Communication creerCommunication( Date date_comu, String message, Devis devis, UtilisateurHardis utilisateur, String QR, int delai );
    
    List<Communication> listCommunication();

    Communication rechercheCommunication(long id);
    
    List<Communication> rechercheCommunicationParUtilisateurHardis(UtilisateurHardis utilisateurHardis);
    
    Communication modifCommunicationQR(Communication co, String Typeqr);
    
    Communication modifCommunicationNom(Communication co, int delai);

    void supprimerCommunication(Communication entite);
    
    List<Communication> rechercheCommunicationParDevis(Devis devis);

    int calculerDelai(Devis devis, Date dtnow);

    float calculDelaiMDevis(Devis d);

    boolean verifDevisR(Devis d);

    int calculQSansRep(Devis d);
    
    
}

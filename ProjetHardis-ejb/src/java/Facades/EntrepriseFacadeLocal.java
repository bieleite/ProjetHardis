/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Adresse;
import Entites.Agence;
import Entites.Entreprise;
import java.util.List;
import javax.ejb.Local;

/**
 *

 * @author gabrielleite

 * @author anastasia.salari

 */
@Local
public interface EntrepriseFacadeLocal {

    void create(Entreprise entreprise);

    void edit(Entreprise entreprise);

    void remove(Entreprise entreprise);

    Entreprise find(Object id);

    List<Entreprise> findAll();

    List<Entreprise> findRange(int[] range);

    int count();

    void majCertif(Entreprise entreprise);

    Entreprise creerEntreprise(String numero, String nom, List<Entites.Interlocuteur> interlocuteurs, Adresse adresse);

    void modifEntreprise(Entreprise e, Agence agence,  String nom, List<Entites.Interlocuteur> interlocuteurs, String codeContrat, String mdpEntreprise, Adresse adresse, String lienJustif, String numeroEnt );

    Entreprise rechercheEntrepriseSiret(String siret);

    Entreprise rechercheEntrepriseParId(long id);

    Entreprise rechercheEntrepriseParNom(String nom);

    Entreprise rechercheEntrepriseParMDP(String mdp);

    void supprimerEntreprise(Entreprise e);

    String generateString(int n);

    String creerCodeContrat(long id);

    Entreprise rechercheEntrepriseSiretMdp(String siret, String mdp);
    
}

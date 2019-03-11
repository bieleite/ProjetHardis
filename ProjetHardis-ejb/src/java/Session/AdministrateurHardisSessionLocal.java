/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entites.Adresse;
import Entites.Agence;
import Entites.Atelier;
import Entites.Client;
import Entites.Devis;
import Entites.Entreprise;
import Entites.Facturation;
import Entites.FacturationFrais;
import Entites.Facture;
import Entites.LieuIntervention;
import Entites.Offre;
import Entites.Service;
import Entites.Statut;
import Entites.TypeService;
import Entites.UtilisateurHardis;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
 */
@Local
public interface AdministrateurHardisSessionLocal {
        
    void creerAdresse(int numRue, String nomRue, String ville, String CP, UtilisateurHardis hardis);
    
    void modifierAdresse(Adresse ad, int numRue, String nomRue, String ville, String CP, UtilisateurHardis hardis);
    
    void supprimerAdresse(Adresse ad, UtilisateurHardis hardis);
    
    Adresse rechercherAdresse(long id, UtilisateurHardis hardis);
    
    List<Adresse> rechercherAdresseParCP(String CP, UtilisateurHardis hardis);
    
    void creerAgence(String NomAgence, UtilisateurHardis hardis);

    void modifierAgence(Agence agence, String nomagence, UtilisateurHardis hardis);
    
    void supprimerAgence(Agence agence, UtilisateurHardis hardis);
    
    Agence rechercherAgence(long id, String nomAgence, UtilisateurHardis hardis);
        
    void creerAtelier(String NomAtelier, UtilisateurHardis hardis);
    
    void modifierAtelier(Atelier at, String nomatelier, UtilisateurHardis hardis);
    
    void supprimerAtelier(Atelier at, UtilisateurHardis hardis);
    
    Atelier rechercherAtelier(long id, String nomAtelier, UtilisateurHardis hardis);

    void affecterClient(Client client, Entreprise entreprise, UtilisateurHardis hardis);

    void supprimerClient(Client client,UtilisateurHardis hardis);

    void validerCompteClient(Client client,UtilisateurHardis hardis);

    Client rechercherClient(long id, String nomclient, String loginclient, UtilisateurHardis hardis);

    void certifieClient(Client client, UtilisateurHardis hardis);
        
    void supprimerDevis(Devis devis, UtilisateurHardis hardis);
    
    Devis rechercherService(long id, Client client, UtilisateurHardis hardis);
    
    void modifieDevis(Devis entite, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence ag, UtilisateurHardis hardis);
    
    void devisFactures(Devis devis, List<Facture> listfacture, UtilisateurHardis hardis);
    
    Entreprise rechercherEntreprise(long id, String siret, String nomentreprise, UtilisateurHardis hardis);
    
    void certifieEntreprise(Entreprise entreprise, UtilisateurHardis hardis);

    void modifieEntreprise(Entreprise entreprise,Agence agence,  String nom, List<Entites.Interlocuteur> interlocuteurs, String codeContrat, String mdpEntreprise, Adresse adresse, String lienJustif, String numeroEnt, UtilisateurHardis hardis);

    void modifieService(Service s, String nomService, String descriptionService, LieuIntervention lieuInterv, Offre offre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS, UtilisateurHardis hardis);
        
    void supprimerService(Service s ,UtilisateurHardis hardis);
    
    List<Service> rechercherService(Offre of, UtilisateurHardis hardis);
    
    Service rechercherServiceId(long id, UtilisateurHardis hardis);



}

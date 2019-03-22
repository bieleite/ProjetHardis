/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entites.Communication;
import Entites.Devis;
import Entites.DevisNonStandard;
import Entites.Disponibilite;
import Entites.Facturation;
import Entites.Statut;
import Entites.UtilisateurHardis;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
 */
@Local
public interface GestionnaireHardisSessionLocal {
      
    
    void creerCommunicationHardis(String message, long iddevis, UtilisateurHardis utilisateur);

    void modifierCommunication(long idcommunication, Date date_comu, String message, long iddevis, long idutilisateur, UtilisateurHardis hardis);
    
    void supprimerCommunication(long idcommunication, UtilisateurHardis hardis);
    
    List<Communication> rechercherCommunication(long iddevis,long idutilisateur, UtilisateurHardis hardis);
    
    Communication rechercherCommunicationID(long id, UtilisateurHardis hardis);
        
    void supprimerDevis(long iddevis, UtilisateurHardis hardis);
    
    List<Devis> rechercherDevis(long id, long idclient, UtilisateurHardis hardis);
    
    void modifieDevis(long iddevis, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,long idclient, long idagence, UtilisateurHardis hardis);
    
    void devisFactures(long iddevis, String[] listidfacture, UtilisateurHardis hardis);
    
    void accepterdevisNonStandard(long iddevis, String choix, UtilisateurHardis hardis);
    
    DevisNonStandard rechercherDevisNonStandart(long id, long idclient, UtilisateurHardis hardis);
    
    void supprimerDevisNonStandard(long iddevis, UtilisateurHardis hardis);
    
    void modifieDevisNonStandard(long iddevis, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,long idclient, long idagence, UtilisateurHardis hardis);
    
    void creerDisponibilite(Date dateDebut, Date dateFin, String libelle, UtilisateurHardis hardis);
    
    void modifierDisponibilite(long iddisponibilite, Date dateDebut, Date dateFin, String libelle, UtilisateurHardis hardis);
    
    void supprimerDisponibilite(long iddisponibilite, UtilisateurHardis hardis);
    
    Disponibilite rechercherDisponibilite(long id, UtilisateurHardis hardis);
    
}

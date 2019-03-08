/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;


import Entites.Agence;
import Entites.Client;
import Entites.DevisNonStandard;
import Entites.Facturation;
import Entites.Statut;
import java.util.Date;

import Entites.DevisNonStandard;

import java.util.List;
import javax.ejb.Local;

/**
 *

 * @author gabrielleite

 * @author anastasia.salari

 */
@Local
public interface DevisNonStandardFacadeLocal {

    void create(DevisNonStandard devisNonStandard);

    void edit(DevisNonStandard devisNonStandard);

    void remove(DevisNonStandard devisNonStandard);

    DevisNonStandard find(Object id);

    List<DevisNonStandard> findAll();

    List<DevisNonStandard> findRange(int[] range);

    int count();
    

    void creerDevisNonStandard( Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence agence);
    
    List<DevisNonStandard> listDevisNonStandard();

    DevisNonStandard rechercheDevisNonStandard(Long id);
    
    DevisNonStandard rechercheDevisNonStandardParClient(Client client);
    
    DevisNonStandard modifDevisNonStandard(DevisNonStandard de, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence ag);
    
    void modifDevis(DevisNonStandard entite, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence ag);


}

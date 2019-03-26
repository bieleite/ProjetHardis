/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;


import Entites.Agence;
import Entites.Client;
import Entites.Devis;
import Entites.Facturation;
import Entites.Statut;
import java.util.Date;

import Entites.Devis;
import Entites.HistoriqueDevis;
import Entites.HistoriqueEtats;
import Entites.HistoriqueTraitement;
import Entites.Service;
import Entites.TypeService;

import java.util.List;
import javax.ejb.Local;

/**
 *

 * @author gabrielleite

 * @author anastasia.salari

 */
@Local
public interface DevisFacadeLocal {

    void create(Devis devis);

    void edit(Devis devis);

    void remove(Devis devis);

    Devis find(Object id);

    List<Devis> findAll();

    List<Devis> findRange(int[] range);

    int count();
    
    void supprimerDevis(Devis entite);

    Devis creerDevis(TypeService type,Service service, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence agence);
    
    List<Devis> listDevis();

    Devis rechercheDevis(long id);
    
    List<Devis>  rechercheDevisParClient(Client client);
    
    void modifDateFinDevis(Devis entite ,Date date_devis);
    
    void modifDevis(Devis entite, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence ag);

    void majFact(Devis Devis, List<Entites.Facture> listeFact);

    void accepterRefuserDevis(Devis d, String choix);

    void modifDateInterv(Devis d, Date date);

    List<Devis> afficherDevisClient(Client cli);

    List<Devis> afficherDevisStatut(Client cli, Statut statut);

    void majHD(Devis d, HistoriqueDevis hd);

    void majHT(Devis d, HistoriqueTraitement ht);

    void majHE(Devis d, HistoriqueEtats he);

    String rechercheDocDevis(Devis d);

    void changeStatutPaye(String type, Devis d);

    void changerStatut(Devis d, String s);

    void majMontant(Devis d, float mont);

    void majMotifRefus(Devis d, String motif);

    void majDateDPresta(Devis d);

    void majNbJP(Devis d, float nbJ);


  
}

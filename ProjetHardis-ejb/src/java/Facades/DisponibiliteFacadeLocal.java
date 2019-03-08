/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Disponibilite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
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
    
    void creerDisponibilite( Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence agence);
    
    List<Disponibilite> listDisponibilite();

    Disponibilite rechercheDisponibilite(Long id);
    
    Disponibilite rechercheDisponibiliteParClient(Client client);
    
    Disponibilite modifDisponibilite(Disponibilite de, Date date_devis, Date date_intev_souh, Facturation facturation, float montantdevis, String motifrefus, String saisielibre, Statut statut ,Client client, Agence ag);
    
    
}

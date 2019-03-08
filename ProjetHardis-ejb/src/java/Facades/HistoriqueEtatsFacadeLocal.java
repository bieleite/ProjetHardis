/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Devis;
import Entites.HistoriqueEtats;
import Entites.Service;
import Entites.Statut;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gabrielleite
 */
@Local
public interface HistoriqueEtatsFacadeLocal {

    void create(HistoriqueEtats historiqueEtats);

    void edit(HistoriqueEtats historiqueEtats);

    void remove(HistoriqueEtats historiqueEtats);

    HistoriqueEtats find(Object id);

    List<HistoriqueEtats> findAll();

    List<HistoriqueEtats> findRange(int[] range);

    int count();
    
    void creerHistoriqueEtats( Date datemaj, Statut statut, Devis devis, Service service );
    
    List<HistoriqueEtats> listHistoriqueEtats();
    
    HistoriqueEtats rechercheHistoriqueEtats(Long id);
    
    HistoriqueEtats rechercheHistoriqueEtatsParDevis(Devis devis);
    
    HistoriqueEtats rechercheHistoriqueEtatsParService(Service service);
    
    HistoriqueEtats modifHistoriqueEtats(HistoriqueEtats he, Date datemaj, Statut statut, Devis devis, Service service);
    
    
}

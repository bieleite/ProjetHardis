/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Devis;
import Entites.Facture;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *

 * @author gabrielleite

 * @author anastasia.salari

 */
@Local
public interface FactureFacadeLocal {

    void create(Facture facture);

    void edit(Facture facture);

    void remove(Facture facture);

    Facture find(Object id);

    List<Facture> findAll();

    List<Facture> findRange(int[] range);

    int count();

    void creerFacture(Date date, Devis devis, float montant, float montantDepass, String motifDepass);

    void modifFacture(Facture fact, Date date, Devis devis, float montant, long montantD, String motifD);

    Facture rechercheFactParId(long id);

    List<Facture> rechercheFactParDevis(Devis d);

    void payerFacture(Facture f);
    
}

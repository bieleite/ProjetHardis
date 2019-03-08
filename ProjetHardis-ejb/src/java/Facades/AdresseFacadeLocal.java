/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Adresse;
import java.util.List;
import javax.ejb.Local;

/**
 *

 * @author gabrielleite

 * @author anastasia.salari

 */
@Local
public interface AdresseFacadeLocal {

    void create(Adresse adresse);

    void edit(Adresse adresse);

    void remove(Adresse adresse);

    Adresse find(Object id);

    List<Adresse> findAll();

    List<Adresse> findRange(int[] range);

    int count();
    
    void creerAdresse(int NumRue, String NomRue, String Ville, String CodePostal);
    
    List<Adresse> listAdresse();

    Adresse rechercheAdresse(Long id);
    
    Adresse rechercheAdresseParCP(String cp);
    
    Adresse modfiAdresseCP(Adresse adresse, String cp);
    
    Adresse modfiAdresseNomRue(Adresse adresse, String NomRue);
    
    Adresse modfiAdresseNumRue(Adresse adresse, int NumRue);
    
    Adresse modfiAdresseVille(Adresse adresse, String ville);
    
    


}

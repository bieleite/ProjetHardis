/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Atelier;
import java.util.List;
import javax.ejb.Local;

/**
 *

 * @author gabrielleite

 * @author anastasia.salari

 */
@Local
public interface AtelierFacadeLocal {

    void create(Atelier atelier);

    void edit(Atelier atelier);

    void remove(Atelier atelier);

    Atelier find(Object id);

    List<Atelier> findAll();

    List<Atelier> findRange(int[] range);

    int count();
    

    void creerAtelier(String NomAtelier);
    
    List<Atelier> listAtelier();

    Atelier rechercheAtelier(Long id);
    
    Atelier rechercheAtelierParNom(String NomAtelier);
    
    Atelier modifAtelierNom(Atelier adresse, String NomAtelier);
    


}

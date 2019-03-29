/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Offre;


import Entites.Offre_Profil_Util_CV;
import Entites.Service;
import Entites.ServiceStandard;

import java.util.List;
import javax.ejb.Local;

/**
 *

 * @author gabrielleite

 * @author anastasia.salari

 */
@Local
public interface OffreFacadeLocal {

    void create(Offre offre);

    void edit(Offre offre);

    void remove(Offre offre);

    Offre find(Object id);

    List<Offre> findAll();

    List<Offre> findRange(int[] range);

    int count();

    Offre creerOffre(String lib);

    void modifierOffre(Offre offre ,  List<Offre_Profil_Util_CV> liste, String lib );

    void supprimerOffre(long id);

    Offre rechercheOffreParId(long id);
    
    Offre rechercheOffreParLibelle(String lib);
    
    Offre rechercheOffreParService(Service lib);

    List<Offre> listOffres();
    
    Offre rechercheOffreParServiceS(ServiceStandard lib);

    
}

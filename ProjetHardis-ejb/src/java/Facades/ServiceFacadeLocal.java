/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;


import Entites.Service;

import Entites.FacturationFrais;
import Entites.LieuIntervention;
import Entites.Offre;
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
public interface ServiceFacadeLocal {

    void create(Service service);

    void edit(Service service);

    void remove(Service service);

    Service find(Object id);

    List<Service> findAll();

    List<Service> findRange(int[] range);

    int count();

    List<Service> rechercheServiceParOffre(Offre o);

    Service creerService(String nomService, String descriptionService, LieuIntervention lieuInterv, Offre offre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS);

    void modifierService(Service s, String nomService, String descriptionService, LieuIntervention lieuInterv, Offre offre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS);

    void supprimerService(Service service);

    Service rechercheServiceParId(long id);

    List<Service> listServices();
    
    List<Service> listServicesNonStandard();

    
}

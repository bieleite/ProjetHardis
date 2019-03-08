/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Atelier;
import Entites.FacturationFrais;
import Entites.LieuIntervention;
import Entites.Livrable;
import Entites.Offre;
import Entites.ServiceStandard;
import Entites.TypeService;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author anastasia.salari
 */
@Local
public interface ServiceStandardFacadeLocal {

    void create(ServiceStandard serviceStandard);

    void edit(ServiceStandard serviceStandard);

    void remove(ServiceStandard serviceStandard);

    ServiceStandard find(Object id);

    List<ServiceStandard> findAll();

    List<ServiceStandard> findRange(int[] range);

    int count();

    void creerServiceStandard(String nomService, String descriptionService, LieuIntervention lieuInterv, Offre offre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS, String descPresta, float nbJS, float nbJC, float nbJJ, float nbHA, List<Livrable> livrable, List<Atelier> listeA, float nbHS);

    void modifierServiceStandard(ServiceStandard s, String nomService, String descriptionService, LieuIntervention lieuInterv, Offre offre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS, String descPresta, float nbJS, float nbJC, float nbJJ, float nbHA, List<Livrable> livrable, List<Atelier> listeA, float nbHS);

    void supprimerServiceStandard(ServiceStandard serv);

    ServiceStandard rechercheServiceSParId(long id);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;



import Entites.Agence;
import Entites.FacturationFrais;
import Entites.HistoriqueEtats;
import Entites.LieuIntervention;
import Entites.Offre;
import Entites.Service;
import Entites.TypeService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gabrielleite



/**
 *
 * @author anastasia.salari

 */
@Stateless
public class ServiceFacade extends AbstractFacade<Service> implements ServiceFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceFacade() {
        super(Service.class);
    }



    @Override
    public Service creerService(String nomService, String descriptionService, LieuIntervention lieuInterv, Offre offre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS) {
        Service s = new Service();
        s.setConditionsContract(listeCond);
        s.setCoutService(cout);
        s.setDelaiRelance(delai);
        s.setDescriptionService(descriptionService);
        s.setFactFrais(facturation);
        s.setLieuInterv(lieuInterv);
        s.setNomService(nomService);
        s.setOffre(offre);
        s.setTypeService(typeS);
        em.persist(s);
        return s;
    }

    @Override
    public void modifierService(Service serv, String nomService, String descriptionService, LieuIntervention lieuInterv, Offre offre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS) {
       
        if (serv!=null){

       
        if (!"".equals(nomService))
        {
            serv.setNomService(nomService);
        }
        
         if (!"".equals(descriptionService))
        {
            serv.setDescriptionService(descriptionService);
        }
         
          if (lieuInterv!=null)
        {
            serv.setLieuInterv(lieuInterv);
        }
          
           if (offre!=null)
        {
            serv.setOffre(offre);
        }
           
            if (cout!=0)
        {
            serv.setCoutService(cout);
        }
            
             if (facturation!=null)
        {
            serv.setFactFrais(facturation);
        }
             
           if (delai!=0)
        {
            serv.setDelaiRelance(delai);
        }
           
             if (typeS!=null)
        {
            serv.setTypeService(typeS);
        }
       
            em.merge(serv);
        }
    
    }

    @Override
    public void supprimerService(Service service) {
        Service pm = null;
        Query requete = em.createQuery("SELECT p from Service as p where p.id=:id");
        requete.setParameter("id",service);     
        List<Service> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            pm  =  liste.get(0); 
            em.remove(pm);
        }
    }

    @Override
    public Service rechercheServiceParId(long id) {
          Service s = null;
        Query requete = em.createQuery("SELECT s from Service as s where s.id=:id");
        requete.setParameter("id",id);     
        List<Service> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            s =  (Service) liste.get(0); 
        }
        return s;
    }

    @Override
    public List<Service> rechercheServiceParOffre(Offre o) {
        Query requete = em.createQuery("SELECT s from Service as s where s.offre=:o");
        requete.setParameter("o",o);     
        List<Service> liste =  requete.getResultList();    
        return liste;
    }

    @Override
    public List<Service> listServices() {
        TypeService tps = TypeService.Non_Standard;
       String txt="SELECT ad FROM Service AS ad where ad.typeService=:o";
        Query req=getEntityManager().createQuery(txt);
        req.setParameter("o",tps);
        List<Service> result=req.getResultList();
        return result;
    }
    
    
    
    
    
    

    
}

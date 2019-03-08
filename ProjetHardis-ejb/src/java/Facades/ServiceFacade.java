/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;



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
    public void creerService(String nomService, String descriptionService, LieuIntervention lieuInterv, Offre offre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS) {
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
        s.setHistoriqueEtatss(new ArrayList<HistoriqueEtats>());
        em.persist(s);
    }

    @Override
    public void modifierService(Service s, String nomService, String descriptionService, LieuIntervention lieuInterv, Offre offre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS) {
        Service serv = null;
        Query requete = em.createQuery("SELECT s from Service as s where s.id=:id");
        requete.setParameter("id",s.getId());     
        List<Service> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            serv =   liste.get(0);
       
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
    public void supprimerService(Service serv) {
        Service s = null;
        Query requete = em.createQuery("SELECT s from Service as s where s.id=:id");
        requete.setParameter("id",serv.getId());     
        List<Service> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            s =   liste.get(0); 
            
            em.remove(s);
        }
    }

    @Override
    public Service rechercheServiceParId(long id) {
          Service s = null;
        Query requete = em.createQuery("SELECT s from Service as s where s.id=:id");
        requete.setParameter("id",id);     
        List<Service> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            s =   liste.get(0); 
        }
        return s;
    }
    
    
    
    
    
    

    
}

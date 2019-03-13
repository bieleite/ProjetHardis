/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;



import Entites.Atelier;
import Entites.FacturationFrais;
import Entites.HistoriqueEtats;
import Entites.LieuIntervention;
import Entites.Livrable;
import Entites.Offre;
import Entites.Service;
import Entites.ServiceStandard;
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
public class ServiceStandardFacade extends AbstractFacade<ServiceStandard> implements ServiceStandardFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceStandardFacade() {
        super(ServiceStandard.class);
    }



    @Override
    public ServiceStandard creerServiceStandard(String nomService, String descriptionService, LieuIntervention lieuInterv, Offre offre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS, String descPresta, float nbJS, float nbJC, float nbJJ, float nbHA, List<Livrable> livrable, List<Atelier> listeA, float nbHS) {
        ServiceStandard s = new ServiceStandard();
        s.setConditionsContract(listeCond);
        s.setCoutService(cout);
        s.setDelaiRelance(delai);
        s.setDescriptionService(descriptionService);
        s.setFactFrais(facturation);
        s.setLieuInterv(lieuInterv);
        s.setNomService(nomService);
        s.setOffre(offre);
        s.setTypeService(typeS);
        s.setAteliers(listeA);
        s.setLivrables(livrable);
        s.setNbreHeuresEntretien(nbHA);
        s.setNbreHeuresSupportTel(nbHS);
        s.setNbreJoursConsultantC(nbJC);
        s.setNbreJoursConsultantJ(nbJJ);
        s.setNbreJoursConsultantS(nbJS);
        em.persist(s);
        return s;
    }

    @Override
    public void modifierServiceStandard(ServiceStandard serv, String nomService, String descriptionService, LieuIntervention lieuInterv, Offre offre, float cout, FacturationFrais facturation, String listeCond, int delai, TypeService typeS, String descPresta, float nbJS, float nbJC, float nbJJ, float nbHA, List<Livrable> livrable, List<Atelier> listeA, float nbHS) {
   
    
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
       
          if (nbJS>=0)
        {
            serv.setNbreJoursConsultantS(nbJS);
        }
       
           if (nbJC>=0)
        {
            serv.setNbreJoursConsultantC(nbJC);
        }
       
            if (nbJJ>=0)
        {
            serv.setNbreJoursConsultantJ(nbJJ);
        }
       
           if (nbHA>=0)
        {
            serv.setNbreHeuresEntretien(nbHA);
        }
           
              if (nbHS>=0)
        {
            serv.setNbreHeuresSupportTel(nbHS);
        }
              
         if (livrable!=null)
        {
            serv.setLivrables(livrable);
        }
       
         
         if (listeA!=null)
        {
            serv.setAteliers(listeA);
        }
              
              
       em.merge(serv);
        
        }
    }

    @Override
    public void supprimerServiceStandard(ServiceStandard service) {
        
             ServiceStandard s = null;
        Query requete = em.createQuery("SELECT s from ServiceStandard as s where s.id=:id");
        requete.setParameter("id",service.getId());     
        List<ServiceStandard> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            s =   liste.get(0); 
            
            em.remove(s);
        }
    }

    @Override
    public ServiceStandard rechercheServiceSParId(long id) {
         ServiceStandard s = null;
        Query requete = em.createQuery("SELECT s from Service as s where s.id=:id");
        requete.setParameter("id",id);     
        List<ServiceStandard> liste =  requete.getResultList();
        if (!liste.isEmpty()){
            s = (ServiceStandard)  liste.get(0); 
        }
        return s;
    }

    @Override
    public List<ServiceStandard> rechercheServiceStandardParOffre(Offre o) {
       Query requete = em.createQuery("SELECT s from ServiceStandard as s where s.offre=:o");
        requete.setParameter("o",o);     
        List<ServiceStandard> liste =  requete.getResultList();    
        return liste;
    }

    @Override
    public List<ServiceStandard> listServStandard() {
        String txt="SELECT ad FROM ServiceStandard AS ad ";
        Query req=getEntityManager().createQuery(txt);
        List<ServiceStandard> result=req.getResultList();
        return result;
    }
    
    
    
    
    
    
    

    
}

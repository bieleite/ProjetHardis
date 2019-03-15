/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Document;

import Entites.HistoriqueDevis;
import Entites.TypeDoc;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gabrielleite

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author anastasia.salari

 */
@Stateless
public class DocumentFacade extends AbstractFacade<Document> implements DocumentFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    @Override
    public Document creerDocument( String descriptif, String liendoc, HistoriqueDevis historiquedevis, TypeDoc type) {
        Document doc = new Document();
        doc.setDescriptif(descriptif);
        doc.setHistoDevis(historiquedevis);
        doc.setLienDoc(liendoc);
        doc.setTypeDoc(type);
       
        em.persist(doc);
        return doc;
    }
    
    
    @Override
    public List<Document> listDocument() {
     
        String txt="SELECT co FROM Document AS co ";
        Query req=getEntityManager().createQuery(txt);
        List<Document> result=req.getResultList();
        return result;
    }

    @Override
    public Document rechercheDocument(long id) {
        Document co = null;        
        String txt = "SELECT co FROM Document AS co WHERE co.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);  
        List<Document> res = req.getResultList();
        if (res.size() >= 1)
        {
              co = (Document) res.get(0);
        }
        return co;
    }

    @Override
    public  List<Document> rechercheDocumentParHistorique(HistoriqueDevis historiquedevis) {       
        String txt = "SELECT doc FROM Document AS doc WHERE doc.histoDevis=:historiquedevis ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("historiquedevis",historiquedevis.getId() );
        List<Document> res = req.getResultList();
        return res;
    }
    

    
    @Override
    public  void modifDocument(Document entite, String descriptif, String liendoc, HistoriqueDevis historiquedevis) {       
       if (entite!=null){

       
            if (!"".equals(descriptif))
        {
            entite.setDescriptif(descriptif);
        }
            if (!"".equals(liendoc))
        {
            entite.setLienDoc(liendoc);
        }
            if (historiquedevis!=null)
        {
            entite.setHistoDevis(historiquedevis);
        }
            
            em.merge(entite);
        }
    }

    public DocumentFacade() {
        super(Document.class);
    }

    @Override
    public void majHD(Document d, HistoriqueDevis hd) {
        d.setHistoDevis(hd);
        em.merge(d);
    }
    
    
    
}

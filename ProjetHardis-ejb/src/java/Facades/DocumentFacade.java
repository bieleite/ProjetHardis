/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Document;
import Entites.HistoriqueDevis;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gabrielleite
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
    public void creerDocument( String descriptif, String liendoc, HistoriqueDevis historiquedevis) {
        Document doc = new Document();
        doc.setDescriptif(descriptif);
        doc.setHistoDevis(historiquedevis);
        doc.setLienDoc(liendoc);
        em.persist(doc);
    }
    @Override
    public List<Document> listDocument() {
        List<Document> co=null;
        String txt="SELECT co FROM Document AS co ";
        Query req=getEntityManager().createQuery(txt);
        List<Document> result=req.getResultList();
        return result;
    }

    @Override
    public Document rechercheDocument(Long id) {
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
    public  Document rechercheDocumentParHistorique(HistoriqueDevis historiquedevis) {
        Document doc = null;        
        String txt = "SELECT doc FROM Document AS doc WHERE doc.histoDevis=:historiquedevis ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("historiquedevis",historiquedevis.getId() );
        List<Document> res = req.getResultList();
        if (res.size() >= 1)
        {
              doc = (Document) res.get(0);
        }
        return doc;
    }
    
     @Override
    public  Document modifDocument(Document doc, String descriptif, String liendoc, HistoriqueDevis historiquedevis) {
               
        String txt = "SELECT doc FROM Document AS doc WHERE doc.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", doc.getId());
        List<Document> res = req.getResultList();
        if (res.size() >= 1)
        {
            doc.setDescriptif(descriptif);
            doc.setHistoDevis(historiquedevis);
            doc.setLienDoc(liendoc);
            em.merge(doc);
        }
        return doc;
    }
    
    public DocumentFacade() {
        super(Document.class);
    }
    
}

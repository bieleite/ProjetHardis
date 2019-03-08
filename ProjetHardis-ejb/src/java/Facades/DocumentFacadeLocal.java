/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Document;

import Entites.HistoriqueDevis;


import java.util.List;
import javax.ejb.Local;

/**
 *

 * @author gabrielleite

 * @author anastasia.salari

 */
@Local
public interface DocumentFacadeLocal {

    void create(Document document);

    void edit(Document document);

    void remove(Document document);

    Document find(Object id);

    List<Document> findAll();

    List<Document> findRange(int[] range);

    int count();
    

    void creerDocument( String descriptif, String liendoc, HistoriqueDevis historiquedevis);
    
    List<Document> listDocument();

    Document rechercheDocument(Long id);
    
    Document rechercheDocumentParHistorique(HistoriqueDevis historiquedevis);
    
    Document modificationDocument(Document doc, String descriptif, String liendoc, HistoriqueDevis historiquedevis);
    
    void modifDocument(Document entite, String descriptif, String liendoc, HistoriqueDevis historiquedevis);
    


}

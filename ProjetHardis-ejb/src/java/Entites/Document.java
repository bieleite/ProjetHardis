/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author anastasia.salari
 */
@Entity
public class Document implements Serializable {

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
       
    @ManyToOne
        private HistoriqueDevis histoDevis;

    /**
     * Get the value of histoDevis
     *
     * @return the value of histoDevis
     */
    public HistoriqueDevis getHistoDevis() {
        return histoDevis;
    }

    /**
     * Set the value of histoDevis
     *
     * @param histoDevis new value of histoDevis
     */
    public void setHistoDevis(HistoriqueDevis histoDevis) {
        this.histoDevis = histoDevis;
    }

    
    private String lienDoc;

    /**
     * Get the value of lienDoc
     *
     * @return the value of lienDoc
     */
    public String getLienDoc() {
        return lienDoc;
    }

    /**
     * Set the value of lienDoc
     *
     * @param lienDoc new value of lienDoc
     */
    public void setLienDoc(String lienDoc) {
        this.lienDoc = lienDoc;
    }

        private String descriptif;

    /**
     * Get the value of descriptif
     *
     * @return the value of descriptif
     */
    public String getDescriptif() {
        return descriptif;
    }

    /**
     * Set the value of descriptif
     *
     * @param descriptif new value of descriptif
     */
    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    private TypeDoc typeDoc;

    /**
     * Get the value of typeDoc
     *
     * @return the value of typeDoc
     */
    public TypeDoc getTypeDoc() {
        return typeDoc;
    }

    /**
     * Set the value of typeDoc
     *
     * @param typeDoc new value of typeDoc
     */
    public void setTypeDoc(TypeDoc typeDoc) {
        this.typeDoc = typeDoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.Document[ id=" + id + " ]";
    }
    
}

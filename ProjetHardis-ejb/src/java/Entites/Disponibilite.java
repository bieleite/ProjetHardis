/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author anastasia.salari
 */
@Entity
public class Disponibilite implements Serializable {

        private String libelleActivite;

    /**
     * Get the value of libelleActivite
     *
     * @return the value of libelleActivite
     */
    public String getLibelleActivite() {
        return libelleActivite;
    }

    /**
     * Set the value of libelleActivite
     *
     * @param libelleActivite new value of libelleActivite
     */
    public void setLibelleActivite(String libelleActivite) {
        this.libelleActivite = libelleActivite;
    }

    
    
        @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date dateFin;

    /**
     * Get the value of dateFin
     *
     * @return the value of dateFin
     */
    public Date getDateFin() {
        return dateFin;
    }

    /**
     * Set the value of dateFin
     *
     * @param dateFin new value of dateFin
     */
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    
        @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date dateDebut;

    /**
     * Get the value of dateDebut
     *
     * @return the value of dateDebut
     */
    public Date getDateDebut() {
        return dateDebut;
    }

    /**
     * Set the value of dateDebut
     *
     * @param dateDebut new value of dateDebut
     */
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disponibilite)) {
            return false;
        }
        Disponibilite other = (Disponibilite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.Disponibilite[ id=" + id + " ]";
    }
    
}

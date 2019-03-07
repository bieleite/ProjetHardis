/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

import java.io.Serializable;
import java.util.Date;
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
public class HistoriqueEtats implements Serializable {

    private static final long serialVersionUID = 1L;
    
        private Statut statut;

    /**
     * Get the value of statut
     *
     * @return the value of statut
     */
    public Statut getStatut() {
        return statut;
    }

    /**
     * Set the value of statut
     *
     * @param statut new value of statut
     */
    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    
        private Date dateMAJ;

    /**
     * Get the value of dateMAJ
     *
     * @return the value of dateMAJ
     */
    public Date getDateMAJ() {
        return dateMAJ;
    }

    /**
     * Set the value of dateMAJ
     *
     * @param dateMAJ new value of dateMAJ
     */
    public void setDateMAJ(Date dateMAJ) {
        this.dateMAJ = dateMAJ;
    }

    @ManyToOne
        private Devis devis;

    /**
     * Get the value of devis
     *
     * @return the value of devis
     */
    public Devis getDevis() {
        return devis;
    }

    /**
     * Set the value of devis
     *
     * @param devis new value of devis
     */
    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    
    @ManyToOne
    private Service service;

    /**
     * Get the value of service
     *
     * @return the value of service
     */
    public Service getService() {
        return service;
    }

    /**
     * Set the value of service
     *
     * @param service new value of service
     */
    public void setService(Service service) {
        this.service = service;
    }

    
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
        if (!(object instanceof HistoriqueEtats)) {
            return false;
        }
        HistoriqueEtats other = (HistoriqueEtats) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.Prestation[ id=" + id + " ]";
    }
    
}

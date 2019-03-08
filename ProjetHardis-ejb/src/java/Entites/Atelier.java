/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

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
public class Atelier implements Serializable {

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
    
    
        private String nomAtelier;

    /**
     * Get the value of nomAtelier
     *
     * @return the value of nomAtelier
     */
    public String getNomAtelier() {
        return nomAtelier;
    }

    /**
     * Set the value of nomAtelier
     *
     * @param nomAtelier new value of nomAtelier
     */
    public void setNomAtelier(String nomAtelier) {
        this.nomAtelier = nomAtelier;
    }

    @ManyToOne
        private ServiceStandard serviceStandard;

    /**
     * Get the value of serviceStandard
     *
     * @return the value of serviceStandard
     */
    public ServiceStandard getServiceStandard() {
        return serviceStandard;
    }

    /**
     * Set the value of serviceStandard
     *
     * @param serviceStandard new value of serviceStandard
     */
    public void setServiceStandard(ServiceStandard serviceStandard) {
        this.serviceStandard = serviceStandard;
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
        if (!(object instanceof Atelier)) {
            return false;
        }
        Atelier other = (Atelier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.Atelier[ id=" + id + " ]";
    }
    
}

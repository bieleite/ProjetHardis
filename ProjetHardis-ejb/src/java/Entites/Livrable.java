/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author anastasia.salari
 */
@Entity
public class Livrable implements Serializable {

 
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
    
        private String nomLivrable;

    /**
     * Get the value of nomLivrable
     *
     * @return the value of nomLivrable
     */
    public String getNomLivrable() {
        return nomLivrable;
    }

    /**
     * Set the value of nomLivrable
     *
     * @param nomLivrable new value of nomLivrable
     */
    public void setNomLivrable(String nomLivrable) {
        this.nomLivrable = nomLivrable;
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



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livrable)) {
            return false;
        }
        Livrable other = (Livrable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.Livrables[ id=" + id + " ]";
    }
    
}

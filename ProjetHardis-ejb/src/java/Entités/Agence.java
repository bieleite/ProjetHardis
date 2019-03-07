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
import javax.persistence.OneToOne;

/**
 *
 * @author anastasia.salari
 */
@Entity
public class Agence implements Serializable {

    @OneToOne(mappedBy = "agence")
    private Devis devis;

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
    
        private String nomAgence;

    /**
     * Get the value of nomAgence
     *
     * @return the value of nomAgence
     */
    public String getNomAgence() {
        return nomAgence;
    }

    /**
     * Set the value of nomAgence
     *
     * @param nomAgence new value of nomAgence
     */
    public void setNomAgence(String nomAgence) {
        this.nomAgence = nomAgence;
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
        if (!(object instanceof Agence)) {
            return false;
        }
        Agence other = (Agence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.Agence[ id=" + id + " ]";
    }
    
}

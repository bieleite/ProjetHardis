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

/**
 *
 * @author anastasia.salari
 */
@Entity
public class DevisNonStandard extends Devis implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
        private String lienPPT;

    /**
     * Get the value of lienPPT
     *
     * @return the value of lienPPT
     */
    public String getLienPPT() {
        return lienPPT;
    }

    /**
     * Set the value of lienPPT
     *
     * @param lienPPT new value of lienPPT
     */
    public void setLienPPT(String lienPPT) {
        this.lienPPT = lienPPT;
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
        if (!(object instanceof DevisNonStandard)) {
            return false;
        }
        DevisNonStandard other = (DevisNonStandard) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.DevisNonStandard[ id=" + id + " ]";
    }
    
}

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author anastasia.salari
 */
@Entity
public class ProfilMetier implements Serializable {

    @OneToMany(mappedBy = "profil")
    private List<Offre_Profil_Util> offre_Profil_Utils;

    private static final long serialVersionUID = 1L;
    
    
        private NiveauHabilitation niveauHabilitation;

    /**
     * Get the value of niveauHabilitation
     *
     * @return the value of niveauHabilitation
     */
    public NiveauHabilitation getNiveauHabilitation() {
        return niveauHabilitation;
    }

    /**
     * Set the value of niveauHabilitation
     *
     * @param niveauHabilitation new value of niveauHabilitation
     */
    public void setNiveauHabilitation(NiveauHabilitation niveauHabilitation) {
        this.niveauHabilitation = niveauHabilitation;
    }

    
        private Expertise niveauExpertise;

    /**
     * Get the value of niveauExpertise
     *
     * @return the value of niveauExpertise
     */
    public Expertise getNiveauExpertise() {
        return niveauExpertise;
    }

    /**
     * Set the value of niveauExpertise
     *
     * @param niveauExpertise new value of niveauExpertise
     */
    public void setNiveauExpertise(Expertise niveauExpertise) {
        this.niveauExpertise = niveauExpertise;
    }

    
        private float plafond;

    /**
     * Get the value of plafond
     *
     * @return the value of plafond
     */
    public float getPlafond() {
        return plafond;
    }

    /**
     * Set the value of plafond
     *
     * @param plafond new value of plafond
     */
    public void setPlafond(float plafond) {
        this.plafond = plafond;
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
        if (!(object instanceof ProfilMetier)) {
            return false;
        }
        ProfilMetier other = (ProfilMetier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.ProfilMetier[ id=" + id + " ]";
    }
    
}

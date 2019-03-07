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

/**
 *
 * @author anastasia.salari
 */
@Entity
public class ServiceStandard extends Service implements Serializable {

    @OneToMany(mappedBy = "serviceStandard")
    private List<Atelier> ateliers;

    public List<Atelier> getAteliers() {
        return ateliers;
    }

    public void setAteliers(List<Atelier> ateliers) {
        this.ateliers = ateliers;
    }
    
    

    @OneToMany(mappedBy = "service")
    private List<Livrable> livrables;

    public List<Livrable> getLivrables() {
        return livrables;
    }

    public void setLivrables(List<Livrable> livrables) {
        this.livrables = livrables;
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
    
        private float nbreJoursConsultantS;

    /**
     * Get the value of nbreJoursConsultantS
     *
     * @return the value of nbreJoursConsultantS
     */
    public float getNbreJoursConsultantS() {
        return nbreJoursConsultantS;
    }

    /**
     * Set the value of nbreJoursConsultantS
     *
     * @param nbreJoursConsultantS new value of nbreJoursConsultantS
     */
    public void setNbreJoursConsultantS(float nbreJoursConsultantS) {
        this.nbreJoursConsultantS = nbreJoursConsultantS;
    }

        private float nbreJoursConsultantC;

    /**
     * Get the value of nbreJoursConsultantC
     *
     * @return the value of nbreJoursConsultantC
     */
    public float getNbreJoursConsultantC() {
        return nbreJoursConsultantC;
    }

    /**
     * Set the value of nbreJoursConsultantC
     *
     * @param nbreJoursConsultantC new value of nbreJoursConsultantC
     */
    public void setNbreJoursConsultantC(float nbreJoursConsultantC) {
        this.nbreJoursConsultantC = nbreJoursConsultantC;
    }

    private float nbreJoursConsultantJ;

    /**
     * Get the value of nbreJoursConsultantJ
     *
     * @return the value of nbreJoursConsultantJ
     */
    public float getNbreJoursConsultantJ() {
        return nbreJoursConsultantJ;
    }

    /**
     * Set the value of nbreJoursConsultantJ
     *
     * @param nbreJoursConsultantJ new value of nbreJoursConsultantJ
     */
    public void setNbreJoursConsultantJ(float nbreJoursConsultantJ) {
        this.nbreJoursConsultantJ = nbreJoursConsultantJ;
    }

        private float nbreHeuresEntretien;

    /**
     * Get the value of nbreHeuresEntretien
     *
     * @return the value of nbreHeuresEntretien
     */
    public float getNbreHeuresEntretien() {
        return nbreHeuresEntretien;
    }

    /**
     * Set the value of nbreHeuresEntretien
     *
     * @param nbreHeuresEntretien new value of nbreHeuresEntretien
     */
    public void setNbreHeuresEntretien(float nbreHeuresEntretien) {
        this.nbreHeuresEntretien = nbreHeuresEntretien;
    }

        private float nbreHeuresSupportTel;

    /**
     * Get the value of nbreHeuresSupportTel
     *
     * @return the value of nbreHeuresSupportTel
     */
    public float getNbreHeuresSupportTel() {
        return nbreHeuresSupportTel;
    }

    /**
     * Set the value of nbreHeuresSupportTel
     *
     * @param nbreHeuresSupportTel new value of nbreHeuresSupportTel
     */
    public void setNbreHeuresSupportTel(float nbreHeuresSupportTel) {
        this.nbreHeuresSupportTel = nbreHeuresSupportTel;
    }

        private String descriptionPresta;

    /**
     * Get the value of descriptionPresta
     *
     * @return the value of descriptionPresta
     */
    public String getDescriptionPresta() {
        return descriptionPresta;
    }

    /**
     * Set the value of descriptionPresta
     *
     * @param descriptionPresta new value of descriptionPresta
     */
    public void setDescriptionPresta(String descriptionPresta) {
        this.descriptionPresta = descriptionPresta;
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
        if (!(object instanceof ServiceStandard)) {
            return false;
        }
        ServiceStandard other = (ServiceStandard) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.ServiceStandard[ id=" + id + " ]";
    }
    
}

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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author anastasia.salari
 */
@Entity
public class Communication implements Serializable {

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
        private UtilisateurHardis utilisateurHardis;

    /**
     * Get the value of utilisateurHardis
     *
     * @return the value of utilisateurHardis
     */
    public UtilisateurHardis getUtilisateurHardis() {
        return utilisateurHardis;
    }

    /**
     * Set the value of utilisateurHardis
     *
     * @param utilisateurHardis new value of utilisateurHardis
     */
    public void setUtilisateurHardis(UtilisateurHardis utilisateurHardis) {
        this.utilisateurHardis = utilisateurHardis;
    }

    
    
    
        private String message;

    /**
     * Get the value of message
     *
     * @return the value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the value of message
     *
     * @param message new value of message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateHeure;

    /**
     * Get the value of dateHeure
     *
     * @return the value of dateHeure
     */
    public Date getDateHeure() {
        return dateHeure;
    }

    /**
     * Set the value of dateHeure
     *
     * @param dateHeure new value of dateHeure
     */
    public void setDateHeure(Date dateHeure) {
        this.dateHeure = dateHeure;
    }

    private String typeQR;

    /**
     * Get the value of typeQR
     *
     * @return the value of typeQR
     */
    public String getTypeQR() {
        return typeQR;
    }

    /**
     * Set the value of typeQR
     *
     * @param typeQR new value of typeQR
     */
    public void setTypeQR(String typeQR) {
        this.typeQR = typeQR;
    }

    
        private int delai;

    /**
     * Get the value of delai
     *
     * @return the value of delai
     */
    public int getDelai() {
        return delai;
    }

    /**
     * Set the value of delai
     *
     * @param delai new value of delai
     */
    public void setDelai(int delai) {
        this.delai = delai;
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
        if (!(object instanceof Communication)) {
            return false;
        }
        Communication other = (Communication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.Communication[ id=" + id + " ]";
    }
    
}

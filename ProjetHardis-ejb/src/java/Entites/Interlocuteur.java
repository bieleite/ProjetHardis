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
public class Interlocuteur implements Serializable {

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
    
    
        private String nomInterlocuteur;

    /**
     * Get the value of nomInterlocuteur
     *
     * @return the value of nomInterlocuteur
     */
    public String getNomInterlocuteur() {
        return nomInterlocuteur;
    }

    /**
     * Set the value of nomInterlocuteur
     *
     * @param nomInterlocuteur new value of nomInterlocuteur
     */
    public void setNomInterlocuteur(String nomInterlocuteur) {
        this.nomInterlocuteur = nomInterlocuteur;
    }

    
        private String prenomInterlocuteur;

    /**
     * Get the value of prenomInterlocuteur
     *
     * @return the value of prenomInterlocuteur
     */
    public String getPrenomInterlocuteur() {
        return prenomInterlocuteur;
    }

    /**
     * Set the value of prenomInterlocuteur
     *
     * @param prenomInterlocuteur new value of prenomInterlocuteur
     */
    public void setPrenomInterlocuteur(String prenomInterlocuteur) {
        this.prenomInterlocuteur = prenomInterlocuteur;
    }

    
    
        private String telInterlocuteur;

    /**
     * Get the value of telInterlocuteur
     *
     * @return the value of telInterlocuteur
     */
    public String getTelInterlocuteur() {
        return telInterlocuteur;
    }

    /**
     * Set the value of telInterlocuteur
     *
     * @param telInterlocuteur new value of telInterlocuteur
     */
    public void setTelInterlocuteur(String telInterlocuteur) {
        this.telInterlocuteur = telInterlocuteur;
    }

        private String fonctionInterlocuteur;

    /**
     * Get the value of fonctionInterlocuteur
     *
     * @return the value of fonctionInterlocuteur
     */
    public String getFonctionInterlocuteur() {
        return fonctionInterlocuteur;
    }

    /**
     * Set the value of fonctionInterlocuteur
     *
     * @param fonctionInterlocuteur new value of fonctionInterlocuteur
     */
    public void setFonctionInterlocuteur(String fonctionInterlocuteur) {
        this.fonctionInterlocuteur = fonctionInterlocuteur;
    }
    
    @ManyToOne
        private Entreprise entreprise;

    /**
     * Get the value of entreprise
     *
     * @return the value of entreprise
     */
    public Entreprise getEntreprise() {
        return entreprise;
    }

    /**
     * Set the value of entreprise
     *
     * @param entreprise new value of entreprise
     */
    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
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
        if (!(object instanceof Interlocuteur)) {
            return false;
        }
        Interlocuteur other = (Interlocuteur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.Interlocuteur[ id=" + id + " ]";
    }
    
}

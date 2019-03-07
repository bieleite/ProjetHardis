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
public class EchangeTel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    private UtilisateurHardis interlocuteur;

    /**
     * Get the value of interlocuteur
     *
     * @return the value of interlocuteur
     */
    public UtilisateurHardis getInterlocuteur() {
        return interlocuteur;
    }

    /**
     * Set the value of interlocuteur
     *
     * @param interlocuteur new value of interlocuteur
     */
    public void setInterlocuteur(UtilisateurHardis interlocuteur) {
        this.interlocuteur = interlocuteur;
    }

        private String texte;

    /**
     * Get the value of texte
     *
     * @return the value of texte
     */
    public String getTexte() {
        return texte;
    }

    /**
     * Set the value of texte
     *
     * @param texte new value of texte
     */
    public void setTexte(String texte) {
        this.texte = texte;
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
        if (!(object instanceof EchangeTel)) {
            return false;
        }
        EchangeTel other = (EchangeTel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.EchangeTel[ id=" + id + " ]";
    }
    
}

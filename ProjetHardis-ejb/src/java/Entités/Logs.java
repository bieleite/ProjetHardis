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
import javax.persistence.Temporal;

/**
 *
 * @author anastasia.salari
 */
@Entity
public class Logs implements Serializable {

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
        private Utilisateur utilisateur;

    /**
     * Get the value of utilisateur
     *
     * @return the value of utilisateur
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /**
     * Set the value of utilisateur
     *
     * @param utilisateur new value of utilisateur
     */
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date dateAction;

    /**
     * Get the value of dateAction
     *
     * @return the value of dateAction
     */
    public Date getDateAction() {
        return dateAction;
    }

    /**
     * Set the value of dateAction
     *
     * @param dateAction new value of dateAction
     */
    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

    private String libelle;

    /**
     * Get the value of libelle
     *
     * @return the value of libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Set the value of libelle
     *
     * @param libelle new value of libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    
    private Action actionEffectue;

    /**
     * Get the value of actionEffectue
     *
     * @return the value of actionEffectue
     */
    public Action getActionEffectue() {
        return actionEffectue;
    }

    /**
     * Set the value of actionEffectue
     *
     * @param actionEffectue new value of actionEffectue
     */
    public void setActionEffectue(Action actionEffectue) {
        this.actionEffectue = actionEffectue;
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
        if (!(object instanceof Logs)) {
            return false;
        }
        Logs other = (Logs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.Logs[ id=" + id + " ]";
    }
    
}

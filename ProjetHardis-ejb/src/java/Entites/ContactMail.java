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
 * @author 6170361
 */
@Entity
public class ContactMail implements Serializable {

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

    private boolean repondu;

    /**
     * Get the value of repondu
     *
     * @return the value of repondu
     */
    public boolean isRepondu() {
        return repondu;
    }

    /**
     * Set the value of repondu
     *
     * @param repondu new value of repondu
     */
    public void setRepondu(boolean repondu) {
        this.repondu = repondu;
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

    private String sujet;

    /**
     * Get the value of sujet
     *
     * @return the value of sujet
     */
    public String getSujet() {
        return sujet;
    }

    /**
     * Set the value of sujet
     *
     * @param sujet new value of sujet
     */
    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    private String tel;

    /**
     * Get the value of tel
     *
     * @return the value of tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * Set the value of tel
     *
     * @param tel new value of tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

        private String email;

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    private String prenom;

    /**
     * Get the value of prenom
     *
     * @return the value of prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Set the value of prenom
     *
     * @param prenom new value of prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    private String nom;

    /**
     * Get the value of nom
     *
     * @return the value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the value of nom
     *
     * @param nom new value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactMail)) {
            return false;
        }
        ContactMail other = (ContactMail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entites.ContactMail[ id=" + id + " ]";
    }
    
}

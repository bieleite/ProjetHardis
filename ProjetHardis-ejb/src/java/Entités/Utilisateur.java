/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import sun.security.provider.MD5;

/**
 *
 * @author anastasia.salari
 */
@Entity
public class Utilisateur implements Serializable {

    @OneToMany(mappedBy = "utilisateur")
    private List<Logs> logss;

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

    @Column(nullable = false, unique = true)
        private String login;

    /**
     * Get the value of login
     *
     * @return the value of login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set the value of login
     *
     * @param login new value of login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    
        private MD5 mdp;

    /**
     * Get the value of mdp
     *
     * @return the value of mdp
     */
    public MD5 getMdp() {
        return mdp;
    }

    /**
     * Set the value of mdp
     *
     * @param mdp new value of mdp
     */
    public void setMdp(MD5 mdp) {
        this.mdp = mdp;
    }
    
    
        private String questionSecrete;

    /**
     * Get the value of questionSecrete
     *
     * @return the value of questionSecrete
     */
    public String getQuestionSecrete() {
        return questionSecrete;
    }

    /**
     * Set the value of questionSecrete
     *
     * @param questionSecrete new value of questionSecrete
     */
    public void setQuestionSecrete(String questionSecrete) {
        this.questionSecrete = questionSecrete;
    }

    
        private MD5 reponseSecrete;

    /**
     * Get the value of reponseSecrete
     *
     * @return the value of reponseSecrete
     */
    public MD5 getReponseSecrete() {
        return reponseSecrete;
    }

    /**
     * Set the value of reponseSecrete
     *
     * @param reponseSecrete new value of reponseSecrete
     */
    public void setReponseSecrete(MD5 reponseSecrete) {
        this.reponseSecrete = reponseSecrete;
    }
    
    
    

    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date dateRGPD;

    /**
     * Get the value of dateRGPD
     *
     * @return the value of dateRGPD
     */
    public Date getDateRGPD() {
        return dateRGPD;
    }

    /**
     * Set the value of dateRGPD
     *
     * @param dateRGPD new value of dateRGPD
     */
    public void setDateRGPD(Date dateRGPD) {
        this.dateRGPD = dateRGPD;
    }

        private int RGPD;

    /**
     * Get the value of RGPD
     *
     * @return the value of RGPD
     */
    public int getRGPD() {
        return RGPD;
    }

    /**
     * Set the value of RGPD
     *
     * @param RGPD new value of RGPD
     */
    public void setRGPD(int RGPD) {
        this.RGPD = RGPD;
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
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.Utilisateur[ id=" + id + " ]";
    }
    
}

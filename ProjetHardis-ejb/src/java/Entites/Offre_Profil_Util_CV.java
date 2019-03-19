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
public class Offre_Profil_Util_CV implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
        private float prixUnit;

    /**
     * Get the value of prixUnit
     *
     * @return the value of prixUnit
     */
    public float getPrixUnit() {
        return prixUnit;
    }

    /**
     * Set the value of prixUnit
     *
     * @param prixUnit new value of prixUnit
     */
    public void setPrixUnit(float prixUnit) {
        this.prixUnit = prixUnit;
    }

    @ManyToOne
        private Offre offre;

    /**
     * Get the value of offre
     *
     * @return the value of offre
     */
    public Offre getOffre() {
        return offre;
    }

    /**
     * Set the value of offre
     *
     * @param offre new value of offre
     */
    public void setOffre(Offre offre) {
        this.offre = offre;
    }
    
        private String lienCV;

    /**
     * Get the value of lienCV
     *
     * @return the value of lienCV
     */
    public String getLienCV() {
        return lienCV;
    }

    /**
     * Set the value of lienCV
     *
     * @param lienCV new value of lienCV
     */
    public void setLienCV(String lienCV) {
        this.lienCV = lienCV;
    }


    @ManyToOne
    private UtilisateurHardis utilisateur;

    /**
     * Get the value of utilisateur
     *
     * @return the value of utilisateur
     */
    public UtilisateurHardis getUtilisateur() {
        return utilisateur;
    }

    /**
     * Set the value of utilisateur
     *
     * @param utilisateur new value of utilisateur
     */
    public void setUtilisateur(UtilisateurHardis utilisateur) {
        this.utilisateur = utilisateur;
    }

    @ManyToOne
    private ProfilMetier profil;

    /**
     * Get the value of profil
     *
     * @return the value of profil
     */
    public ProfilMetier getProfil() {
        return profil;
    }

    /**
     * Set the value of profil
     *
     * @param profil new value of profil
     */
    public void setProfil(ProfilMetier profil) {
        this.profil = profil;
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
        if (!(object instanceof Offre_Profil_Util_CV)) {
            return false;
        }
        Offre_Profil_Util_CV other = (Offre_Profil_Util_CV) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.Offre_Profil_Util[ id=" + id + " ]";
    }
    
}

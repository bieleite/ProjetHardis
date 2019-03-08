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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author anastasia.salari
 */
@Entity
public class HistoriqueTraitement implements Serializable {

    private static final long serialVersionUID = 1L;
    
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
    
    @OneToOne
        private UtilisateurHardis validateur;

    /**
     * Get the value of validateur
     *
     * @return the value of validateur
     */
    public UtilisateurHardis getValidateur() {
        return validateur;
    }

    /**
     * Set the value of validateur
     *
     * @param validateur new value of validateur
     */
    public void setValidateur(UtilisateurHardis validateur) {
        this.validateur = validateur;
    }

    
    @OneToOne
        private UtilisateurHardis consultant;

    /**
     * Get the value of consultant
     *
     * @return the value of consultant
     */
    public UtilisateurHardis getConsultant() {
        return consultant;
    }

    /**
     * Set the value of consultant
     *
     * @param consultant new value of consultant
     */
    public void setConsultant(UtilisateurHardis consultant) {
        this.consultant = consultant;
    }
    
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date dateDebut;

    /**
     * Get the value of dateDebut
     *
     * @return the value of dateDebut
     */
    public Date getDateDebut() {
        return dateDebut;
    }

    /**
     * Set the value of dateDebut
     *
     * @param dateDebut new value of dateDebut
     */
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateFin;

    /**
     * Get the value of dateFin
     *
     * @return the value of dateFin
     */
    public Date getDateFin() {
        return dateFin;
    }

    /**
     * Set the value of dateFin
     *
     * @param dateFin new value of dateFin
     */
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

        private TypeUtilisateur utilisateurCourant;

    /**
     * Get the value of utilisateurCourant
     *
     * @return the value of utilisateurCourant
     */
    public TypeUtilisateur getUtilisateurCourant() {
        return utilisateurCourant;
    }

    /**
     * Set the value of utilisateurCourant
     *
     * @param utilisateurCourant new value of utilisateurCourant
     */
    public void setUtilisateurCourant(TypeUtilisateur utilisateurCourant) {
        this.utilisateurCourant = utilisateurCourant;
    }


    
    @OneToOne
        private UtilisateurHardis refLocal;

    /**
     * Get the value of refLocal
     *
     * @return the value of refLocal
     */
    public UtilisateurHardis getRefLocal() {
        return refLocal;
    }

    /**
     * Set the value of refLocal
     *
     * @param refLocal new value of refLocal
     */
    public void setRefLocal(UtilisateurHardis refLocal) {
        this.refLocal = refLocal;
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
        if (!(object instanceof HistoriqueTraitement)) {
            return false;
        }
        HistoriqueTraitement other = (HistoriqueTraitement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.HistoriqueTraitement[ id=" + id + " ]";
    }
    
}

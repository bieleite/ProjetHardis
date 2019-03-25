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
public class Facture implements Serializable {

    
    private boolean paye;

    /**
     * Get the value of paye
     *
     * @return the value of paye
     */
    public boolean isPaye() {
        return paye;
    }

    /**
     * Set the value of paye
     *
     * @param paye new value of paye
     */
    public void setPaye(boolean paye) {
        this.paye = paye;
    }

    private static final long serialVersionUID = 1L;
    
        private String motifDepass;

    /**
     * Get the value of motifDepass
     *
     * @return the value of motifDepass
     */
    public String getMotifDepass() {
        return motifDepass;
    }
    

    /**
     * Set the value of motifDepass
     *
     * @param motifDepass new value of motifDepass
     */
    public void setMotifDepass(String motifDepass) {
        this.motifDepass = motifDepass;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateFacture;

    /**
     * Get the value of dateFacture
     *
     * @return the value of dateFacture
     */
    public Date getDateFacture() {
        return dateFacture;
    }

    /**
     * Set the value of dateFacture
     *
     * @param dateFacture new value of dateFacture
     */
    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    
        private float montantDepass;

    /**
     * Get the value of montantDepass
     *
     * @return the value of montantDepass
     */
    public float getMontantDepass() {
        return montantDepass;
    }

    /**
     * Set the value of montantDepass
     *
     * @param montantDepass new value of montantDepass
     */
    public void setMontantDepass(float montantDepass) {
        this.montantDepass = montantDepass;
    }

        private float montant;

    /**
     * Get the value of montant
     *
     * @return the value of montant
     */
    public float getMontant() {
        return montant;
    }

    /**
     * Set the value of montant
     *
     * @param montant new value of montant
     */
    public void setMontant(float montant) {
        this.montant = montant;
    }

    
        private String lienFact;

    /**
     * Get the value of lienFact
     *
     * @return the value of lienFact
     */
    public String getLienFact() {
        return lienFact;
    }

    /**
     * Set the value of lienFact
     *
     * @param lienFact new value of lienFact
     */
    public void setLienFact(String lienFact) {
        this.lienFact = lienFact;
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
        if (!(object instanceof Facture)) {
            return false;
        }
        Facture other = (Facture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.Facture[ id=" + id + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author anastasia.salari
 */
@Entity
public class HistoriqueDevis implements Serializable {

    @OneToMany(mappedBy = "histoDevis")
    private List<Document> documents;

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
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

    @ManyToOne
        private UtilisateurHardis utilHardis;

    /**
     * Get the value of utilHardis
     *
     * @return the value of utilHardis
     */
    public UtilisateurHardis getUtilHardis() {
        return utilHardis;
    }

    /**
     * Set the value of utilHardis
     *
     * @param utilHardis new value of utilHardis
     */
    public void setUtilHardis(UtilisateurHardis utilHardis) {
        this.utilHardis = utilHardis;
    }

    
        private int numPropo;

    /**
     * Get the value of numPropo
     *
     * @return the value of numPropo
     */
    public int getNumPropo() {
        return numPropo;
    }

    /**
     * Set the value of numPropo
     *
     * @param numPropo new value of numPropo
     */
    public void setNumPropo(int numPropo) {
        this.numPropo = numPropo;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoriqueDevis)) {
            return false;
        }
        HistoriqueDevis other = (HistoriqueDevis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.HistoriqueDevis[ id=" + id + " ]";
    }
    
}

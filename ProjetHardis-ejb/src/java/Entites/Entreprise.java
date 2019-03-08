/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author anastasia.salari
 */
@Entity
public class Entreprise implements Serializable {

    @OneToOne(mappedBy = "entreprise")
    private Client client;

    @OneToMany(mappedBy = "entreprise")
    private List<Interlocuteur> interlocuteurs;

    public List<Interlocuteur> getInterlocuteurs() {
        return interlocuteurs;
    }

    public void setInterlocuteurs(List<Interlocuteur> interlocuteurs) {
        this.interlocuteurs = interlocuteurs;
    }
    
        private Agence agence;

    /**
     * Get the value of agence
     *
     * @return the value of agence
     */
    public Agence getAgence() {
        return agence;
    }

    /**
     * Set the value of agence
     *
     * @param agence new value of agence
     */
    public void setAgence(Agence agence) {
        this.agence = agence;
    }

        private String mdpEntreprise;

    /**
     * Get the value of mdpEntreprise
     *
     * @return the value of mdpEntreprise
     */
    public String getMdpEntreprise() {
        return mdpEntreprise;
    }

    /**
     * Set the value of mdpEntreprise
     *
     * @param mdpEntreprise new value of mdpEntreprise
     */
    public void setMdpEntreprise(String mdpEntreprise) {
        this.mdpEntreprise = mdpEntreprise;
    }

    
    @Column(unique=true)
    private String codeContrat;

    /**
     * Get the value of codeContrat
     *
     * @return the value of codeContrat
     */
    public String getCodeContrat() {
        return codeContrat;
    }

    /**
     * Set the value of codeContrat
     *
     * @param codeContrat new value of codeContrat
     */
    public void setCodeContrat(String codeContrat) {
        this.codeContrat = codeContrat;
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
    
        private String numeroEntreprise;

    /**
     * Get the value of numeroEntreprise
     *
     * @return the value of numeroEntreprise
     */
    public String getNumeroEntreprise() {
        return numeroEntreprise;
    }

    /**
     * Set the value of numeroEntreprise
     *
     * @param numeroEntreprise new value of numeroEntreprise
     */
    public void setNumeroEntreprise(String numeroEntreprise) {
        this.numeroEntreprise = numeroEntreprise;
    }

    
        private Adresse adresseFact;

    /**
     * Get the value of adresseFact
     *
     * @return the value of adresseFact
     */
    public Adresse getAdresseFact() {
        return adresseFact;
    }

    /**
     * Set the value of adresseFact
     *
     * @param adresseFact new value of adresseFact
     */
    public void setAdresseFact(Adresse adresseFact) {
        this.adresseFact = adresseFact;
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
        if (!(object instanceof Entreprise)) {
            return false;
        }
        Entreprise other = (Entreprise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.Entreprise[ id=" + id + " ]";
    }
    
}

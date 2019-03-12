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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author anastasia.salari
 */
@Entity
public class Entreprise implements Serializable {

        private boolean visible;

    /**
     * Get the value of visible
     *
     * @return the value of visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Set the value of visible
     *
     * @param visible new value of visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

        private String nomEntreprise;

    /**
     * Get the value of nomEntreprise
     *
     * @return the value of nomEntreprise
     */
    public String getNomEntreprise() {
        return nomEntreprise;
    }

    /**
     * Set the value of nomEntreprise
     *
     * @param nomEntreprise new value of nomEntreprise
     */
    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    
    
    @OneToOne(mappedBy = "entreprise")
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
        private boolean certifie;

    /**
     * Get the value of certifie
     *
     * @return the value of certifie
     */
    public boolean getCertifie() {
        return certifie;
    }

    /**
     * Set the value of certifie
     *
     * @param certifie new value of certifie
     */
    public void setCertifie(boolean certifie) {
        this.certifie = certifie;
    }

    private String lienJustif;

    /**
     * Get the value of lienJustif
     *
     * @return the value of lienJustif
     */
    public String getLienJustif() {
        return lienJustif;
    }

    /**
     * Set the value of lienJustif
     *
     * @param lienJustif new value of lienJustif
     */
    public void setLienJustif(String lienJustif) {
        this.lienJustif = lienJustif;
    }

    @OneToMany(mappedBy = "entreprise")
    private List<Interlocuteur> interlocuteurs;

    public List<Interlocuteur> getInterlocuteurs() {
        return interlocuteurs;
    }

    public void setInterlocuteurs(List<Interlocuteur> interlocuteurs) {
        this.interlocuteurs = interlocuteurs;
    }
    
    @ManyToOne
        private Adresse adresse;

    /**
     * Get the value of adresse
     *
     * @return the value of adresse
     */
    public Adresse getAdresse() {
        return adresse;
    }

    /**
     * Set the value of adresse
     *
     * @param adresse new value of adresse
     */
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
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

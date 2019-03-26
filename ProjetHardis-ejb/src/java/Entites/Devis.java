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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author anastasia.salari
 */
@Entity

@Inheritance
    (strategy=InheritanceType.TABLE_PER_CLASS)



public class Devis implements Serializable {

    
        private float nbJoursPresta;

    /**
     * Get the value of nbJoursPresta
     *
     * @return the value of nbJoursPresta
     */
    public float getNbJoursPresta() {
        return nbJoursPresta;
    }

    /**
     * Set the value of nbJoursPresta
     *
     * @param nbJoursPresta new value of nbJoursPresta
     */
    public void setNbJoursPresta(float nbJoursPresta) {
        this.nbJoursPresta = nbJoursPresta;
    }

    @OneToMany(mappedBy = "devis")
    private List<HistoriqueDevis> historiqueDeviss;

    public List<HistoriqueDevis> getHistoriqueDeviss() {
        return historiqueDeviss;
    }

    public void setHistoriqueDeviss(List<HistoriqueDevis> historiqueDeviss) {
        this.historiqueDeviss = historiqueDeviss;
    }

    public List<HistoriqueTraitement> getHistoriqueTraitements() {
        return historiqueTraitements;
    }

    public void setHistoriqueTraitements(List<HistoriqueTraitement> historiqueTraitements) {
        this.historiqueTraitements = historiqueTraitements;
    }
    

    @OneToMany(mappedBy = "devis")
    private List<HistoriqueTraitement> historiqueTraitements;

    @OneToMany(mappedBy = "devis")
    private List<EchangeTel> echangeTels;

    public List<EchangeTel> getEchangeTels() {
        return echangeTels;
    }

    public void setEchangeTels(List<EchangeTel> echangeTels) {
        this.echangeTels = echangeTels;
    }

    public List<Communication> getCommunications() {
        return communications;
    }

    public void setCommunications(List<Communication> communications) {
        this.communications = communications;
    }

    public List<HistoriqueEtats> getHistoriqueEtatss() {
        return historiqueEtatss;
    }

    public void setHistoriqueEtatss(List<HistoriqueEtats> historiqueEtatss) {
        this.historiqueEtatss = historiqueEtatss;
    }
    
    

    @OneToMany(mappedBy = "devis")
    private List<Communication> communications;

    @OneToMany(mappedBy = "devis")
    private List<HistoriqueEtats> historiqueEtatss;

    @OneToMany(mappedBy = "devis")
    private List<Facture> factures;

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }
    
    
   
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
       private Date dateIntervSouhaitee;

    /**
     * Get the value of dateIntervSouhaitee
     *
     * @return the value of dateIntervSouhaitee
     */
    public Date getDateIntervSouhaitee() {
        return dateIntervSouhaitee;
    }

    /**
     * Set the value of dateIntervSouhaitee
     *
     * @param dateIntervSouhaitee new value of dateIntervSouhaitee
     */
    public void setDateIntervSouhaitee(Date dateIntervSouhaitee) {
        this.dateIntervSouhaitee = dateIntervSouhaitee;
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
    
    @ManyToOne
        private Client client;

    /**
     * Get the value of client
     *
     * @return the value of client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Set the value of client
     *
     * @param client new value of client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    @OneToOne
        private Service service;

    /**
     * Get the value of service
     *
     * @return the value of service
     */
    public Service getService() {
        return service;
    }

    /**
     * Set the value of service
     *
     * @param service new value of service
     */
    public void setService(Service service) {
        this.service = service;
    }

        private TypeService typeDevis;

    /**
     * Get the value of typeDevis
     *
     * @return the value of typeDevis
     */
    public TypeService getTypeDevis() {
        return typeDevis;
    }

    /**
     * Set the value of typeDevis
     *
     * @param typeDevis new value of typeDevis
     */
    public void setTypeDevis(TypeService typeDevis) {
        this.typeDevis = typeDevis;
    }

    
        private Statut statut;

    /**
     * Get the value of statut
     *
     * @return the value of statut
     */
    public Statut getStatut() {
        return statut;
    }

    
    /**
     * Set the value of statut
     *
     * @param statut new value of statut
     */
    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    
        private String motifRefus;

    /**
     * Get the value of motifRefus
     *
     * @return the value of motifRefus
     */
    public String getMotifRefus() {
        return motifRefus;
    }

    /**
     * Set the value of motifRefus
     *
     * @param motifRefus new value of motifRefus
     */
    public void setMotifRefus(String motifRefus) {
        this.motifRefus = motifRefus;
    }

    
    @OneToOne
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

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateFinPresta;

    /**
     * Get the value of dateFinPresta
     *
     * @return the value of dateFinPresta
     */
    public Date getDateFinPresta() {
        return dateFinPresta;
    }

    /**
     * Set the value of dateFinPresta
     *
     * @param dateFinPresta new value of dateFinPresta
     */
    public void setDateFinPresta(Date dateFinPresta) {
        this.dateFinPresta = dateFinPresta;
    }

    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
        private Date dateDebutPresta;

    /**
     * Get the value of dateDebutPresta
     *
     * @return the value of dateDebutPresta
     */
    public Date getDateDebutPresta() {
        return dateDebutPresta;
    }

    /**
     * Set the value of dateDebutPresta
     *
     * @param dateDebutPresta new value of dateDebutPresta
     */
    public void setDateDebutPresta(Date dateDebutPresta) {
        this.dateDebutPresta = dateDebutPresta;
    }

    
    @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateDevis;

    /**
     * Get the value of dateDevis
     *
     * @return the value of dateDevis
     */
    public Date getDateDevis() {
        return dateDevis;
    }

    /**
     * Set the value of dateDevis
     *
     * @param dateDevis new value of dateDevis
     */
    public void setDateDevis(Date dateDevis) {
        this.dateDevis = dateDevis;
    }

    private float montantDevis;

    /**
     * Get the value of montantDevis
     *
     * @return the value of montantDevis
     */
    public float getMontantDevis() {
        return montantDevis;
    }

    /**
     * Set the value of montantDevis
     *
     * @param montantDevis new value of montantDevis
     */
    public void setMontantDevis(float montantDevis) {
        this.montantDevis = montantDevis;
    }
    
    
        private Facturation indicateurFact;

    /**
     * Get the value of indicateurFact
     *
     * @return the value of indicateurFact
     */
    public Facturation getIndicateurFact() {
        return indicateurFact;
    }

    /**
     * Set the value of indicateurFact
     *
     * @param indicateurFact new value of indicateurFact
     */
    public void setIndicateurFact(Facturation indicateurFact) {
        this.indicateurFact = indicateurFact;
    }

    
        private String saisieLibre;

    /**
     * Get the value of saisieLibre
     *
     * @return the value of saisieLibre
     */
    public String getSaisieLibre() {
        return saisieLibre;
    }

    /**
     * Set the value of saisieLibre
     *
     * @param saisieLibre new value of saisieLibre
     */
    public void setSaisieLibre(String saisieLibre) {
        this.saisieLibre = saisieLibre;
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
        if (!(object instanceof Devis)) {
            return false;
        }
        Devis other = (Devis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.Devis[ id=" + id + " ]";
    }
    
}

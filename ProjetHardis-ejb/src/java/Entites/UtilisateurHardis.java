/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author anastasia.salari
 */
@Entity

public class UtilisateurHardis extends Utilisateur implements Serializable {

    @OneToMany(mappedBy = "UtilisateurHardis")
    private List<Disponibilite> disponibilites;

    @OneToMany(mappedBy = "interlocuteur")
    private List<EchangeTel> echangeTels;

    public List<EchangeTel> getEchangeTels() {
        return echangeTels;
    }

    public void setEchangeTels(List<EchangeTel> echangeTels) {
        this.echangeTels = echangeTels;
    }

    public HistoriqueTraitement getHistoriqueTraitement() {
        return historiqueTraitement;
    }

    public void setHistoriqueTraitement(HistoriqueTraitement historiqueTraitement) {
        this.historiqueTraitement = historiqueTraitement;
    }

<<<<<<< HEAD
=======

>>>>>>> origin/v3
    public List<HistoriqueDevis> getHistoriqueDeviss() {
        return historiqueDeviss;
    }

    public void setHistoriqueDeviss(List<HistoriqueDevis> historiqueDeviss) {
        this.historiqueDeviss = historiqueDeviss;
    }

    public List<Communication> getCommunications() {
        return communications;
    }

    public void setCommunications(List<Communication> communications) {
        this.communications = communications;
    }

    public List<Offre_Profil_Util_CV> getOffre_Profil_Utils() {
        return offre_Profil_Utils;
    }

    public void setOffre_Profil_Utils(List<Offre_Profil_Util_CV> offre_Profil_Utils) {
        this.offre_Profil_Utils = offre_Profil_Utils;
    }
    
    
    

    @OneToOne(mappedBy = "refLocal")
    private HistoriqueTraitement historiqueTraitement;


    @OneToMany(mappedBy = "utilHardis")
    private List<HistoriqueDevis> historiqueDeviss;

    @OneToMany(mappedBy = "utilisateurHardis")
    private List<Communication> communications;

    @OneToMany(mappedBy = "utilisateur")
    private List<Offre_Profil_Util_CV> offre_Profil_Utils;


    private String lienCVDefaut;

    /**
     * Get the value of lienCVDefaut
     *
     * @return the value of lienCVDefaut
     */
    public String getLienCVDefaut() {
        return lienCVDefaut;
    }

    /**
     * Set the value of lienCVDefaut
     *
     * @param lienCVDefaut new value of lienCVDefaut
     */
    public void setLienCVDefaut(String lienCVDefaut) {
        this.lienCVDefaut = lienCVDefaut;
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
    
    
    private StatutUtilisateur statut;

    /**
     * Get the value of statut
     *
     * @return the value of statut
     */
    public StatutUtilisateur getStatut() {
        return statut;
    }

    /**
     * Set the value of statut
     *
     * @param statut new value of statut
     */
    public void setStatut(StatutUtilisateur statut) {
        this.statut = statut;
    }
    
    
        private ProfilTechnique profilTechique;

    /**
     * Get the value of profilTechique
     *
     * @return the value of profilTechique
     */
    public ProfilTechnique getProfilTechique() {
        return profilTechique;
    }

    /**
     * Set the value of profilTechique
     *
     * @param profilTechique new value of profilTechique
     */
    public void setProfilTechique(ProfilTechnique profilTechique) {
        this.profilTechique = profilTechique;
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
        if (!(object instanceof UtilisateurHardis)) {
            return false;
        }
        UtilisateurHardis other = (UtilisateurHardis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.UtilisateurHardis[ id=" + id + " ]";
    }

    /**
     * @return the disponibilites
     */
    public List<Disponibilite> getDisponibilites() {
        return disponibilites;
    }

    /**
     * @param disponibilites the disponibilites to set
     */
    public void setDisponibilites(List<Disponibilite> disponibilites) {
        this.disponibilites = disponibilites;
    }
    
}

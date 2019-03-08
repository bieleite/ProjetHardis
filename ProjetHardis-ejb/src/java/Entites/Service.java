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
<<<<<<< HEAD
@Inheritance
    (strategy=InheritanceType.TABLE_PER_CLASS)
=======
 @Inheritance
(strategy=InheritanceType.TABLE_PER_CLASS)
>>>>>>> origin/v3
public class Service implements Serializable {

    @OneToMany(mappedBy = "service")
    private List<HistoriqueEtats> historiqueEtatss;

    public List<HistoriqueEtats> getHistoriqueEtatss() {
        return historiqueEtatss;
    }

    public void setHistoriqueEtatss(List<HistoriqueEtats> historiqueEtatss) {
        this.historiqueEtatss = historiqueEtatss;
    }

   
    
    private static final long serialVersionUID = 1L;
    
    
    
    
    
    
    private String nomService;

    /**
     * Get the value of nomService
     *
     * @return the value of nomService
     */
    public String getNomService() {
        return nomService;
    }

    /**
     * Set the value of nomService
     *
     * @param nomService new value of nomService
     */
    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    
    private String descriptionService;

    /**
     * Get the value of descriptionService
     *
     * @return the value of descriptionService
     */
    public String getDescriptionService() {
        return descriptionService;
    }

    /**
     * Set the value of descriptionService
     *
     * @param descriptionService new value of descriptionService
     */
    public void setDescriptionService(String descriptionService) {
        this.descriptionService = descriptionService;
    }

        private LieuIntervention lieuInterv;

    /**
     * Get the value of lieuInterv
     *
     * @return the value of lieuInterv
     */
    public LieuIntervention getLieuInterv() {
        return lieuInterv;
    }

    /**
     * Set the value of lieuInterv
     *
     * @param lieuInterv new value of lieuInterv
     */
    public void setLieuInterv(LieuIntervention lieuInterv) {
        this.lieuInterv = lieuInterv;
    }

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

    
    
    
    private TypeService typeService;

    /**
     * Get the value of typeService
     *
     * @return the value of typeService
     */
    public TypeService getTypeService() {
        return typeService;
    }

    /**
     * Set the value of typeService
     *
     * @param typeService new value of typeService
     */
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

        private String conditionsContract;

    /**
     * Get the value of conditionsContract
     *
     * @return the value of conditionsContract
     */
    public String getConditionsContract() {
        return conditionsContract;
    }

    /**
     * Set the value of conditionsContract
     *
     * @param conditionsContract new value of conditionsContract
     */
    public void setConditionsContract(String conditionsContract) {
        this.conditionsContract = conditionsContract;
    }

        private double coutService;

    /**
     * Get the value of coutService
     *
     * @return the value of coutService
     */
    public double getCoutService() {
        return coutService;
    }

    /**
     * Set the value of coutService
     *
     * @param coutService new value of coutService
     */
    public void setCoutService(double coutService) {
        this.coutService = coutService;
    }

    private FacturationFrais factFrais;

    /**
     * Get the value of factFrais
     *
     * @return the value of factFrais
     */
    public FacturationFrais getFactFrais() {
        return factFrais;
    }

    /**
     * Set the value of factFrais
     *
     * @param factFrais new value of factFrais
     */
    public void setFactFrais(FacturationFrais factFrais) {
        this.factFrais = factFrais;
    }

        private int delaiRelance;

    /**
     * Get the value of delaiRelance
     *
     * @return the value of delaiRelance
     */
    public int getDelaiRelance() {
        return delaiRelance;
    }

    /**
     * Set the value of delaiRelance
     *
     * @param delaiRelance new value of delaiRelance
     */
    public void setDelaiRelance(int delaiRelance) {
        this.delaiRelance = delaiRelance;
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
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.Service[ id=" + id + " ]";
    }
    
}

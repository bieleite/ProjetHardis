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
import javax.persistence.OneToMany;

/**
 *
 * @author anastasia.salari
 */
@Entity
public class Adresse implements Serializable {

    @OneToMany(mappedBy = "adresse")
    private List<Entreprise> entreprises;

    public List<Entreprise> getEntreprises() {
        return entreprises;
    }

    public void setEntreprises(List<Entreprise> entreprises) {
        this.entreprises = entreprises;
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
    
    
    private int numeroRue;

    /**
     * Get the value of numeroRue
     *
     * @return the value of numeroRue
     */
    public int getNumeroRue() {
        return numeroRue;
    }

    /**
     * Set the value of numeroRue
     *
     * @param numeroRue new value of numeroRue
     */
    public void setNumeroRue(int numeroRue) {
        this.numeroRue = numeroRue;
    }

    private String nomRue;

    /**
     * Get the value of nomRue
     *
     * @return the value of nomRue
     */
    public String getNomRue() {
        return nomRue;
    }

    /**
     * Set the value of nomRue
     *
     * @param nomRue new value of nomRue
     */
    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

        private String ville;

    /**
     * Get the value of ville
     *
     * @return the value of ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * Set the value of ville
     *
     * @param ville new value of ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

        private String codePostal;

    /**
     * Get the value of codePostal
     *
     * @return the value of codePostal
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Set the value of codePostal
     *
     * @param codePostal new value of codePostal
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
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
        if (!(object instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.Adresse[ id=" + id + " ]";
    }
    
}

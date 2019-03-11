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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author anastasia.salari
 */
@Entity
public class Client extends Utilisateur implements Serializable {

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

    @OneToMany(mappedBy = "client")
    private List<Devis> deviss;

    public List<Devis> getDeviss() {
        return deviss;
    }

    public void setDeviss(List<Devis> deviss) {
        this.deviss = deviss;
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
    
    @OneToOne
        private Entreprise entreprise;

    /**
     * Get the value of entreprise
     *
     * @return the value of entreprise
     */
    public Entreprise getEntreprise() {
        return entreprise;
    }

    /**
     * Set the value of entreprise
     *
     * @param entreprise new value of entreprise
     */
    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }
    
       private String codepostal;

    /**
     * Get the value of codepostal
     *
     * @return the value of codepostal
     */
    public String getCodepostal() {
        return codepostal;
    }

    /**
     * Set the value of codepostal
     *
     * @param codepostal new value of codepostal
     */
    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entit\u00e9s.Client[ id=" + id + " ]";
    }
    
}

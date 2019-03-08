/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

<<<<<<< HEAD
import Entites.UtilisateurHardis;
=======
import Entites.EchangeTel;
import Entites.HistoriqueDevis;
import Entites.HistoriqueTraitement;
import Entites.ProfilTechnique;
import Entites.StatutUtilisateur;
import Entites.Utilisateur;
import Entites.UtilisateurHardis;
import java.util.ArrayList;
import java.util.Date;
>>>>>>> origin/v3
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
<<<<<<< HEAD
 * @author gabrielleite
=======
 * @author anastasia.salari
>>>>>>> origin/v3
 */
@Stateless
public class UtilisateurHardisFacade extends AbstractFacade<UtilisateurHardis> implements UtilisateurHardisFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurHardisFacade() {
        super(UtilisateurHardis.class);
    }
<<<<<<< HEAD
=======

    @Override
    public void creerUtilisateurH(String nom, String prenom, String login, String mdp, String questSecrete, String repSecrete, Date dateRGPD, int rgpd, ProfilTechnique profil, StatutUtilisateur statut, String lientCV) {
       UtilisateurHardis u = new UtilisateurHardis();  
       u.setNom(nom);
       u.setPrenom(prenom);
       u.setLogin(login);
       u.setMdp(mdp);
       u.setDateRGPD(null);
       u.setRGPD(-1);
       u.setStatut(statut);
       u.setLienCVDefaut(lientCV);
       u.setProfilTechique(profil);
       u.setEchangeTels(new ArrayList<EchangeTel>());
       u.setHistoriqueDeviss(new ArrayList<HistoriqueDevis>());
       u.setHistoriqueTraitement(new HistoriqueTraitement());
       u.setOffre_Profil_Utils(new ArrayList());
       u.setHistoriqueDeviss(new ArrayList<HistoriqueDevis>());
       u.setQuestionSecrete(questSecrete);
       u.setReponseSecrete(repSecrete);
         u.setVisible(true);
       em.persist(u);
    }
    
    
    
    
>>>>>>> origin/v3
    
}

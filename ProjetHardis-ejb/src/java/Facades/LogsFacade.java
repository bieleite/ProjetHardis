/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

<<<<<<< HEAD
import Entites.Logs;
=======
import Entites.Action;
import Entites.Logs;
import Entites.UtilisateurHardis;
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
public class LogsFacade extends AbstractFacade<Logs> implements LogsFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LogsFacade() {
        super(Logs.class);
    }
<<<<<<< HEAD
=======

    @Override
    public void creerLog(Action action, Date date, String libelle, UtilisateurHardis util) {
        Logs log = new Logs();
        log.setActionEffectue(action);
        log.setDateAction(date);
        log.setLibelle(libelle);
        log.setUtilisateur(util);   
        em.persist(log);
    } 
    
>>>>>>> origin/v3
    
}

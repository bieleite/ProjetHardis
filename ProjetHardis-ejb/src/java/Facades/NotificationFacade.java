/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Client;
import Entites.Notification;
import Entites.Service;
import Entites.Utilisateur;
import Entites.UtilisateurHardis;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 6170361
 */
@Stateless
public class NotificationFacade extends AbstractFacade<Notification> implements NotificationFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotificationFacade() {
        super(Notification.class);
    }

    @Override
    public Notification creerNotif(Object uti, String mess) {
        Notification n = new Notification();
        n.setDateNotif(new Date());
        n.setMessage(mess);
        if (uti instanceof UtilisateurHardis)
           n.setUtilisateur((UtilisateurHardis)uti);
        else if (uti instanceof Client)
           n.setClient((Client)uti);
        n.setVisible(true);
        em.persist(n);
        return n;
    }

    @Override
    public List<Notification> rechercheNotif(Client u) {
        Query requete = em.createQuery("SELECT p from Notification as p where p.client.id=:id and p.visible=true order by p.dateNotif desc");
        requete.setParameter("id",u.getId());     
        return  requete.getResultList();
       
    }
    
    
    
    
    
    
    
}

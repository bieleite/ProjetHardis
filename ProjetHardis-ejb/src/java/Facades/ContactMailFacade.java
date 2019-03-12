/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.ContactMail;
import Entites.UtilisateurHardis;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 6170361
 */
@Stateless
public class ContactMailFacade extends AbstractFacade<ContactMail> implements ContactMailFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContactMailFacade() {
        super(ContactMail.class);
    }

    @Override
    public void creerContactMail(String nom, String prenom, String mail, String tel, String sujet, String message) {
        ContactMail cm = new ContactMail();
        cm.setEmail(mail);
        cm.setMessage(message);
        cm.setNom(nom);
        cm.setPrenom(prenom);
        cm.setSujet(sujet);
        cm.setTel(tel);
        cm.setUtilisateurHardis(null);
        cm.setRepondu(false);
        em.persist(cm);
    }

    @Override
    public void majUtilisateurH(ContactMail cm, UtilisateurHardis u) {
        cm.setUtilisateurHardis(u);
        em.merge(cm);
    }

    @Override
    public void majReponse(ContactMail cm) {
        cm.setRepondu(true);
        em.merge(cm);
    }
    
    
    
    
    
}

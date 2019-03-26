/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.ContactMail;
import Entites.UtilisateurHardis;
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
    public ContactMail creerContactMail(String nom, String prenom, String mail, String tel, String sujet, String message, String societe) {
        ContactMail cm = new ContactMail();
        cm.setEmail(mail);
        cm.setSociete(societe);
        cm.setMessage(message);
        cm.setNom(nom);
        cm.setPrenom(prenom);
        cm.setSujet(sujet);
        cm.setTel(tel);
        cm.setUtilisateurHardis(null);
        cm.setRepondu(false);
        em.persist(cm);
        return cm;
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
    
    @Override
    public List<ContactMail> listCommunication() {
        String txt="SELECT co FROM ContactMail AS co ";
        Query req=getEntityManager().createQuery(txt);
        List<ContactMail> result=req.getResultList();
        return result;
    }
    
    @Override
    public ContactMail rechercheCommunication(long id) {
        ContactMail co = null;        
        String txt = "SELECT co FROM ContactMail AS co WHERE co.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);  
        List<ContactMail> res = req.getResultList();
        if (res.size() >= 1)
        {
              co = (ContactMail) res.get(0);
        }
        return co;
    }
    
    @Override
    public  List<ContactMail> rechercheCommunicationParUtilisateurHardis(UtilisateurHardis utilisateurHardis) {        
        String txt = "SELECT co FROM ContactMail AS co WHERE co.utilisateurHardis=:utilisateurHardis ";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("utilisateurHardis", utilisateurHardis);
        List<ContactMail> co = req.getResultList();
        return co;
    }
    
    @Override
    public  ContactMail modifContactMailRepondu(ContactMail co) {
               
        String txt = "SELECT co FROM ContactMail AS co WHERE co.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", co.getId());
        List<ContactMail> res = req.getResultList();
        if (res.size() >= 1)
        {
            co.setRepondu(true);
            em.merge(co);
        }
        return co;
    }
    
    @Override
    public List<ContactMail> listCommunicationNonRepondu() {
        String txt = "SELECT co FROM ContactMail AS co WHERE co.repondu=0 ";
        Query req = getEntityManager().createQuery(txt);
        List<ContactMail> co = req.getResultList();
        return co;
    }
    
    
}

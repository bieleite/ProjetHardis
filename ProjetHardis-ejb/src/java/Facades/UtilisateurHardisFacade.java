/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.UtilisateurHardis;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gabrielleite
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
    
}

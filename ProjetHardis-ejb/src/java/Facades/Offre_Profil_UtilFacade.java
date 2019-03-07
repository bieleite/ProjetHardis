/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entites.Offre_Profil_Util;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gabrielleite
 */
@Stateless
public class Offre_Profil_UtilFacade extends AbstractFacade<Offre_Profil_Util> implements Offre_Profil_UtilFacadeLocal {

    @PersistenceContext(unitName = "ProjetHardis-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Offre_Profil_UtilFacade() {
        super(Offre_Profil_Util.class);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadatak.app.entity.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import zadatak.app.entity.Role;

/**
 *
 * @author borisdom
 */
@Stateless
public class RoleFacade extends AbstractFacade<Role> implements RoleFacadeLocal {

    @PersistenceContext(unitName = "Task_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoleFacade() {
        super(Role.class);
    }
    
}

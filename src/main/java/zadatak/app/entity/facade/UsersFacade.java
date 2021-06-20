package zadatak.app.entity.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import zadatak.app.entity.Users;

@Stateless
public class UsersFacade extends AbstractFacade<Users> implements UsersFacadeLocal{

    @PersistenceContext(unitName = "Task_PU")
    private EntityManager entityManager;
    
    public UsersFacade() {
        super(Users.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
    

    
}

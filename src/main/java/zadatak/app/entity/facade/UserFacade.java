package zadatak.app.entity.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import zadatak.app.entity.User;


@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext(unitName = "Task_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        Query query = em.createNamedQuery("User.findByUsername");
        query.setParameter("username", username);
        List resultList = query.getResultList();
        return resultList.isEmpty() ? null : (User) resultList.iterator().next();
    }

}

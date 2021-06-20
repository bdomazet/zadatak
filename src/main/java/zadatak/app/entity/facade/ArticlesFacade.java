package zadatak.app.entity.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import zadatak.app.entity.Articles;

@Stateless
public class ArticlesFacade extends AbstractFacade<Articles> implements ArticlesFacadeLocal{

    @PersistenceContext(unitName = "Task_PU")
    private EntityManager entityManager;
    
    public ArticlesFacade() {
        super(Articles.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    
}

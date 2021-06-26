package zadatak.app.entity.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import zadatak.app.entity.Article;

@Stateless
public class ArticleFacade extends AbstractFacade<Article> implements ArticleFacadeLocal {

    @PersistenceContext(unitName = "Task_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticleFacade() {
        super(Article.class);
    }
    
}

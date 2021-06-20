
package zadatak.app.entity.facade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;


public abstract class AbstractFacade<E> {
    private Class<E> entityClass;
    
    public AbstractFacade(Class<E> entityClass){
        this.entityClass = entityClass;
    }
    
    protected abstract EntityManager getEntityManager();
    
    public void create(E entity){
        getEntityManager().persist(entityClass);
    }
    
    public void edit(E entity){
        getEntityManager().merge(entity);
    }
    
    public void remove(E entity){
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    
    public E find(Object id){
        return getEntityManager().find(entityClass, id);
    }
    
    public List<E> findAll(){
        CriteriaQuery criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(entityClass));
        Query query = getEntityManager().createQuery(criteriaQuery);
        return  query.getResultList();
    }
    
    
}

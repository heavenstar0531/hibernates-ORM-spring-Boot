package ru.lionsoft.javaee.jpa.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ru.lionsoft.javaee.jpa.entities.Company;

/**
 * Класс фасад для работы с сущностью Company в БД
 * 
 * @author Igor Morenko <morenko at lionsoft.ru>
 */
@Stateless
public class CompanyFacade {

    @PersistenceContext(unitName = "HelloJPA-PU")
    private EntityManager entityManager;

    private final Class entityClass = Company.class;
    
    public CompanyFacade() {
    }

    public void create(Company entity) {
        entityManager.persist(entity);
    }

    public void edit(Company entity) {
        entityManager.merge(entity);
    }

    public void remove(Company entity) {
        entityManager.remove(entityManager.merge(entity));
    }

    public Company find(Object id) {
        return (Company) entityManager.find(entityClass, id);
    }

    public List<Company> findAll() {
        CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Company> findRange(int[] range) {
        CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = entityManager.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        Root<Company> rt = cq.from(entityClass);
        cq.select(entityManager.getCriteriaBuilder().count(rt));
        Query q = entityManager.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}

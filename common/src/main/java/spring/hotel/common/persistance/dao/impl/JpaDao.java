package spring.hotel.common.persistance.dao.impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import spring.hotel.common.persistance.dao.IJpaDao;
import spring.hotel.common.persistance.exception.DaoException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Yayheniy_Lepkovich on 10/31/2016.
 */

@Repository
public abstract class JpaDao<T> implements IJpaDao<T> {
    @Setter @Getter
    @PersistenceContext
    @Qualifier("prod_entity")
    protected EntityManager entityManager;

    @Setter
    private Class<T> entityClass;

    protected JpaDao(){
    }


    public T findById(long id){
        return entityManager.find(entityClass, id);
    }

    public List<T> getAll(){
        String className = entityClass.getSimpleName();
        return entityManager.createQuery("select s from " + className + " s").getResultList();
    }

    public void add(T entity) throws DaoException {
        entityManager.persist(entity);
    }

    public void update(T entity){
        entityManager.merge(entity);
    }

//    public void delete(T entity){
//        entityManager.find(entityClass, entity);
//        entityManager.remove(entity);
//    }

    public void deleteById(long id){
        T entity = entityManager.find(entityClass, id);
        entityManager.remove(entity);
//        delete(entity);
    }
}

package spring.hotel.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.hotel.common.persistance.dao.IJpaDao;
import spring.hotel.common.persistance.dao.impl.JpaDao;
import spring.hotel.common.persistance.exception.DaoException;

import java.util.List;

/**
 * Created by Yayheniy_Lepkovich on 11/2/2016.
 */
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class JpaCrudService<T> implements IJpaDao<T> {
    @Autowired
    private JpaDao jpaDao;

    protected JpaCrudService(){}

    @Override
    public T findById(long id) {
        return (T) jpaDao.findById(id);
    }

    @Override
    public List<T> getAll() {
        return jpaDao.getAll();
    }

    @Override
    public void add(T entity) throws DaoException {
        jpaDao.add(entity);
    }

    @Override
    public void update(T entity) {
        jpaDao.update(entity);
    }

//    @Override
//    @Transactional
//    public void delete(T entity) {
//        jpaDao.delete(entity);
//    }

    @Override
    public void deleteById(long id) {
        jpaDao.deleteById(id);
    }
}

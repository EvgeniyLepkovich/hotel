package spring.hotel.common.persistance.dao;


import spring.hotel.common.persistance.exception.DaoException;

import java.util.List;

/**
 * Created by Yayheniy_Lepkovich on 10/31/2016.
 */
public interface IJpaDao<T> {
    T findById(long id);
    List<T> getAll();
    void add(T entity) throws DaoException;
    void update(T entity);
//    void delete(T entity);
    void deleteById(long id);
}

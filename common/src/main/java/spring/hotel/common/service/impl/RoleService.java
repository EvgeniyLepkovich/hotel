package spring.hotel.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.hotel.common.persistance.dao.RoleDao;
import spring.hotel.common.persistance.to.Role;
import spring.hotel.common.service.IRoleService;

@Service
@Transactional
public class RoleService implements IRoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public <S extends Role> S save(S entity) {
        return roleDao.save(entity);
    }

    @Override
    public <S extends Role> Iterable<S> save(Iterable<S> entities) {
        return roleDao.save(entities);
    }

    @Override
    public Role findOne(Long id) {
        return roleDao.findOne(id);
    }

    @Override
    public boolean exists(Long id) {
        return roleDao.exists(id);
    }

    @Override
    public Iterable<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public long count() {
        return roleDao.count();
    }

    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }

    @Override
    public void delete(Role entity) {
        roleDao.delete(entity);
    }

    @Override
    public void delete(Iterable<? extends Role> entities) {
        roleDao.delete(entities);
    }

    @Override
    public void deleteAll() {
        roleDao.deleteAll();
    }
}

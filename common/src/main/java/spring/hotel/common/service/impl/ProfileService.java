package spring.hotel.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.hotel.common.persistance.dao.ProfileDao;
import spring.hotel.common.persistance.to.Profile;
import spring.hotel.common.service.IProfileService;

@Service
@Transactional
public class ProfileService implements IProfileService {
    @Autowired
    private ProfileDao profileDao;

    @Override
    public <S extends Profile> S save(S entity) {
        return profileDao.save(entity);
    }

    @Override
    public <S extends Profile> Iterable<S> save(Iterable<S> entities) {
        return profileDao.save(entities);
    }

    @Override
    public Profile findOne(Long id) {
        return profileDao.findOne(id);
    }

    @Override
    public boolean exists(Long id) {
        return profileDao.exists(id);
    }

    @Override
    public Iterable<Profile> findAll() {
        return profileDao.findAll();
    }

    @Override
    public long count() {
        return profileDao.count();
    }

    @Override
    public void delete(Long id) {
        profileDao.delete(id);
    }

    @Override
    public void delete(Profile entity) {
        profileDao.delete(entity);
    }

    @Override
    public void delete(Iterable<? extends Profile> entities) {
        profileDao.delete(entities);
    }

    @Override
    public void deleteAll() {
        profileDao.deleteAll();
    }
}

package spring.hotel.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.hotel.common.persistance.dao.AccountDao;
import spring.hotel.common.persistance.to.Account;
import spring.hotel.common.service.IAccountService;

@Service
@Transactional
public class AccountService implements IAccountService {
    @Autowired
    private AccountDao accountDao;


    @Override
    public <S extends Account> S save(S entity) {
        return accountDao.save(entity);
    }

    @Override
    public <S extends Account> Iterable<S> save(Iterable<S> entities) {
        return accountDao.save(entities);
    }

    @Override
    public Account findOne(Long id) {
        return accountDao.findOne(id);
    }

    @Override
    public boolean exists(Long id) {
        return accountDao.exists(id);
    }

    @Override
    public Iterable<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public long count() {
        return accountDao.count();
    }

    @Override
    public void delete(Long id) {
        accountDao.delete(id);
    }

    @Override
    public void delete(Account entity) {
        accountDao.delete(entity);
    }

    @Override
    public void delete(Iterable<? extends Account> entities) {
        accountDao.delete(entities);
    }

    @Override
    public void deleteAll() {
        accountDao.deleteAll();
    }
}

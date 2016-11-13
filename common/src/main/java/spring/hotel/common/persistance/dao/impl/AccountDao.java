package spring.hotel.common.persistance.dao.impl;

import org.springframework.stereotype.Repository;
import spring.hotel.common.persistance.dao.IAccountDao;
import spring.hotel.common.persistance.to.Account;

/**
 * Created by Fene4ka_ on 13.11.2016.
 */
@Repository
public class AccountDao extends JpaDao<Account> implements IAccountDao {

    private static final String GET_ACCOUNT_BY_USERNAME = "SELECT a FROM Account a WHERE username = :username";

    @Override
    public Account getAccountByUsername(String username) {
        return (Account) entityManager.createQuery(GET_ACCOUNT_BY_USERNAME, Account.class).setParameter("username", username).getSingleResult();
    }
}

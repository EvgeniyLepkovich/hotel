package spring.hotel.common.persistance.dao;

import spring.hotel.common.persistance.to.Account;

/**
 * Created by Fene4ka_ on 13.11.2016.
 */
public interface IAccountDao extends IJpaDao<Account> {
    Account getAccountByUsername(String username);
}

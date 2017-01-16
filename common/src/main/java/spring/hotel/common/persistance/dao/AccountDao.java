package spring.hotel.common.persistance.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.hotel.common.persistance.to.Account;

@Repository
public interface AccountDao extends CrudRepository<Account, Long> {
    Account getAccountByUsername(String username);
}

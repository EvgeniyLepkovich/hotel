package dbunit;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import spring.hotel.common.config.TestDataBaseConfig;
import spring.hotel.common.persistance.dao.AccountDao;
import spring.hotel.common.persistance.to.Account;

/**
 * Created by Fene4ka_ on 13.11.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataBaseConfig.class}, loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DatabaseTearDown(value = "/data/dbTearDown.xml")
@DirtiesContext
public class AccountDaoTest {
    @Autowired
    private AccountDao accountDao;

    @Test
    @DatabaseSetup(value = "/data/account/accountData.xml")
    public void getAccountByUsername() throws Exception {
        Account account = accountDao.getAccountByUsername("admin");
        Assert.assertEquals("admin", account.getUsername());
        Assert.assertEquals("admin", account.getRoles().get(0).getNameRole());
    }
}
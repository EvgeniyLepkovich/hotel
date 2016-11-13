package spring.hotel.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by Fene4ka_ on 13.11.2016.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(value = {"spring.hotel.common.persistance", "spring.hotel.common.service"})
@PropertySource("classpath:db.properties")
public class TestDataBaseConfig extends DataBase {
    @Value("${dbtest.driver}")
    private String driver;
    @Value("${dbtest.url}")
    private String url;
    @Value("${dbtest.username}")
    private String username;
    @Value("${dbtest.password}")
    private String password;

    @Value("${dbtest.dialect}")
    private String dialect;
    @Value("${dbtest.showsql}")
    private String show_sql;
    @Value("${dbtest.hbm2ddl_auto}")
    private String hbm2ddl_auto;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        return super.entityManagerFactory(hibernateProp(dialect, show_sql, hbm2ddl_auto), dataSourceProp(driver, url, username, password));
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        return super.transactionManager(hibernateProp(dialect, show_sql, hbm2ddl_auto), dataSourceProp(driver, url, username, password));
    }

    @Bean
    public DataSource dataSource(){
        return super.dataSource(dataSourceProp(driver, url, username, password));
    }
}

package spring.hotel.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
@EnableJpaRepositories(value = {"spring.hotel.common.persistance.dao"})
@PropertySource("classpath:db.properties")
public class ProductionDataBaseConfig extends DataBase{
    @Value("${db.driver}")
    private String driver;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Value("${db.dialect}")
    private String dialect;
    @Value("${db.showsql}")
    private String show_sql;
    @Value("${db.hbm2ddl_auto}")
    private String hbm2ddl_auto;

    @Bean("prod_entity")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        return super.entityManagerFactory(hibernateProp(dialect, show_sql, hbm2ddl_auto), dataSourceProp(driver, url, username, password));
    }

    @Bean
    @Primary
    public JpaTransactionManager transactionManager() {
        return super.transactionManager(hibernateProp(dialect, show_sql, hbm2ddl_auto), dataSourceProp(driver, url, username, password));
    }

    @Bean("prod_ds")
    @Primary
    public DataSource dataSource(){
        return super.dataSource(dataSourceProp(driver, url, username, password));
    }


}

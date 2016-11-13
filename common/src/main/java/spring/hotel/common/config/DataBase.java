package spring.hotel.common.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Fene4ka_ on 13.11.2016.
 */
public abstract class DataBase {
    private static final String[] PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = {"spring.hotel.common.persistance", "spring.hotel.common.service"};

    protected LocalContainerEntityManagerFactoryBean entityManagerFactory(Properties hibernateProp, Properties dataSourceProp) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource(dataSourceProp));
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setJpaProperties(hibernateProp);
        return entityManagerFactoryBean;
    }

    protected JpaTransactionManager transactionManager(Properties hibernateProp, Properties dataSourceProp) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory(hibernateProp, dataSourceProp).getObject());
        transactionManager.setDataSource(dataSource(dataSourceProp));
        return transactionManager;
    }


    protected DataSource dataSource(Properties dataSourceProp) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceProp.getProperty("driver"));
        dataSource.setUrl(dataSourceProp.getProperty("url"));
        dataSource.setUsername(dataSourceProp.getProperty("username"));
        dataSource.setPassword(dataSourceProp.getProperty("password"));
        return dataSource;
    }

    public Properties dataSourceProp(String driver, String url, String username, String password){
        Properties properties = new Properties();
        properties.put("driver", driver);
        properties.put("url", url);
        properties.put("username", username);
        properties.put("password", password);
        return properties;
    }

    public Properties hibernateProp(String dialect, String show_sql, String hbm2ddl_auto) {
        Properties properties = new Properties();
        properties.put("hibernate.enable_lazy_load_no_trans", true);
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", show_sql);
        properties.put("hibernate.hbm2ddl.auto", hbm2ddl_auto);
        return properties;
    }
}

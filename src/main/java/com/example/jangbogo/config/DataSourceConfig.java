package com.example.jangbogo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    ApplicationContext context;
    Environment environment;

    public DataSourceConfig(ApplicationContext context) {
        this.context = context;
        this.environment = context.getEnvironment();
    }

//    @Bean
//    @Primary
//    public DataSource getDataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
//        dataSourceBuilder.url("jdbc:mysql://localhost:3306/petclinic?useUnicode=true");
//        dataSourceBuilder.username("petclinic");
//        dataSourceBuilder.password("petclinic");
//        return dataSourceBuilder.build();
//    }

    @Bean
    @Qualifier("jangbogo")
    public DataSource getJangbogoDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(environment.getProperty("jangbogo.driverClassName"));
        dataSourceBuilder.url(environment.getProperty("jangbogo.url"));
        dataSourceBuilder.username(environment.getProperty("jangbogo.username"));
        dataSourceBuilder.password(environment.getProperty("jangbogo.password"));
        return dataSourceBuilder.build();
    }

    @Bean
    @Qualifier("erp")
    public DataSource getErpDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(environment.getProperty("erp.driverClassName"));
        dataSourceBuilder.url(environment.getProperty("erp.url"));
        dataSourceBuilder.username(environment.getProperty("erp.username"));
        dataSourceBuilder.password(environment.getProperty("erp.password"));
        return dataSourceBuilder.build();
    }


}

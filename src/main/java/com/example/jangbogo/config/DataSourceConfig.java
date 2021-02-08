package com.example.jangbogo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/petclinic?useUnicode=true");
        dataSourceBuilder.username("petclinic");
        dataSourceBuilder.password("petclinic");
        return dataSourceBuilder.build();
    }

    @Bean
    @Qualifier("jangbogo")
    public DataSource getJangbogoDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://34.64.253.34:3306/jangbogovrp?useUnicode=true");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("jangfood");
        return dataSourceBuilder.build();
    }


}

package com.example.jangbogo.repository.jdbc;

import com.example.jangbogo.config.DataSourceConfig;
import com.example.jangbogo.rest.AuthUserRestController;
import com.example.jangbogo.rest.CompanyRestController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, JdbcErpRepositoryImpl.class})
class JdbcErpRepositoryImplTest {

    @Autowired
    private JdbcErpRepositoryImpl jdbcErpRepository;

    @Test
    public void testGetOrders() throws SQLException {
        assertThat(jdbcErpRepository.getOrders(true, "003")).hasSizeGreaterThanOrEqualTo(1);
        assertNotNull(jdbcErpRepository.getOrders(true, "003"));
    }
}
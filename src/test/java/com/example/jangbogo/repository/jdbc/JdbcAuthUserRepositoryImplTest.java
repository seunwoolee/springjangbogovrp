package com.example.jangbogo.repository.jdbc;

import com.example.jangbogo.config.DataSourceConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= DataSourceConfig.class)
class JdbcAuthUserRepositoryImplTest {

}
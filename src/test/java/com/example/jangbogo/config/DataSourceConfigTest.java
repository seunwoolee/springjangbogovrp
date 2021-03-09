package com.example.jangbogo.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= DataSourceConfig.class)
@WebAppConfiguration
public class DataSourceConfigTest {

    @Autowired
    @Qualifier("erp")
    DataSource dataSource;

    @Test
    public void testErpDataSource() throws SQLException {
        Connection connection = dataSource.getConnection();
        assertNotNull(connection);
    }
}
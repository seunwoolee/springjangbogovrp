package com.example.jangbogo;

import com.example.jangbogo.config.DataSourceConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= DataSourceConfig.class)
@WebAppConfiguration
class JangbogoApplicationTests {

    @Test
    void contextLoads() {
    }

}

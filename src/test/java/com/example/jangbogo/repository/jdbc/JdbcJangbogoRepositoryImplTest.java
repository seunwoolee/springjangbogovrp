package com.example.jangbogo.repository.jdbc;

import com.example.jangbogo.DTO.Company;
import com.example.jangbogo.DTO.Customer;
import com.example.jangbogo.config.DataSourceConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, JdbcJangbogoRepositoryImpl.class})
class JdbcJangbogoRepositoryImplTest {
    @Autowired
    JdbcJangbogoRepositoryImpl jdbcJangbogoRepository;

    String GUEEST_ID = "347048" ;
    Double lat = 35.6579850000;
    Double lon = 128.4040940000;
    String customer_id = "dd" ;
    String name = "dd" ;
    String address = "dd" ;
    int course_number = 1;

    @Test
    void getCustomer() {
        assertThat(jdbcJangbogoRepository.getCustomer(GUEEST_ID, lat, lon)).isInstanceOf(Customer.class);
    }

    @Test
    void getCustomerCount() {
        assertThat(jdbcJangbogoRepository.getCustomer(GUEEST_ID)).isInstanceOf(Integer.class);
        assertThat(jdbcJangbogoRepository.getCustomer(GUEEST_ID)).isEqualTo(1);
    }

    @Test
    void getDeliveries() throws ParseException {
        Company company = new Company();
        company.setId(2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = "2021-03-19" ;
        String endDate = "2021-03-19" ;
        assertThat(jdbcJangbogoRepository.getDeliveries(company, simpleDateFormat.parse(startDate), simpleDateFormat.parse(endDate))).hasSizeGreaterThanOrEqualTo(1);
    }

//    @Test
//    void createCustomer() {
//
//        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
//        namedParameters.addValue("customer_id", customer_id);
//        namedParameters.addValue("name", name);
//        namedParameters.addValue("address", address);
//        namedParameters.addValue("latitude", lat);
//        namedParameters.addValue("longitude", lon);
//        namedParameters.addValue("course_number",course_number);
//        assertThat(jdbcJangbogoRepository.createCustomer(namedParameters)).isInstanceOf(Customer.class);
//    }
//
//    @Test
//    void createOrder() {
//        Map<String, Object> params = new HashMap<>();
//        params.put("order_id", "121212");
//        params.put("company_id", 2);
//        params.put("date", new Date());
//        params.put("customer_id", 2);
//        params.put("price", 1);
//        params.put("is_am", 0);
//        jdbcJangbogoRepository.createOrder(params);
//    }
}

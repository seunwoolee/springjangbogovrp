package com.example.jangbogo.repository.jdbc;

import com.example.jangbogo.DTO.Company;
import com.example.jangbogo.DTO.Customer;
import com.example.jangbogo.repository.JangbogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
@Profile("jdbc")
public class JdbcJangbogoRepositoryImpl implements JangbogoRepository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcJangbogoRepositoryImpl(@Qualifier("jangbogo") DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.insertUser = new SimpleJdbcInsert(dataSource).withTableName("users");
    }

    @Override
    public Collection<Company> findAll() throws DataAccessException {
        List<Company> companies = new ArrayList<>();
        companies.addAll(this.namedParameterJdbcTemplate.query(
                "SELECT * FROM company_company",
                BeanPropertyRowMapper.newInstance(Company.class)));
        return companies;
    }

    @Override
    public Company getCompany(String key) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("key", key);
        String query = new StringBuilder()
                .append("select cc.* ")
                .append("from authtoken_token ")
                .append("left join auth_user au on au.id = authtoken_token.user_id ")
                .append("left join company_company cc on au.id = cc.manager_id ")
                .append("WHERE authtoken_token.key= :key").toString();
        return this.namedParameterJdbcTemplate.queryForObject(query, params, BeanPropertyRowMapper.newInstance(Company.class));
    }

    @Override
    public Customer getCustomer(String guestId, Double lat, Double lon) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("guestId", guestId);
        params.put("lat", lat);
        params.put("lon", lon);
        String query = new StringBuilder()
                .append("select * from customer_customer ")
                .append("where customer_id = :guestId ")
                .append("and latitude = :lat and longitude = :lon ").toString();
        return this.namedParameterJdbcTemplate.queryForObject(query, params, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public Customer getCustomer(int pk) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("pk", pk);
        String query = new StringBuilder()
                .append("select * from customer_customer ")
                .append("where id = :pk ").toString();
        return this.namedParameterJdbcTemplate.queryForObject(query, params, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public int getCustomer(String guestId) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("guestId", guestId);

        String query = new StringBuilder()
                .append("select count(*) from customer_customer ")
                .append("where customer_id = :guestId ").toString();
        return this.namedParameterJdbcTemplate.queryForObject(query, params, Integer.class);
    }

    @Override
    public void updateCustomer(Customer customer) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("courseNumber", customer.getCourse_number());
        params.put("id", customer.getCustomer_id());
        params.put("name", customer.getName());
        String query = new StringBuilder()
                .append("UPDATE customer_customer SET course_number=:courseNumber, ")
                .append("name=:name WHERE id=:id ").toString();
        this.namedParameterJdbcTemplate.update(query, params);
    }

    @Override
    public Customer createCustomer(MapSqlParameterSource params) throws DataAccessException {
        params.addValue("created",new Date());
        params.addValue("modified",new Date());
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        this.namedParameterJdbcTemplate.update(
                "INSERT INTO customer_customer(customer_id, name, address, latitude, longitude, course_number, created, modified)" +
                        " VALUES (:customer_id, :name, :address, :latitude, :longitude, :course_number, :created, :modified)", params, holder);
        int pk = holder.getKey().intValue();
        return this.getCustomer(pk);
    }

    @Override
    public void createOrder(Map<String, Object> params) throws DataAccessException {
        params.put("created",new Date());
        params.put("modified",new Date());
        this.namedParameterJdbcTemplate.update(
                "INSERT INTO customer_order(order_id, price, customer_id, company_id, date, is_am, created, modified)" +
                        " VALUES (:order_id, :price, :customer_id, :company_id, :date, :is_am, :created, :modified)", params);
    }

}

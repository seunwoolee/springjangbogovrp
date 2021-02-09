package com.example.jangbogo.repository.jdbc;

import com.example.jangbogo.DTO.Company;
import com.example.jangbogo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
@Profile("jdbc")
public class JdbcCompanyRepositoryImpl implements CompanyRepository {
    private JdbcTemplate jdbcTemplate;
    //    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcCompanyRepositoryImpl(@Qualifier("jangbogo") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
//        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.insertUser = new SimpleJdbcInsert(dataSource).withTableName("users");
    }

    @Override
    public Collection<Company> findAll() throws DataAccessException {
        List<Company> companies = new ArrayList<>();
        companies.addAll(this.jdbcTemplate.query(
                "SELECT * FROM company_company",
                BeanPropertyRowMapper.newInstance(Company.class)));
        return companies;
    }

//    @Override
//    public void save(User user) throws DataAccessException {
//
//        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
//
//        try {
//            getByUsername(user.getUsername());
//            this.namedParameterJdbcTemplate.update("UPDATE users SET password=:password, enabled=:enabled WHERE username=:username", parameterSource);
//        } catch (EmptyResultDataAccessException e) {
//            this.insertUser.execute(parameterSource);
//        } finally {
//            updateUserRoles(user);
//        }
//    }
//
//    private User getByUsername(String username) {
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("username", username);
//        return this.namedParameterJdbcTemplate.queryForObject("SELECT * FROM users WHERE username=:username",
//                params, BeanPropertyRowMapper.newInstance(User.class));
//    }
//
//    private void updateUserRoles(User user) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("username", user.getUsername());
//        this.namedParameterJdbcTemplate.update("DELETE FROM roles WHERE username=:username", params);
//        for (Role role : user.getRoles()) {
//            params.put("role", role.getName());
//            if (role.getName() != null) {
//                this.namedParameterJdbcTemplate.update("INSERT INTO roles(username, role) VALUES (:username, :role)", params);
//            }
//        }
//    }
}

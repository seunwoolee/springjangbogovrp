package com.example.jangbogo.repository.jdbc;

import com.example.jangbogo.DTO.AuthUser;
import com.example.jangbogo.DTO.Company;
import com.example.jangbogo.model.Vet;
import com.example.jangbogo.repository.AuthUserRepository;
import com.example.jangbogo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
@Profile("jdbc")
public class JdbcAuthUserRepositoryImpl implements AuthUserRepository {
//    private JdbcTemplate jdbcTemplate;
    //    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//    private SimpleJdbcInsert insertUser;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public JdbcAuthUserRepositoryImpl(@Qualifier("jangbogo") DataSource dataSource) {
//        this.insertUser = new SimpleJdbcInsert(dataSource).withTableName("users");
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public AuthUser login(String username, String password) throws DataAccessException {
        AuthUser authUser;
        Map<String, Object> authUser_params = new HashMap<>();
        authUser_params.put("username", username);
//        authUser_params.put("password", password);

        authUser = this.namedParameterJdbcTemplate.queryForObject(
                "SELECT * FROM auth_user WHERE username= :username",
                authUser_params,
                BeanPropertyRowMapper.newInstance(AuthUser.class));

        return authUser;
    }
}

package com.example.jangbogo.repository;

import com.example.jangbogo.model.User;
import org.springframework.dao.DataAccessException;

public interface UserRepository {

    void save(User user) throws DataAccessException;
}

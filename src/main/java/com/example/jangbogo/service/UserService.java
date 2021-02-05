package com.example.jangbogo.service;


import com.example.jangbogo.model.User;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface UserService {

    void saveUser(User user) throws Exception;
    Collection<User> findAllUsers() throws DataAccessException;

}

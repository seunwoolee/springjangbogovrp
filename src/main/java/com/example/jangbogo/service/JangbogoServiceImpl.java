package com.example.jangbogo.service;

import com.example.jangbogo.DTO.AuthUser;
import com.example.jangbogo.DTO.Company;
import com.example.jangbogo.repository.AuthUserRepository;
import com.example.jangbogo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class JangbogoServiceImpl implements JangbogoService {
    CompanyRepository companyRepository;
    AuthUserRepository authUserRepository;

    @Autowired
    public JangbogoServiceImpl(CompanyRepository companyRepository, AuthUserRepository authUserRepository) {
        this.companyRepository = companyRepository;
        this.authUserRepository = authUserRepository;
    }

    @Override
    public Collection<Company> findCompanies() throws DataAccessException {
        return companyRepository.findAll();
    }

    @Override
    public AuthUser login(String username, String password) throws DataAccessException {
        return authUserRepository.login(username, password);
    }
}

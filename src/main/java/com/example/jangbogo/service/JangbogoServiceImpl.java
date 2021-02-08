package com.example.jangbogo.service;

import com.example.jangbogo.model.Company;
import com.example.jangbogo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class JangbogoServiceImpl implements JangbogoService {
    CompanyRepository companyRepository;

    @Autowired
    public JangbogoServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Collection<Company> findCompanies() throws DataAccessException {
        return companyRepository.findAll();
    }
}

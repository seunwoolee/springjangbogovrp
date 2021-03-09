/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.jangbogo.repository;

import com.example.jangbogo.DTO.Company;
import com.example.jangbogo.DTO.Customer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.Collection;
import java.util.Map;

public interface JangbogoRepository {
    Collection<Company> findAll() throws DataAccessException;

    Company getCompany(String key) throws DataAccessException;

    Customer getCustomer(String guestId, Double lat, Double lon) throws DataAccessException;

    Customer getCustomer(int pk) throws DataAccessException;

    int getCustomer(String guestId) throws DataAccessException;

    void updateCustomer(Customer customer) throws DataAccessException;

    Customer createCustomer(MapSqlParameterSource params) throws DataAccessException;

    void createOrder(Map<String, Object> params) throws DataAccessException;

}

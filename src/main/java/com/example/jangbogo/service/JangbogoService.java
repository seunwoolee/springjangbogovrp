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
package com.example.jangbogo.service;

import com.example.jangbogo.DTO.AuthUser;
import com.example.jangbogo.DTO.Company;
import com.example.jangbogo.DTO.Customer;
import com.example.jangbogo.DTO.Order;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public interface JangbogoService {
	Collection<Company> findCompanies() throws DataAccessException;
	Company getCompany(String key) throws DataAccessException;
	AuthUser login(String username, String password) throws DataAccessException;
	Collection<Order> getOrders(Boolean isAm, Company company) throws DataAccessException, SQLException;
	void updateCourseNumber(String guestId, int toCourseNumber) throws DataAccessException, SQLException;
	void saveGeolocation(String orderNumber, String lat, String lon, String companyCode) throws DataAccessException, SQLException;
	void removePrevRouteM(Boolean isAm, Company company) throws DataAccessException, SQLException;
	Customer createCustomers(Order order) throws DataAccessException, SQLException;
	void createOrder(Map<String, Object> params) throws DataAccessException, SQLException;
	HashMap<String, Object> validateCustomers(Collection<Order> orders) throws DataAccessException, SQLException;
}

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

import com.example.jangbogo.DTO.Order;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public interface ErpRepository {
    Collection<Order> getOrders(Boolean isAm, String companyCode) throws DataAccessException, SQLException;
    void updateCourseNumber(String guestId, int toCourseNumber) throws DataAccessException;
    void saveGeolocation(Map<String, Object> params) throws DataAccessException;
    void removePrevRouteM(Map<String, Object> params) throws DataAccessException;;
}

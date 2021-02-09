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
import org.springframework.dao.DataAccessException;

import java.util.Collection;


/**
 * Mostly used as a facade so all controllers have a single point of entry
 *
 * @author Michael Isvy
 * @author Vitaliy Fedoriv
 */
public interface JangbogoService {

//	Vet findVetById(int id) throws DataAccessException;
	Collection<Company> findCompanies() throws DataAccessException;
	AuthUser login(String username, String password) throws DataAccessException;
//	Collection<Vet> findAllVets() throws DataAccessException;
//	void saveVet(Vet vet) throws DataAccessException;
//	void deleteVet(Vet vet) throws DataAccessException;
}

/*
 * Copyright 2016-2018 the original author or authors.
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
package com.example.jangbogo.rest;

import com.example.jangbogo.DTO.Company;
import com.example.jangbogo.service.JangbogoService;
import com.example.jangbogo.util.DjangoAuth;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
public class CompanyRestController {

    private final JangbogoService jangbogoService;

    public CompanyRestController(JangbogoService jangbogoService) {
        this.jangbogoService = jangbogoService;
    }

    @RequestMapping(value = "api/companies", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<Company>> getAllCompanies() {
        Collection<Company> companies = new ArrayList<Company>();
        companies.addAll(this.jangbogoService.findCompanies());
        if (companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @RequestMapping(value = "company/get_company", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Company> getCompany(@RequestHeader Map<String, String> headers) {
        String key = DjangoAuth.getAuthToken(headers);
        Company company = this.jangbogoService.getCompany(key);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
}

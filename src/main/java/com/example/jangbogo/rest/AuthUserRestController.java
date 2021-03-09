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

import com.example.jangbogo.DTO.AuthUser;
import com.example.jangbogo.DTO.Company;
import com.example.jangbogo.model.Vet;
import com.example.jangbogo.service.JangbogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Vitaliy Fedoriv
 */

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("rest-auth/login/")
public class AuthUserRestController {

    private final JangbogoService jangbogoService;

    public AuthUserRestController(JangbogoService jangbogoService) {
        this.jangbogoService = jangbogoService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<AuthUser> login(@RequestBody AuthUser authUser) {
        try {
            AuthUser authUser1 = this.jangbogoService.login(authUser.getUsername(), authUser.getPassword());
            return new ResponseEntity<AuthUser>(authUser1, HttpStatus.OK);
        } catch (DataAccessException dataAccessException) {
            return new ResponseEntity<AuthUser>(HttpStatus.BAD_REQUEST);
        }
    }
}

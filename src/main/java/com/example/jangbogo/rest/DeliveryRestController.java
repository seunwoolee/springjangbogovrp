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
import com.example.jangbogo.DTO.Delivery;
import com.example.jangbogo.service.JangbogoService;
import com.example.jangbogo.util.DjangoAuth;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;


@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("delivery/")
public class DeliveryRestController {

    private final JangbogoService jangbogoService;

    public DeliveryRestController(JangbogoService jangbogoService) {
        this.jangbogoService = jangbogoService;
    }

    @RequestMapping(value = "deliveries", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<Delivery>> getDeliveries(
            @RequestParam(value = "startDate", required = true) String startDate,
            @RequestParam(value = "endDate", required = true) String endDate,
            @RequestHeader Map<String, String> headers
    ) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String key = DjangoAuth.getAuthToken(headers);
        Company company = this.jangbogoService.getCompany(key);
        Collection<Delivery> deliveries = this.jangbogoService.getDeliveries(company, simpleDateFormat.parse(startDate), simpleDateFormat.parse(endDate));
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }
}

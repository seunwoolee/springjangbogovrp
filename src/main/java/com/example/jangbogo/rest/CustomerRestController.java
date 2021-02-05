package com.example.jangbogo.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vitaliy Fedoriv
 *
 */

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api/customers")
public class CustomerRestController {

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public String getAllVets(){
        return "asfdasdf";
    }


}

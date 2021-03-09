package com.example.jangbogo.rest;

import com.example.jangbogo.DTO.*;
import com.example.jangbogo.service.JangbogoService;
import com.example.jangbogo.util.DjangoAuth;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("customer/")
public class CustomerRestController {
    private final JangbogoService jangbogoService;

    public CustomerRestController(JangbogoService jangbogoService) {
        this.jangbogoService = jangbogoService;
    }

    @RequestMapping(value = "preview_order/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<Order>> previewOrder(
            @RequestHeader Map<String, String> headers,
            @RequestParam(value = "isAm", required = true) Boolean isAm
    ) throws SQLException {
        String key = DjangoAuth.getAuthToken(headers);
        Company company = this.jangbogoService.getCompany(key);
        Collection<Order> orders = this.jangbogoService.getOrders(isAm, company);
        return new ResponseEntity<Collection<Order>>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "update_course_number/", method = RequestMethod.PATCH, produces = "application/json")
    public ResponseEntity<Void> updateCourseNumber(@RequestBody UpdateCourseVo updateCourseVo) throws SQLException {
        this.jangbogoService.updateCourseNumber(updateCourseVo.getGuest_id(), Integer.parseInt(updateCourseVo.getTo_course_number()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "save_geolocation/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Void> saveGeolocation(
            @RequestHeader Map<String, String> headers,
            @RequestBody SaveGeoVo saveGeoVo) throws SQLException {
        String key = DjangoAuth.getAuthToken(headers);
        Company company = this.jangbogoService.getCompany(key);
        this.jangbogoService.saveGeolocation(saveGeoVo.getOrderNumber(), saveGeoVo.getLat(), saveGeoVo.getLon(), company.getCode());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "create_customers/", method = RequestMethod.POST, produces = "application/json")
    @Transactional
    public HttpEntity<? extends Object> createCustomers(
            @RequestHeader Map<String, String> headers,
            @RequestBody CreateCustomersVo vo) throws SQLException {
        String key = DjangoAuth.getAuthToken(headers);
        Company company = this.jangbogoService.getCompany(key);

//        this.jangbogoService.removePrevRouteM(vo.getIs_am(), company);

        Collection<Order> orders = this.jangbogoService.getOrders(vo.getIsAm(), company);

        Map<String, Object> resultMap = this.jangbogoService.validateCustomers(orders);

        Boolean result = (Boolean) resultMap.get("result");

        if (!result) {
            String message = (String) resultMap.get("message");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        for (Order order : orders) {
            Customer customer = this.jangbogoService.createCustomers(order);
            Map<String, Object> params = new HashMap<>();
            params.put("order_id", order.getOrderNumber());
            params.put("company_id", company.getId());
            params.put("date", order.getDeliveryDate());
            params.put("customer_id", customer.getId());
            params.put("price", order.getPay());
            params.put("is_am", order.getIsAm());
            this.jangbogoService.createOrder(params);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}

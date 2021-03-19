package com.example.jangbogo.rest;

import com.example.jangbogo.DTO.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Collection;


public class TestRestController {
    public static String USERNAME = "lee";
    public static String PASSWORD = "seungwoo";
    public static String KEY = "asdfkqlwejrklqweklrjlqwer";

    protected final Collection<Order> orders = new ArrayList<>();
    protected Company company;
    protected AuthUser authUser;
    protected Customer customer;
    protected final Collection<Delivery> deliveries = new ArrayList<>();

    @BeforeEach
    public void TestRestController() {
        authUser = new AuthUser();
        authUser.setUsername(USERNAME);
        authUser.setPassword(PASSWORD);
        authUser.setKey(KEY);

        Order order1 = new Order();
        order1.setId(1);
        order1.setCourseNumber(1);
        orders.add(order1);

        Order order2 = new Order();
        order2.setId(2);
        order2.setCourseNumber(2);
        orders.add(order2);

        company = new Company();
        company.setId(1);
        company.setName("칠성");
        company.setAddress("칠성점");
        company.setLatitude("1.11");
        company.setLongitude("1.12");
        company.setCode("003");

        customer = new Customer();
        customer.setId(1);
        customer.setName("a");
        customer.setAddress("aa");
        customer.setLatitude(1.0);
        customer.setLongitude(1.0);
        customer.setCustomer_id("111");
        customer.setCourse_number(1);


        Delivery delivery1 = new Delivery();
        delivery1.setId(1);
        Delivery delivery2 = new Delivery();
        delivery2.setId(2);
        deliveries.add(delivery1);
        deliveries.add(delivery2);
    }

}

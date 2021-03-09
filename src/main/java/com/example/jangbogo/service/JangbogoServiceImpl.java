package com.example.jangbogo.service;

import com.example.jangbogo.DTO.AuthUser;
import com.example.jangbogo.DTO.Company;
import com.example.jangbogo.DTO.Customer;
import com.example.jangbogo.DTO.Order;
import com.example.jangbogo.repository.AuthUserRepository;
import com.example.jangbogo.repository.JangbogoRepository;
import com.example.jangbogo.repository.ErpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JangbogoServiceImpl implements JangbogoService {
    JangbogoRepository jangbogoRepository;
    AuthUserRepository authUserRepository;
    ErpRepository erpRepository;

    @Autowired
    public JangbogoServiceImpl(JangbogoRepository companyRepository,
                               AuthUserRepository authUserRepository,
                               ErpRepository erpRepository) {
        this.jangbogoRepository = companyRepository;
        this.authUserRepository = authUserRepository;
        this.erpRepository = erpRepository;
    }

    @Override
    public Collection<Company> findCompanies() throws DataAccessException {
        return jangbogoRepository.findAll();
    }

    @Override
    public Company getCompany(String key) throws DataAccessException {
        return jangbogoRepository.getCompany(key);
    }

    @Override
    public AuthUser login(String username, String password) throws DataAccessException {
        return authUserRepository.login(username, password);
    }

    @Override
    public Collection<Order> getOrders(Boolean isAm, Company company) throws DataAccessException, SQLException {
        return this.erpRepository.getOrders(isAm, company.getCode());
    }

    @Override
    public void updateCourseNumber(String guestId, int toCourseNumber) throws DataAccessException, SQLException {
        this.erpRepository.updateCourseNumber(guestId, toCourseNumber);
    }

    @Override
    public void saveGeolocation(String orderNumber, String lat, String lon, String companyCode) throws DataAccessException, SQLException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("orderNumber", orderNumber);
        map.put("lat", lat);
        map.put("lon", lon);
        map.put("companyCode", companyCode);
        this.erpRepository.saveGeolocation(map);
    }

    @Override
    public void removePrevRouteM(Boolean isAm, Company company) throws DataAccessException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("isAm", isAm);
        map.put("companyId", company.getId());
        map.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        this.erpRepository.removePrevRouteM(map);
    }

    @Override
    public HashMap<String, Object> validateCustomers(Collection<Order> orders) throws DataAccessException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", true);
        map.put("message", "");

        for (Order order : orders) {
            if (order.getLat() == null || order.getLon() == null) {
                map.put("result", false);
                map.put("message", "수집되지 않은 좌표가 있습니다.");
                return map;
            }

            if (order.getCourseNumber() == null || order.getCourseNumber() == 0) {
                map.put("result", false);
                map.put("message", "코스번호가 없는 거래처가 있습니다.");
                return map;
            }
        }

        return map;
    }

    @Override
    public Customer createCustomers(Order order) throws DataAccessException {
        Customer customer = jangbogoRepository.getCustomer(order.getGuestId(), order.getLat(), order.getLon());
        if (customer != null) {
            customer.setCourse_number(order.getCourseNumber());
            customer.setName(order.getName());
            jangbogoRepository.updateCustomer(customer);
        } else {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("customer_id", order.getGuestId());
            namedParameters.addValue("name", order.getName());
            namedParameters.addValue("address", order.getAddress());
            namedParameters.addValue("latitude", order.getLat());
            namedParameters.addValue("longitude", order.getLon());
            namedParameters.addValue("course_number", order.getCourseNumber());
            namedParameters.addValue("created",new Date());
            namedParameters.addValue("modified",new Date());
            int countSameCustomer = jangbogoRepository.getCustomer(order.getGuestId());
            if (countSameCustomer > 1) {
                namedParameters.addValue("customer_id", order.getGuestId() + "_" + Integer.toString(countSameCustomer));
            }
            customer = jangbogoRepository.createCustomer(namedParameters);
        }
        return customer;
    }

    @Override
    public void createOrder(Map<String, Object> params) throws DataAccessException, SQLException {

    }

}

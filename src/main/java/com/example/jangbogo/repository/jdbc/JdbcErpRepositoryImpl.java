package com.example.jangbogo.repository.jdbc;

import com.example.jangbogo.DTO.Order;
import com.example.jangbogo.repository.ErpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
@Profile("jdbc")
public class JdbcErpRepositoryImpl implements ErpRepository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    DataSource dataSource;

    @Autowired
    public JdbcErpRepositoryImpl(@Qualifier("erp") DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }

    @Override
    public Collection<Order> getOrders(Boolean isAm, String companyCode) throws DataAccessException, SQLException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());

        String sql = "EXEC Iregen_OrderSheet_Ready_List_Vehicle ?, ?, ?, ?, ?, ?, ?";   // for stored proc taking 2 parameters
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setEscapeProcessing(true);
        ps.setQueryTimeout(10);
        ps.setString(1, " ");
        ps.setString(2, "10001");
        ps.setString(3, date);
        ps.setString(4, date);
        ps.setString(5, companyCode);
        ps.setString(6, companyCode);
        if(isAm){
            ps.setString(7, "1");
        } else {
            ps.setString(7, "2");
        }

        ResultSet rs = ps.executeQuery();
        int index = 0;
        ArrayList<Order> orders = new ArrayList<>();
        while (rs.next()) {
            Double lat = null;
            Double lon = null;
            Order order = new Order();
            order.setId(++index);
            order.setOrderNumber(rs.getString("SaCode"));
            order.setGuestId(rs.getString("CtCode"));
            order.setDeliveryDate(rs.getDate("DevDDay"));
            order.setName(rs.getString("JoinUserName"));
            order.setCourseNumber(rs.getInt("CourseNum"));
            if (rs.getString("DevY") != null && rs.getString("DevY").length() > 0) {
                lat = Double.parseDouble(rs.getString("DevY"));
            }
            if (rs.getString("DevX") != null && rs.getString("DevX").length() > 0) {
                lon = Double.parseDouble(rs.getString("DevX"));
            }
            order.setLat(lat);
            order.setLon(lon);
            order.setAddress(rs.getString("Address1").strip());
            order.setPay(rs.getInt("TotalSalePrice"));
//            order.setIsAm(rs.getInt(""));
            orders.add(order);
        }

        return orders;
    }

    @Override
    public void updateCourseNumber(String guestId, int toCourseNumber) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("guestId", guestId);
        params.put("toCourseNumber", toCourseNumber);
        this.namedParameterJdbcTemplate.update("UPDATE table_Customer SET CourseNum=:toCourseNumber WHERE CtCode=:guestId", params);
    }

    @Override
    public void saveGeolocation(Map<String, Object> params) throws DataAccessException {
        this.namedParameterJdbcTemplate.update("UPDATE table_OrderSheet SET DevX=:lon, DevY=:lat WHERE SaCode=:orderNumber", params);
    }

    @Override
    public void removePrevRouteM(Map<String, Object> params) throws DataAccessException {
        String query = new StringBuilder()
                .append("DELETE FROM RouteM ")
                .append("WHERE company_id=:companyId ")
                .append("and is_am=:isAm ")
                .append("and date=:date ").toString();
        this.namedParameterJdbcTemplate.update(query, params);
    }
}

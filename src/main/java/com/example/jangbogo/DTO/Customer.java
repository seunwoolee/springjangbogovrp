package com.example.jangbogo.DTO;

import com.example.jangbogo.model.BaseEntity;

public class Customer extends BaseEntity {
    private String customer_id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private Integer course_number;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getCourse_number() {
        return course_number;
    }

    public void setCourse_number(Integer course_number) {
        this.course_number = course_number;
    }
}

/*
 * Copyright 2002-2018 the original author or authors.
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
package com.example.jangbogo.DTO;

import com.example.jangbogo.model.BaseEntity;

import java.util.Date;

public class Delivery extends BaseEntity {
    private Integer companyId;
    private String date;
    private int is_am;
    private int count_car;
    private int price;
    private int count_location;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIs_am() {
        return is_am;
    }

    public void setIs_am(int is_am) {
        this.is_am = is_am;
    }

    public int getCount_car() {
        return count_car;
    }

    public void setCount_car(int count_car) {
        this.count_car = count_car;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount_location() {
        return count_location;
    }

    public void setCount_location(int count_location) {
        this.count_location = count_location;
    }
}

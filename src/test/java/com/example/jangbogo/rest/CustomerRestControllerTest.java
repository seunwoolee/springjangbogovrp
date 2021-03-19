package com.example.jangbogo.rest;

import com.example.jangbogo.DTO.*;
import com.example.jangbogo.service.JangbogoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static com.example.jangbogo.rest.AuthUserRestControllerTest.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CustomerRestController.class)
class CustomerRestControllerTest extends TestRestController {
    @Autowired
    CustomerRestController customerRestController;

    @MockBean
    private JangbogoService jangbogoService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void initData() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerRestController).build();
    }

    @Test
    void previewOrder() throws Exception {
        given(this.jangbogoService.getCompany(KEY)).willReturn(company);
        given(this.jangbogoService.getOrders(true, company)).willReturn(orders);
        MvcResult result = this.mockMvc.perform(get("/customer/preview_order/")
                .header("authorization", "Token " + KEY)
                .param("isAm", String.valueOf(true))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    void updateCourseNumber() throws Exception {
        UpdateCourseVo updateCourseVo = new UpdateCourseVo();
        updateCourseVo.setTo_course_number("1");
        updateCourseVo.setGuest_id("121");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(updateCourseVo);
        System.out.println("newAuthUserAsJSON " + json);

        this.mockMvc.perform(patch("/customer/update_course_number/")
                .header("authorization", "Token " + KEY)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void saveGeolocation() throws Exception {
        given(this.jangbogoService.getCompany(KEY)).willReturn(company);
        SaveGeoVo saveGeoVo = new SaveGeoVo();
        saveGeoVo.setLat("11.0");
        saveGeoVo.setLon("12.3");
        saveGeoVo.setOrderNumber("12123123");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(saveGeoVo);

        this.mockMvc.perform(post("/customer/save_geolocation/")
                .header("authorization", "Token " + KEY)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void createCustomers() throws Exception {
        HashMap<String, Object> failResult = new HashMap<>();
        failResult.put("result", false);
        failResult.put("message", "수집되지 않은 좌표가 있습니다.");

        HashMap<String, Object> successResult = new HashMap<>();
        successResult.put("result", true);
        successResult.put("message", "완료");

        CreateCustomersVo vo = new CreateCustomersVo();
        vo.setIsAm(true);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(vo);

        given(this.jangbogoService.getCompany(KEY)).willReturn(company);
        given(this.jangbogoService.getOrders(true, company)).willReturn(orders);
        given(this.jangbogoService.validateCustomers(orders)).willReturn(failResult);

        this.mockMvc.perform(post("/customer/create_customers/")
                .header("authorization", "Token " + KEY)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());

        given(this.jangbogoService.validateCustomers(orders)).willReturn(successResult);

        for (Order order : orders) {
            given(this.jangbogoService.createCustomers(order)).willReturn(customer);
        }

//        given(this.jangbogoService.createCustomers(orders)).willReturn(successResult);
//        Customer customer = this.jangbogoService.createCustomers(order);

        this.mockMvc.perform(post("/customer/create_customers/")
                .header("authorization", "Token " + KEY)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}

package com.example.jangbogo.rest;

import com.example.jangbogo.DTO.*;
import com.example.jangbogo.service.JangbogoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import static com.example.jangbogo.rest.AuthUserRestControllerTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(DeliveryRestController.class)
class DeliveryRestControllerTest extends TestRestController {
    @Autowired
    DeliveryRestController restController;

    @MockBean
    private JangbogoService jangbogoService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void initData() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(restController).build();
    }

    @Test
    void getDeliveries() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = "2021-03-19" ;
        String endDate = "2021-03-19" ;
        given(this.jangbogoService.getCompany(KEY)).willReturn(company);
        given(this.jangbogoService.getDeliveries(company, simpleDateFormat.parse(startDate), simpleDateFormat.parse(endDate))).willReturn(deliveries);
        this.mockMvc.perform(get("/delivery/deliveries/")
                .header("authorization", "Token " + KEY)
                .param("startDate", startDate)
                .param("endDate", endDate)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andReturn();
    }
}

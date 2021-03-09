package com.example.jangbogo.rest;

import com.example.jangbogo.DTO.Company;
import com.example.jangbogo.exceptions.DuplicateUserIdException;
import com.example.jangbogo.service.JangbogoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CompanyRestController.class)
class CompanyRestControllerTest {
    private final String KEY = "asdfasdfasdf";

    @Autowired
    private CompanyRestController companyRestController;

    @MockBean
    private JangbogoService jangbogoService;

    @Autowired
    private MockMvc mockMvc;

    private List<Company> companies;

    @BeforeEach
    public void initCompanies() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(companyRestController)
                .build();
        companies = new ArrayList<Company>();
        Company company1 = new Company();
        company1.setId(1);
        Company company2 = new Company();
        company2.setId(2);

        companies.add(company1);
        companies.add(company2);
    }


    @Test
    void getAllCompanies() throws Exception {
        given(this.jangbogoService.findCompanies()).willReturn(companies);
        MvcResult result = this.mockMvc.perform(get("/api/companies/")
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
    void getCompany() throws Exception {
        given(this.jangbogoService.getCompany(KEY)).willReturn(companies.get(0));
        this.mockMvc.perform(get("/company/get_company/")
//                .header(HttpHeaders.AUTHORIZATION, "Token " + KEY)
                .header("authorization", "Token " + KEY)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1));
    }

}
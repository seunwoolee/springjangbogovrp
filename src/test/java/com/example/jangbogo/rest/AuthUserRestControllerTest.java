package com.example.jangbogo.rest;

import com.example.jangbogo.DTO.AuthUser;
import com.example.jangbogo.DTO.Company;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AuthUserRestController.class)
class AuthUserRestControllerTest extends TestRestController{
    @Autowired
    private AuthUserRestController authUserRestController;

    @MockBean
    private JangbogoService jangbogoService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void initAuthUser() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(authUserRestController).build();
    }

    @Test
    void login() throws Exception {
        given(this.jangbogoService.login(authUser.getUsername(), authUser.getPassword())).willReturn(authUser);
        ObjectMapper mapper = new ObjectMapper();
        String newAuthUserAsJSON = mapper.writeValueAsString(authUser);
        System.out.println("newAuthUserAsJSON " + newAuthUserAsJSON);

        MvcResult result = this.mockMvc.perform(post("/rest-auth/login/")
                .content(newAuthUserAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(USERNAME))
                .andExpect(jsonPath("$.password").value(PASSWORD))
                .andExpect(jsonPath("$.key").value(KEY))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        System.out.println(content);



    }
}

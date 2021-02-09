package com.example.jangbogo.rest;

import com.example.jangbogo.model.Vet;
import com.example.jangbogo.service.ClinicService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(VetRestController.class)
class VetRestControllerTest {
    @Autowired
    private VetRestController vetRestController;

    @MockBean
    private ClinicService clinicService;

    @Autowired
    private MockMvc mockMvc;

    private List<Vet> vets;

    @BeforeEach
    public void initVets(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(vetRestController)
                .build();
        vets = new ArrayList<Vet>();


        Vet vet = new Vet();
        vet.setId(1);
        vet.setFirstName("James");
        vet.setLastName("Carter");
        vets.add(vet);

        vet = new Vet();
        vet.setId(2);
        vet.setFirstName("Helen");
        vet.setLastName("Leary");
        vets.add(vet);

        vet = new Vet();
        vet.setId(3);
        vet.setFirstName("Linda");
        vet.setLastName("Douglas");
        vets.add(vet);
    }

    @Test
    @WithMockUser(roles="VET_ADMIN")
    public void testGetAllVetsSuccess() throws Exception {
        given(this.clinicService.findAllVets()).willReturn(vets);
        this.mockMvc.perform(get("/api/vets/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].firstName").value("James"))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[1].firstName").value("Helen"));
    }

//    @Test
//    void getAllVets() {
//    }
//
//    @Test
//    void getVet() {
//    }
//
//    @Test
//    void addVet() {
//    }
}
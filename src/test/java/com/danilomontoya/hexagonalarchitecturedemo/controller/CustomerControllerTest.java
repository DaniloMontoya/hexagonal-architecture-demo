package com.danilomontoya.hexagonalarchitecturedemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 4:22 PM
 **/

@AutoConfigureMockMvc
@SpringBootTest
class CustomerControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create() throws Exception {
        String jsonCreate = "{\n" +
                "    \"identificationType\": \"CC\",\n" +
                "    \"identificationNumber\": \"12345678\",\n" +
                "    \"name\": \"Sebastian\",\n" +
                "    \"lastname\": \"Montoya\",\n" +
                "    \"email\": \"ing.danilo@gmail.com\",\n" +
                "    \"birthday\": \"2000-05-28\"\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders
                .post("/client/create")
                .content(jsonCreate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        String jsonUpdate = "{\n" +
                "    \"id\": \"930b5282-7eaa-4015-a5ea-38c5750dc777\",\n" +
                "    \"identificationType\": \"CC\",\n" +
                "    \"identificationNumber\": \"1234567\",\n" +
                "    \"name\": \"Sebastian\",\n" +
                "    \"lastname\": \"Montoya\",\n" +
                "    \"email\": \"ing.danilomontoya@gmail.com\",\n" +
                "    \"birthday\": \"2000-05-28\"\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders
                        .post("/client/update")
                        .content(jsonUpdate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}

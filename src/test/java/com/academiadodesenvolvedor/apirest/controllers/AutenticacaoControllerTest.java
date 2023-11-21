package com.academiadodesenvolvedor.apirest.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
public class AutenticacaoControllerTest {

    private final String REQUEST_URL = "/login";
    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldCreateUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(REQUEST_URL.concat("/create"))
                        .contentType("application/json")
                        .content("{\"nome\": \"teste testudo\",\"email\": \"teste@testudo.com\",\"password\":\"123456789\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}

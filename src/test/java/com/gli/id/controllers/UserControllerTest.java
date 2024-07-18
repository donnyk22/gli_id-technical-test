package com.gli.id.controllers;

import com.gli.id.BaseTest;
import org.apache.hc.core5.net.URIBuilder;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest extends BaseTest {

    @Test
    void findAllUser() throws Exception {
        log("Start findAllUser");
        String url = new URIBuilder()
                .setPath("/user-api")
                .toString();
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id", Matchers.notNullValue()));
        log("Finish findAllUser");
    }
}
package com.gli.id;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class BaseTest {

    @Autowired
    protected MockMvc mockMvc;

    protected void log(String msg){
        System.out.println(msg);
    }

    protected Gson gson = new GsonBuilder().setPrettyPrinting().create();
}

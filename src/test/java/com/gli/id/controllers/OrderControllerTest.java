package com.gli.id.controllers;

import com.gli.id.BaseTest;
import com.gli.id.dtos.forms.OrderAddForm;
import com.gli.id.dtos.forms.OrderDetailAddForm;
import com.gli.id.dtos.forms.OrderEditForm;
import com.jayway.jsonpath.JsonPath;
import org.apache.hc.core5.net.URIBuilder;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class OrderControllerTest extends BaseTest {

    private Integer orderId;

    @Test
    @Order(1)
    void addOrder() throws Exception {
        log("Start addOrder");
        List<OrderDetailAddForm> detailAddFormList = new ArrayList<>();
        OrderDetailAddForm detailAddForm = new OrderDetailAddForm()
                .setProduct_id(1)
                .setQty(1);
        detailAddFormList.add(detailAddForm);
        OrderAddForm addForm = new OrderAddForm()
                .setUser_id(1)
                .setOrder_list(detailAddFormList);
        String json = gson.toJson(addForm);
        String url = new URIBuilder()
                .setPath("/order-api")
                .toString();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.order_list[0].product_detail.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.user_data.id", Matchers.is(1)))
                .andReturn();
        orderId = JsonPath.read(result.getResponse().getContentAsString(), "$.data.id");
        log("Finish addOrder");
    }

    @Test
    @Order(2)
    void findOrder() throws Exception {
        log("Start findOrder");
        String url = new URIBuilder()
                .setPath("/product-api")
                .addParameter("id", orderId.toString())
                .toString();
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id", Matchers.notNullValue()));
        log("Finish findOrder");
    }

    @Test
    @Order(3)
    void editOrder() throws Exception {
        log("Start editOrder");
        OrderEditForm editForm = new OrderEditForm()
                .setUser_id(2)
                .setId(orderId);
        String json = gson.toJson(editForm);
        String url = new URIBuilder()
                .setPath("/order-api")
                .toString();
        mockMvc.perform(MockMvcRequestBuilders.put(url)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.order_list", Matchers.nullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.user_data.id", Matchers.is(2)))
                .andReturn();
        log("Finish editOrder");
    }

    @Test
    @Order(4)
    void deleteOrder() throws Exception {
        log("Start deleteOrder");
        String url = new URIBuilder()
                .setPath("/product-api")
                .addParameter("id", orderId.toString())
                .toString();
        mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id", Matchers.notNullValue()));
        log("Finish deleteOrder");
    }

    @Test
    @Order(5)
    void checkIfOrderIsDeleted() throws Exception {
        log("Start checkIfOrderIsDeleted");
        String url = new URIBuilder()
                .setPath("/product-api")
                .addParameter("id", "1102")
                .toString();
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(500)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.nullValue()));
        log("Finish checkIfOrderIsDeleted");
    }
}
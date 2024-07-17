package com.gli.id.controllers;

import com.gli.id.dtos.OrderDto;
import com.gli.id.dtos.forms.OrderAddForm;
import com.gli.id.dtos.forms.OrderEditForm;
import com.gli.id.dtos.responses.Response;
import com.gli.id.services.order.OrderService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-api")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity find(@RequestParam(required = false) Integer id){
        try {
            List<OrderDto> result = orderService.find(id);
            return Response.ok(result).build();
        }catch (Exception e){
            return Response.error(e.getMessage()).build();
        }
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity add(@RequestBody OrderAddForm orderAddForm){
        try {
            OrderDto result = orderService.add(orderAddForm);
            return Response.ok(result).build();
        }catch (Exception e){
            return Response.error(e.getMessage()).build();
        }
    }

    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity edit(@RequestBody OrderEditForm orderEditForm){
        try {
            OrderDto result = orderService.edit(orderEditForm);
            return Response.ok(result).build();
        }catch (Exception e){
            return Response.error(e.getMessage()).build();
        }
    }

    @DeleteMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity delete(@RequestParam Integer id){
        try {
            OrderDto result = orderService.delete(id);
            return Response.ok(result).build();
        }catch (Exception e){
            return Response.error(e.getMessage()).build();
        }
    }
}

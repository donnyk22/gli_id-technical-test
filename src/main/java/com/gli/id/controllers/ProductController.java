package com.gli.id.controllers;

import com.gli.id.dtos.ProductDto;
import com.gli.id.dtos.responses.Response;
import com.gli.id.services.product.ProductService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product-api")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity findAll() {
        try {
            List<ProductDto> result = productService.findAll();
            return Response.ok(result).build();
        }catch (Exception e){
            return Response.error(e.getMessage()).build();
        }
    }
}

package com.gli.id.controllers;

import com.gli.id.dtos.UserDto;
import com.gli.id.dtos.responses.Response;
import com.gli.id.services.user.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response findAll() {
        try {
            List<UserDto> result = userService.findAll();
            return new Response().setMessage("User data found").setData(result);
        }catch (Exception e){
            return new Response().setMessage(e.getMessage());
        }
    }
}

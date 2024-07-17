package com.gli.id.controllers;

import com.gli.id.services.dog_public_api.DogPublicApiService;
import com.gli.id.tools.JsonFormatter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/dog-public-api")
public class DogPublicApiController {

    @Autowired
    DogPublicApiService dogPublicApiService;

    @GetMapping
    public String find(@RequestParam(value = "breed", required = false) String breed) throws IOException {
        return dogPublicApiService.getData(breed);
    }

    @GetMapping("/lambda")
    public String findLambda() throws IOException {
        return getData(s ->
            {
                Connection.Response response = Jsoup.connect("https://dog.ceo/api/breeds/list/all")
                        .method(org.jsoup.Connection.Method.GET)
                        .ignoreContentType(true)
                        .timeout(5000)
                        .execute();
                return JsonFormatter.format(response.body());
            }
        );
    }

    private String getData(DogPublicApiService dogPublicApi) throws IOException {
        return dogPublicApi.getData(null);
    }
}

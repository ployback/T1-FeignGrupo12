package com.grupo12.t1feign.client;

import com.grupo12.t1feign.model.SwapiPeopleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "swapiClient", url = "${swapi.api.url:https://swapi.dev}")
public interface SwapiClient {

    @GetMapping("/api/people/")
    SwapiPeopleResponse getFirstPage();
}
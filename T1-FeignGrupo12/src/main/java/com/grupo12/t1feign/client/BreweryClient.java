package com.grupo12.t1feign.client;

import com.grupo12.t1feign.model.BreweryData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "openBreweryClient", url = "${brewery.api.url:https://api.openbrewerydb.org}")
public interface BreweryClient {

    @GetMapping("/v1/breweries")
    List<BreweryData> findBreweries(
            @RequestParam("by_type") String type,
            @RequestParam("by_state") String state
    );
}
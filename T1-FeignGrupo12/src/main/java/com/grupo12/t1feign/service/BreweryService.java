package com.grupo12.t1feign.service;

import com.grupo12.t1feign.client.BreweryClient;
import com.grupo12.t1feign.model.BreweryData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class BreweryService {

    private static final String BREWERY_TYPE = "micro";
    private static final String STATE = "California";

    private final BreweryClient breweryClient;

    public BreweryService(BreweryClient breweryClient) {
        this.breweryClient = breweryClient;
    }

    public List<BreweryData> getCaliforniaMicroBreweries() {
        return breweryClient.findBreweries(BREWERY_TYPE, STATE.toLowerCase(Locale.ROOT)).stream()
                .filter(brewery -> brewery.breweryType() != null
                        && BREWERY_TYPE.equalsIgnoreCase(brewery.breweryType()))
                .filter(brewery -> brewery.state() != null
                        && STATE.equalsIgnoreCase(brewery.state()))
                .toList();
    }
}
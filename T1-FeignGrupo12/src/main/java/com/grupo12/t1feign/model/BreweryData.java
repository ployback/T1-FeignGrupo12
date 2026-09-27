package com.grupo12.t1feign.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BreweryData(
        String id,
        String name,
        @JsonProperty("brewery_type") String breweryType,
        String state
) {
}
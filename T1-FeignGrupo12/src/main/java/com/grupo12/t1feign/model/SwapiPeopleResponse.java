package com.grupo12.t1feign.model;

import java.util.List;

public record SwapiPeopleResponse(
        int count,
        String next,
        String previous,
        List<StarWarsCharacter> results
) {
}
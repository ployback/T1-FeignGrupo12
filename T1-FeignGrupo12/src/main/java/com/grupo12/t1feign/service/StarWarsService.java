package com.grupo12.t1feign.service;

import com.grupo12.t1feign.client.SwapiClient;
import com.grupo12.t1feign.model.StarWarsCharacter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StarWarsService {

    private static final String FEMALE_GENDER = "female";
    private static final int MINIMUM_HEIGHT_CM = 160;

    private final SwapiClient swapiClient;

    public StarWarsService(SwapiClient swapiClient) {
        this.swapiClient = swapiClient;
    }

    public List<StarWarsCharacter> getFemaleCharactersTallerThan160() {
        return swapiClient.getFirstPage().results().stream()
                .filter(this::isFemaleAndTallerThanMinimum)
                .toList();
    }

    private boolean isFemaleAndTallerThanMinimum(StarWarsCharacter character) {
        if (!FEMALE_GENDER.equalsIgnoreCase(character.gender()) || character.height() == null) {
            return false;
        }

        try {
            return Integer.parseInt(character.height()) > MINIMUM_HEIGHT_CM;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
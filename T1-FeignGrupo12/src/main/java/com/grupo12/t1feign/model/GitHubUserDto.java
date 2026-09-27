package com.grupo12.t1feign.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GitHubUserDto(
        long id,
        String login,
        @JsonProperty("site_admin") Boolean siteAdmin,
        @JsonProperty("avatar_url") String avatarUrl
) {
}
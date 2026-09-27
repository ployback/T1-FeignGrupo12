package com.grupo12.t1feign.client;

import com.grupo12.t1feign.model.GitHubUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "gitHubClient", url = "${github.api.url:https://api.github.com}")
public interface GitHubClient {

    @GetMapping("/users")
    List<GitHubUserDto> getUsers();
}
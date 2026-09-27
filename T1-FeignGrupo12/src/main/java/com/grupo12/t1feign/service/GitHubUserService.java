package com.grupo12.t1feign.service;

import com.grupo12.t1feign.client.GitHubClient;
import com.grupo12.t1feign.model.GitHubUserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GitHubUserService {

    private static final int MAX_LOGIN_LENGTH = 5;

    private final GitHubClient gitHubClient;

    public GitHubUserService(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    public List<GitHubUserDto> getUsersWithShortLoginWhoAreNotSiteAdmins() {
        return gitHubClient.getUsers().stream()
                .filter(user -> user.login() != null && user.login().length() <= MAX_LOGIN_LENGTH)
                .filter(user -> Boolean.FALSE.equals(user.siteAdmin()))
                .toList();
    }
}
package com.nilsw13.spring_replicate.ResponseType.Account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountResponse {

    private String type;
    private String username;
    private String name;

    @JsonProperty("github_url")
    private String githubUrl;

    public AccountResponse(String githubUrl, String name, String username, String type) {
        this.githubUrl = githubUrl;
        this.name = name;
        this.username = username;
        this.type = type;
    }

    public AccountResponse() {

    }

    public String getType() {
        return type;
    }


    public String getUsername() {
        return username;
    }


    public String getName() {
        return name;
    }


    public String getGithubUrl() {
        return githubUrl;
    }

}

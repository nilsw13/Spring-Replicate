package com.nilsw13.spring_replicate.model;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }
}

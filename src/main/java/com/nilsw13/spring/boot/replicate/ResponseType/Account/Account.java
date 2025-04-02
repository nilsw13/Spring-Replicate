package com.nilsw13.spring.boot.replicate.ResponseType.Account;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Represents user account information from the Replicate API.
 *
 * This class contains basic profile information about a Replicate user account,
 * including identification details and profile links. The information is retrieved
 * from the authenticated API token's associated account.
 *
 * @author Nilsw13
 * @since 1.0.0
 */
public class Account {

    private String type;
    private String username;
    private String name;

    @JsonProperty("github_url")
    private String githubUrl;



    public Account() {

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

    public void setType(String type) {
        this.type = type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }
}

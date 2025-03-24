package com.nilsw13.spring_replicate.ResponseType.Deployment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class DeploymentRelease {

    private String number;
    private String model;
    private String version;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("created_by")
    private Map<String, String> createdBy;

    private DeploymentConfiguration configuration;

    public String getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public String getVersion() {
        return version;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Map<String, String> getCreatedBy() {
        return createdBy;
    }

    public DeploymentConfiguration getConfiguration() {
        return configuration;
    }
}

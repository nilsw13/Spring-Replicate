package com.nilsw13.spring_replicate.ResponseType.Deployment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Deployment {

    private String owner;
    private String name;

    @JsonProperty("current_release")
    private DeploymentRelease deploymentRelease;

    public String getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public DeploymentRelease getCurrentRelease() {
        return deploymentRelease;
    }

    @Override
    public String toString() {
        return "Deployment{" +
                "owner='" + owner + '\'' +
                ", name='" + name + '\'' +
                ", deploymentRelease=" + deploymentRelease +
                '}';
    }
}

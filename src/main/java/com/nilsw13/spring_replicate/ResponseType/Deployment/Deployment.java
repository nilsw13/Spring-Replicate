package com.nilsw13.spring_replicate.ResponseType.Deployment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Deployment {

    private String owner;
    private String name;

    @JsonProperty("current_release")
    private DeploymentRelease deploymentRelease;

    public Deployment() {
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeploymentRelease(DeploymentRelease deploymentRelease) {
        this.deploymentRelease = deploymentRelease;
    }

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

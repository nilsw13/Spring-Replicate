package com.nilsw13.spring_boot.replicate.ResponseType.Deployment;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Represents a model deployment in the Replicate API.
 *
 * A deployment is a production-ready instance of a model that provides a stable
 * endpoint for predictions with defined scaling and hardware configurations.
 * This class encapsulates the core properties of a deployment including its
 * ownership information and current configuration.
 *
 * Deployments provide consistent endpoints with predictable performance
 * characteristics, making them suitable for production applications that
 * require reliability and scaling capabilities.
 *
 * @author Nilsw13
 * @since 1.0.0
 */
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

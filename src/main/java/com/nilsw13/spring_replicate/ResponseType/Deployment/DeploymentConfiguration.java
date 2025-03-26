package com.nilsw13.spring_replicate.ResponseType.Deployment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Configuration settings for creating or updating a deployment in the Replicate API.
 *
 * This class encapsulates the configurable properties of a deployment including
 * the hardware type, scaling parameters, and the specific model version to deploy.
 * It is used both when creating new deployments and when updating existing ones.
 *
 * The class is annotated with @JsonInclude(JsonInclude.Include.NON_NULL) to ensure
 * that null properties are omitted from the JSON request body. This allows for
 * partial updates where only the specified properties are changed.
 *
 * @author Nilsw13
 * @since 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeploymentConfiguration {
    private String hardware;
    private String name;
    private String model;
    @JsonProperty("min_instances")
    private Integer minInstances;
    @JsonProperty("max_instances")
    private Integer maxInstances;
    @JsonProperty("version")
    private String version;


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }



    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeploymentConfiguration() {
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public Integer getMinInstances() {
        return minInstances;
    }

    public void setMinInstances(Integer minInstances) {
        this.minInstances = minInstances;
    }

    public Integer getMaxInstances() {
        return maxInstances;
    }

    public void setMaxInstances(Integer maxInstances) {
        this.maxInstances = maxInstances;
    }
}

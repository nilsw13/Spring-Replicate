package com.nilsw13.spring_boot.replicate.ResponseType.Deployment;

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

    /**
     * Default constructor for DeploymentConfiguration class.
     *
     * This empty constructor exists for the following reasons:
     * 1. Required by JSON/Jackson deserialization process when mapping API responses
     * 2. Enables library users to instantiate response objects when needed
     * 3. Supports serialization/deserialization in various client implementations
     *
     * Although empty, this constructor is essential for the proper functioning
     * of the API client library and should not be removed.
     */
    public DeploymentConfiguration() {
    }

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

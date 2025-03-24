package com.nilsw13.spring_replicate.ResponseType.Deployment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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

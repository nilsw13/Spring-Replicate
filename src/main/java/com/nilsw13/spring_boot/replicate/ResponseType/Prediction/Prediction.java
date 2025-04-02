package com.nilsw13.spring_boot.replicate.ResponseType.Prediction;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;



/**
 * Represents a prediction in the Replicate platform.
 *
 * A prediction is a specific execution of a model with inputs and outputs.
 * When a model is run on Replicate, it creates a prediction object that tracks
 * the execution status, inputs provided, outputs generated, and associated metadata
 * such as timing information and metrics.
 *
 * This class corresponds to the JSON structure returned by the Replicate API
 * when creating, retrieving, or listing predictions. It includes fields for
 * tracking the lifecycle of a prediction from creation through completion
 * or error states.
 *
 * @author Nilsw13
 * @since 1.0.0
 */
public class Prediction {


    @JsonProperty("data_removed")
    private Boolean dataRemoved;

    private String error;
    private String id;
    private Map<String, Object> input;
    private Map<String, Double> metrics;
    private Object output;

    @JsonProperty("completed_at")
    private String completedAt;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("started_at")
    private String startedAt;

    private String source;
    private String status;
    private Map<String, String> urls;
    private String model;
    private String version;



    public Prediction() {
        /**
         * Default constructor for Prediction class.
         *
         * This empty constructor exists for the following reasons:
         * 1. Required by JSON/Jackson deserialization process when mapping API responses
         * 2. Enables library users to instantiate response objects when needed
         * 3. Supports serialization/deserialization in various client implementations
         *
         * Although empty, this constructor is essential for the proper functioning
         * of the API client library and should not be removed.
         */

    }

    public String getCompletedAt() {
        return completedAt;
    }



    public String getCreatedAt() {
        return createdAt;
    }



    public Boolean getDataRemoved() {
        return dataRemoved;
    }


    public String getError() {
        return error;
    }



    public String getId() {
        return id;
    }



    public Map<String, Object> getInput() {
        return input;
    }



    public Map<String, Double> getMetrics() {
        return metrics;
    }



    public Object getOutput() {
        return output;
    }



    public String getStartedAt() {
        return startedAt;
    }



    public String getSource() {
        return source;
    }



    public String getStatus() {
        return status;
    }



    public Map<String, String> getUrls() {
        return urls;
    }


    public String getModel() {
        return model;
    }


    public String getVersion() {
        return version;
    }

    public void setDataRemoved(Boolean dataRemoved) {
        this.dataRemoved = dataRemoved;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInput(Map<String, Object> input) {
        this.input = input;
    }

    public void setMetrics(Map<String, Double> metrics) {
        this.metrics = metrics;
    }

    public void setOutput(Object output) {
        this.output = output;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUrls(Map<String, String> urls) {
        this.urls = urls;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVersion(String version) {
        this.version = version;
    }


}

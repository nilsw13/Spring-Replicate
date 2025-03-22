package com.nilsw13.spring_replicate.ResponseType.Prediction;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class PredictionResponse {


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

    public PredictionResponse() {


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


}

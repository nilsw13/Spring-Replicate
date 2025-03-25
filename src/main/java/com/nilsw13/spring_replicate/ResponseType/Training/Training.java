package com.nilsw13.spring_replicate.ResponseType.Training;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Training {

    @JsonProperty("completed_at")
    private String completedAt;
    @JsonProperty("created_at")
    private String createdAt;
    private String error;
    private String id;
    private Map<String, String> input;
    private String logs;
    private Map<String , Double> metrics;
    private Map<String, String> output;
    @JsonProperty("started_at")
    private String startedAt;
    private String status;
    private Map<String, String> urls;
    private String model;
    private String version;

    public String getCompletedAt() {
        return completedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getError() {
        return error;
    }

    public String getId() {
        return id;
    }

    public Map<String, String> getInput() {
        return input;
    }

    public String getLogs() {
        return logs;
    }

    public Map<String, Double> getMetrics() {
        return metrics;
    }

    public Map<String, String> getOutput() {
        return output;
    }

    public String getStartedAt() {
        return startedAt;
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
    @Override
    public String toString() {
        return "Training{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", version='" + version + '\'' +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", startedAt='" + startedAt + '\'' +
                ", completedAt='" + completedAt + '\'' +
                ", input=" + input +
                ", output=" + output +
                ", metrics=" + metrics +
                ", error='" + error + '\'' +
                ", logs='" + (logs != null ? logs.substring(0, Math.min(logs.length(), 50)) + "..." : "null") + '\'' +
                ", urls=" + urls +
                '}';
    }
}

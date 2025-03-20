package com.nilsw13.spring_replicate.model.Prediction;

import java.time.OffsetDateTime;
import java.util.Map;

public class Prediction {
    private final String id;
    private final String status;
    private final String model;
    private final String version;
    private final Object output;
    private final Map<String, Object> input;
    private final String createdAt;
    private final String completedAt;


    public Prediction(String id, String status, String model, String version, Object output, Map<String, Object> input, String createdAt, String completedAt) {
        this.id = id;
        this.status = status;
        this.model = model;
        this.version = version;
        this.output = output;
        this.input = input;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getModel() {
        return model;
    }

    public String getVersion() {
        return version;
    }

    public Object getOutput() {
        return output;
    }

    public Map<String, Object> getInput() {
        return input;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public boolean isCompleted() {
        return "succeeded".equals(status) || "failed".equals(status) || "canceled".equals(status);
    }

    public boolean isSuccessful() {
        return "succeeded".equals(status);
    }
}

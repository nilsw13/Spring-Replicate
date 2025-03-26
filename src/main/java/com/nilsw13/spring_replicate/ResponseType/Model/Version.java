package com.nilsw13.spring_replicate.ResponseType.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;


/**
 * Represents a specific version of a model in the Replicate platform.
 *
 * A version is a specific trained instance of a model, with its own unique
 * identifier and configuration. Each version has an associated OpenAPI schema
 * that describes the inputs and outputs for running predictions.
 *
 * Different versions of the same model may have different capabilities,
 * performance characteristics, or training data.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see Model
 */
public class Version {
    private String id;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("cog_version")
    private String cogVersion;
    private Map<String, Object> openapiSchema;

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getCogVersion() {
        return cogVersion;
    }

    public Map<String, Object> getOpenapiSchema() {
        return openapiSchema;
    }

    @Override
    public String toString() {
        return "Version{" +
                "id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", cogVersion='" + cogVersion + '\'' +
                ", openapiSchema=" + openapiSchema +
                '}';
    }


}

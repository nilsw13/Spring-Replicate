package com.nilsw13.spring_replicate.ResponseType.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

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

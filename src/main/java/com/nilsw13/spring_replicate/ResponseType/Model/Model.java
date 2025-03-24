package com.nilsw13.spring_replicate.ResponseType.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.Map;

public class ModelResponse {
    private String id;

    public String getId() {
        return id;
    }

    private String url;
    private String owner;
    private String name;
    private String description;
    private String visibility;
    @JsonProperty("github_url")
    private String githubUrl;
    @JsonProperty("paper_url")
    private String paperUrl;
    @JsonProperty("license_url")
    private String licenseUrl;
    @JsonProperty("run_count")
    private BigInteger runCount;
    @JsonProperty("cover_image_url")
    private String coverImageUrl;
    @JsonProperty("default_example")
    private Map<String, Object> defaultExample;
    @JsonProperty("latest_version")
    private Map<String, Object> latestVersion;

    public String getUrl() {
        return url;
    }

    public String getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getPaperUrl() {
        return paperUrl;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public BigInteger getRunCount() {
        return runCount;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public Map<String, Object> getDefaultExample() {
        return defaultExample;
    }

    public Map<String, Object> getLatestVersion() {
        return latestVersion;
    }

    @Override
    public String toString() {
        return "ModelResponse{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}

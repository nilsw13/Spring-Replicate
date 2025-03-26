package com.nilsw13.spring_replicate.ResponseType.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.Map;

/**
 * Represents a model in the Replicate platform.
 *
 * A model is a machine learning model that can be run via the Replicate API.
 * This class holds the properties returned by the API when retrieving model information,
 * including metadata such as the model owner, description, visibility settings, and usage statistics.
 *
 * Models contain versions, which represent specific trained instances of the model.
 * The latest version is included in the model response for convenience.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see Version
 */
public class Model {
    private String id;

    public String getId() {
        return id;
    }

    private String url;
    private String owner;
    private String name;
    private String description;
    private String visibility;
    private String hardware;
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


    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
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
        return "Model{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}

package com.nilsw13.spring_replicate.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "replicate")
public class ReplicateProperties {

    private String apiKey;
    private String apiUrl = "https://api.replicate.com/v1";

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }


}
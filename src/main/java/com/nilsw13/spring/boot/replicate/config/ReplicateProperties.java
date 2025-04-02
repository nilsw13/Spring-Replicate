package com.nilsw13.spring.boot.replicate.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * Configuration properties for the Replicate API client.
 *
 * This class captures the configuration settings needed to communicate with
 * the Replicate API. It automatically binds properties from the application.properties or
 * any configuration file with the prefix "replicate".
 *
 * Required configuration:
 * - replicate.api-key: The API key used for authentication with Replicate
 *
 * Optional configuration:
 * - replicate.api-url: The base URL for the Replicate API (defaults to "https://api.replicate.com/v1")
 *
 * Example configuration in application.properties:
 *
 * ```
 * replicate.api-key=your_api_key_here
 * # Optional: replicate.api-url=https://api.replicate.com/v1
 * ```
 *
 * This class is automatically discovered by Spring Boot's configuration
 * properties scanning and is used by ReplicateAutoConfig to create and
 * configure the necessary beans.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see ReplicateAutoConfig
 */
@Component
@ConfigurationProperties(prefix = "replicate")
public class ReplicateProperties {


    private String apiKey;
    private String apiUrl = "https://api.replicate.com/v1";

    /**
     * Gets the API key used for authentication with the Replicate API.
     *
     * This key is required for all API operations and should be kept secure.
     * You can obtain an API key from your Replicate account dashboard.
     *
     * @return The configured API key
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Sets the API key used for authentication with the Replicate API.
     *
     * @param apiKey The API key to use for authentication
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Gets the base URL for the Replicate API.
     *
     * By default, this is set to "https://api.replicate.com/v1".
     * This method is primarily used for testing against different
     * environments or API versions.
     *
     * @return The configured API base URL
     */
    public String getApiUrl() {
        return apiUrl;
    }


}
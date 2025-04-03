package com.nilsw13.springboot.replicate.api;

import com.nilsw13.springboot.replicate.responsetype.deployment.DeploymentConfiguration;
import com.nilsw13.springboot.replicate.config.ReplicateProperties;
import com.nilsw13.springboot.replicate.exception.ReplicateApiException;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.Map;

/**
 * Core HTTP client for communicating with the Replicate API.
 *
 * This class encapsulates all direct HTTP interactions with the Replicate API,
 * providing methods for each supported HTTP verb (GET, POST, PATCH, DELETE).
 * It handles authentication, request formatting, error handling, and response
 * deserialization, offering a clean abstraction for service implementations.
 *
 * The client is configured with the API key and base URL from the application
 * properties and automatically applies the required authentication headers to
 * all requests. It also handles response error codes by translating them into
 * ReplicateApiException instances with descriptive error messages.
 *
 * This client is not typically used directly by application code, but rather
 * serves as the foundation for the service implementations that provide
 * domain-specific methods.
 *
 * @author Nilsw13
 * @since 1.0.0
 */
public class ReplicateRestClient {


    private final RestTemplate restTemplate;
    private final String baseUrl ;
    private final HttpHeaders defaultHeaders;


    /**
     * Constructs a new ReplicateRestClient with configuration from properties.
     *
     * Initializes the underlying RestTemplate and configures default headers
     * with the authentication token derived from the API key. All requests made
     * through this client will include these headers automatically.
     *
     * @param properties Configuration properties for the Replicate API connection
     * @see ReplicateProperties
     */
    public ReplicateRestClient(ReplicateProperties properties) {
        this.restTemplate = new RestTemplate();

        this.baseUrl = properties.getApiUrl();

        this.defaultHeaders = new HttpHeaders();
        this.defaultHeaders.set(HttpHeaders.AUTHORIZATION, "Token " + properties.getApiKey());
        this.defaultHeaders.setContentType(MediaType.APPLICATION_JSON);
        this.defaultHeaders.setAccept(java.util.Collections.singletonList(MediaType.APPLICATION_JSON));
    }


    /**
     * Performs a GET request to the specified API endpoint.
     *
     * Sends a GET request to the Replicate API at the specified endpoint path and
     * deserializes the JSON response into an object of the specified type.
     *
     * @param <T> The expected return type
     * @param endpoint The API endpoint path, relative to the base URL
     * @param responseType The class of the expected response object
     * @return The deserialized response object
     * @throws ReplicateApiException If the request fails or returns an error status
     */
    public <T> T get(String endpoint, Class <T> responseType) {
        String url = buildUrl(endpoint);

      try {
          HttpEntity <?> entity = new HttpEntity<>(defaultHeaders);
          ResponseEntity<T> response = restTemplate.exchange(
                  url,
                  HttpMethod.GET,
                  entity,
                  responseType
          );
          return  response.getBody();
      } catch (HttpStatusCodeException e) {
          throw new ReplicateApiException(
                  "Erreur while calling Replicate API: " + endpoint,
                  e,
                  e.getStatusCode().value(),
                  e.getResponseBodyAsString()
          );
      }



    }


    /**
     * Performs a POST request with a request body to the specified API endpoint.
     *
     * Sends a POST request with a JSON-serialized body to the Replicate API and
     * deserializes the response into an object of the specified type.
     *
     * @param <T> The expected return type
     * @param endpoint The API endpoint path, relative to the base URL
     * @param body The object to be serialized as the request body
     * @param responseType The class of the expected response object
     * @return The deserialized response object
     * @throws ReplicateApiException If the request fails or returns an error status
     */
    public <T> T post(String endpoint, Object body, Class<T> responseType){
        String url = buildUrl(endpoint);

        try {
            HttpEntity<?> entity = new HttpEntity<>(body != null ? body : new Object(), defaultHeaders);
            ResponseEntity<T> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    responseType
            );
            return response.getBody();
        } catch (HttpStatusCodeException e){
            throw  new ReplicateApiException(
                    "Error while post method on Replicate API :" + endpoint,
                    e,
                    e.getStatusCode().value(),
                    e.getResponseBodyAsString()
            );
        }
    }

    /**
     * Performs a POST request with custom headers and a request body.
     *
     * Similar to the standard post method, but allows for additional headers
     * to be specified. The default authentication headers are still included.
     *
     * @param <T> The expected return type
     * @param endpoint The API endpoint path, relative to the base URL
     * @param body The object to be serialized as the request body
     * @param headers Additional headers to include in the request
     * @param responseType The class of the expected response object
     * @return The deserialized response object
     * @throws ReplicateApiException If the request fails or returns an error status
     */
    public <T> T post(String endpoint, Object body,  Map<String, String> headers,  Class<T> responseType){
        String url = buildUrl(endpoint);

        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null) {
            headers.forEach(httpHeaders::add);
        }

        if (defaultHeaders != null) {
            httpHeaders.putAll(defaultHeaders);
        }


        try {
            HttpEntity<?> entity = new HttpEntity<>(body != null ? body : new Object(), httpHeaders);
            ResponseEntity<T> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    responseType
            );
            return response.getBody();
        } catch (HttpStatusCodeException e){
            throw  new ReplicateApiException(
                    "Error while post method on Replicate API :" + endpoint,
                    e,
                    e.getStatusCode().value(),
                    e.getResponseBodyAsString()
            );
        }
    }


    /**
     * Performs a PATCH request to update deployment configuration.
     *
     * This method temporarily modifies the RestTemplate to use HttpComponentsClientHttpRequestFactory
     * which relies on the Apache HttpComponents Client library. This substitution is necessary because
     * Java's default HTTP client does not natively support the PATCH method, while Apache HttpComponents
     * does. The original request factory is restored after the request completes.
     *
     * Note: This method requires the Apache HttpComponents Client library to be present in the classpath,
     * which is included as a dependency in this library:
     * org.apache.httpcomponents.client5:httpclient5
     *
     * @param <T> The expected return type
     * @param endpoint The API endpoint path, relative to the base URL
     * @param changes The deployment configuration changes to apply
     * @param responseType The class of the expected response object
     * @return The deserialized response object
     * @throws ReplicateApiException If the request fails or returns an error status
     */
    public <T> T patch(String endpoint, DeploymentConfiguration changes, Class<T> responseType) {
        String url = buildUrl(endpoint);
        ClientHttpRequestFactory originalFactory = restTemplate.getRequestFactory();

        try {
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

            HttpEntity<?> entity = new HttpEntity<>(changes != null ? changes : Collections.emptyMap(), defaultHeaders);
            ResponseEntity<T> response = restTemplate.exchange(
                    url,
                    HttpMethod.PATCH,
                    entity,
                    responseType
            );
            return response.getBody();

        } catch (HttpStatusCodeException e) {
            throw new ReplicateApiException(
                    "Error while patch method on Replicate API :" + endpoint,
                    e,
                    e.getStatusCode().value(),
                    e.getResponseBodyAsString()
            );
        } finally {
            restTemplate.setRequestFactory(originalFactory);
        }
    }





    /**
     * Performs a POST request without a request body.
     *
     * This method is specifically designed for Replicate API endpoints that expect
     * POST requests without content, such as the prediction cancellation endpoint
     * (/predictions/{prediction_id}/cancel). In the Replicate API, certain actions
     * like cancellations are triggered by POST requests to specific endpoints
     * rather than using DELETE or other HTTP methods.
     *
     * According to the Replicate API documentation, operations like prediction
     * cancellation are implemented as:
     * POST https://api.replicate.com/v1/predictions/{prediction_id}/cancel
     *
     * @param <T> The expected return type
     * @param endpoint The API endpoint path, relative to the base URL
     * @param responseType The class of the expected response object
     * @return The deserialized response object
     * @throws ReplicateApiException If the request fails or returns an error status
     */
    public <T> T post(String endpoint, Class<T> responseType){
        String url = buildUrl(endpoint);

        try {
            HttpEntity<?> entity = new HttpEntity<>(defaultHeaders);
            ResponseEntity<T> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    responseType
            );
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            throw  new ReplicateApiException(
                    "Error while post method on Replicate API :" + endpoint,
                    e,
                    e.getStatusCode().value(),
                    e.getResponseBodyAsString()
            );
        }
    }

    /**
     * Performs a DELETE request to the specified API endpoint.
     *
     * Sends a DELETE request to the Replicate API at the specified endpoint path and
     * deserializes the JSON response into an object of the specified type.
     *
     * @param <T> The expected return type
     * @param endpoint The API endpoint path, relative to the base URL
     * @param responseType The class of the expected response object
     * @return The deserialized response object
     * @throws ReplicateApiException If the request fails or returns an error status
     */
    public <T> T delete(String endpoint , Class<T> responseType) {
        String url = buildUrl(endpoint);

        try {
           HttpEntity<?> entity = new HttpEntity<>(defaultHeaders);
           ResponseEntity<T> response = restTemplate.exchange(
                   url,
                   HttpMethod.DELETE,
                   entity,
                   responseType
           );
           return response.getBody();
        } catch (HttpStatusCodeException e) {
            throw  new ReplicateApiException(
                    "Error while delete method on Replicate API :" + endpoint,
                    e,
                    e.getStatusCode().value(),
                    e.getResponseBodyAsString()
            );
        }

    }



    /**
     * Builds a complete URL by combining the base URL with the endpoint path.
     *
     * Ensures that the endpoint is properly formatted with a leading slash if needed.
     *
     * @param endpoint The API endpoint path
     * @return The complete URL for the API request
     */
    private String buildUrl(String endpoint) {
        String sanitizedEndpoint = sanitizeEndpoint(endpoint);
        return UriComponentsBuilder.fromUriString(baseUrl)
                .path(sanitizedEndpoint)
                .build()
                .toUriString();
    }


    /**
     * Ensures that an endpoint path is properly formatted with a leading slash.
     *
     * @param endpoint The endpoint path to sanitize
     * @return The sanitized endpoint path
     */
    private String sanitizeEndpoint(String endpoint) {
        if (endpoint == null || endpoint.isEmpty()) {
            return "";
        }
        if (!endpoint.startsWith("/")) {
            return "/" + endpoint;
        }
        return endpoint;
    }


}
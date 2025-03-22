package com.nilsw13.spring_replicate.api;

import com.nilsw13.spring_replicate.config.ReplicateProperties;
import com.nilsw13.spring_replicate.exception.ReplicateApiException;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class ReplicateRestClient {


    private final RestTemplate restTemplate;
    private final String baseUrl ;
    private final HttpHeaders defaultHeaders;

    public ReplicateRestClient(ReplicateProperties properties) {
        this.restTemplate = new RestTemplate();

        this.baseUrl = properties.getApiUrl();

        this.defaultHeaders = new HttpHeaders();
        this.defaultHeaders.set(HttpHeaders.AUTHORIZATION, "Token " + properties.getApiKey());
        this.defaultHeaders.setContentType(MediaType.APPLICATION_JSON);
        this.defaultHeaders.setAccept(java.util.Collections.singletonList(MediaType.APPLICATION_JSON));
    }

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

    // post method with headers
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

    // post request without requestBody for delete methods
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




    private String buildUrl(String endpoint) {
        String sanitizedEndpoint = sanitizeEndpoint(endpoint); // Normalisation du chemin de l'endpoint
        return UriComponentsBuilder.fromUriString(baseUrl)
                .path(sanitizedEndpoint)
                .build()
                .toUriString();
    }


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
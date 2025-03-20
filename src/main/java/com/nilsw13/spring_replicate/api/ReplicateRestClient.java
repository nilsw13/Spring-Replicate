package com.nilsw13.spring_replicate.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.nilsw13.spring_replicate.config.ReplicateProperties;
import com.nilsw13.spring_replicate.exception.ReplicateApiException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.reactive.ServerWebExchangeContextFilter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Map;

public class ReplicateRestClient {

    private final WebClient webClient;

    public ReplicateRestClient(ReplicateProperties properties) {
        this.webClient = WebClient.builder()
                .baseUrl(properties.getApiUrl())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Token " + properties.getApiKey())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


    public <T>Mono<T> get(String endpoint, Class<T> responseType) {
        return webClient.get()
                .uri(sanitizeEndpoint(endpoint))
                .retrieve()
                .bodyToMono(responseType)
                .onErrorMap(WebClientResponseException.class, e ->
                        new ReplicateApiException(
                                "Erreur lors de l'appel à l'API Replicate: " + endpoint,
                                e,
                                e.getStatusCode().value(),
                                e.getResponseBodyAsString()
                        )
                );
    }



    public <T> Mono<T> post(String endpoint, Object body , Class<T> responseType){
        return webClient.post()
                .uri(sanitizeEndpoint(endpoint))
                .bodyValue(body != null ? body : new Object())
                .retrieve()
                .bodyToMono(responseType)
                .onErrorMap(WebClientResponseException.class, e ->
                        new ReplicateApiException(
                                "Erreur lors de l'appel à l'API Replicate: " + endpoint,
                                e,
                                e.getStatusCode().value(),
                                e.getResponseBodyAsString()
                        )
                );

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
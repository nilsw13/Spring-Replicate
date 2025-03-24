package com.nilsw13.spring_replicate.exception;

import org.springframework.web.reactive.function.client.WebClientResponseException;

public class ReplicateApiException extends RuntimeException {

    private int statusCode;
    private String responseBody;

    public ReplicateApiException(String message) {
        super(message);
    }

    public ReplicateApiException(String message, Throwable cause) {
        super(message, cause);

        if (cause instanceof WebClientResponseException) {
            WebClientResponseException wcre = (WebClientResponseException) cause;
            this.statusCode = wcre.getStatusCode().value();
            this.responseBody = wcre.getResponseBodyAsString();
        }
    }

    public ReplicateApiException(String message, Throwable cause, int statusCode, String responseBody) {
        super(message, cause);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        if (statusCode > 0) {
            sb.append(" [Status: ").append(statusCode).append("]");
        }

        if (responseBody != null && !responseBody.isEmpty()) {
            sb.append(" Account: ").append(responseBody);
        }

        return sb.toString();
    }
}
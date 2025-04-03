package com.nilsw13.springboot.replicate.exception;

import org.springframework.web.client.HttpStatusCodeException;


/**
 * Custom exception for handling errors from the Replicate API.
 *
 * This exception encapsulates HTTP response details from failed API calls to the
 * Replicate platform, preserving important contextual information such as status
 * codes and response bodies.
 *
 * The exception can be constructed with varying levels of detail, from a simple
 * error message to a full set of HTTP response metadata. When wrapping a
 * WebClientResponseException, it automatically extracts and preserves the status code
 * and response body content.
 *
 * This exception is thrown by service implementations when API calls fail, providing
 * clients with consistent error handling regardless of the underlying HTTP client
 * implementation.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see HttpStatusCodeException
 */

public class ReplicateApiException extends RuntimeException {

    private int statusCode;
    private String responseBody;

    public ReplicateApiException(String message) {
        super(message);
    }


    public ReplicateApiException(String message, Throwable cause) {
        super(message, cause);
        if (cause instanceof HttpStatusCodeException hsce) {
            this.statusCode = hsce.getStatusCode().value();
            this.responseBody = hsce.getResponseBodyAsString();
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
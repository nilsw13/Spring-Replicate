package com.nilsw13.springboot.replicate.unitaire;

import com.nilsw13.springboot.replicate.exception.ReplicateApiException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit-test")
public class ReplicateApiExceptionTest {

    @Test
    public void testConstructorWithMessageTest() {
        String errorMessage = "API connection failed";
        ReplicateApiException exception = new ReplicateApiException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
        assertEquals(0, exception.getStatusCode());
        assertNull(exception.getResponseBody());
    }

    @Test
    public void testConstructorWithHttpStatusCodeException() {
        HttpStatusCodeException httpException = new HttpStatusCodeException(
                HttpStatus.NOT_FOUND,
                "Not Found",
                null,
                "{ \"error\": \"Resource not found\" }".getBytes(),
                StandardCharsets.UTF_8
        ) {};

        ReplicateApiException exception = new ReplicateApiException("API error", httpException);

        // Vérifications
        assertEquals("API error", exception.getMessage());
        assertEquals(404, exception.getStatusCode());
        assertEquals("{ \"error\": \"Resource not found\" }", exception.getResponseBody());
    }
    @Test
    public void testConstructorWithAllParameters() {
        ReplicateApiException exception = new ReplicateApiException(
                "Custom error",
                new RuntimeException("Original error"),
                429,
                "{ \"error\": \"Rate limit exceeded\" }"
        );

        assertEquals("Custom error", exception.getMessage());
        assertEquals(429, exception.getStatusCode());
        assertEquals("{ \"error\": \"Rate limit exceeded\" }", exception.getResponseBody());
        assertTrue(exception.getCause() instanceof RuntimeException);
    }

    @Test
    public void testToString() {
        ReplicateApiException exception = new ReplicateApiException(
                "API error",
                new RuntimeException(),
                403,
                "{ \"error\": \"Forbidden\" }"
        );

        String result = exception.toString();

        assertTrue(result.contains("API error"));
        assertTrue(result.contains("[Status: 403]"));
        assertTrue(result.contains("Account: { \"error\": \"Forbidden\" }"));
    }

}

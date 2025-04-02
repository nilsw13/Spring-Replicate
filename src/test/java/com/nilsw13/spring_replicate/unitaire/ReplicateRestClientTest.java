package com.nilsw13.spring_replicate.unitaire;

import com.nilsw13.spring_replicate.ResponseType.Deployment.DeploymentConfiguration;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.config.ReplicateProperties;
import com.nilsw13.spring_replicate.exception.ReplicateApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@Tag("unit-test")
public class ReplicateRestClientTest {

    @Mock
    private RestTemplate mockRestTemplate;

    private ReplicateRestClient replicateRestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // create instance of properties
        ReplicateProperties properties = new ReplicateProperties();
        properties.setApiKey("test-api-key");

        // create instance of ReplicateRestClient with properties instance as parameter
        replicateRestClient = new ReplicateRestClient(properties);

        // inject mocked restTemplate via reflexion
        try {
            Field restTemplateField = ReplicateRestClient.class.getDeclaredField("restTemplate");
            restTemplateField.setAccessible(true);
            restTemplateField.set(replicateRestClient, mockRestTemplate);

        } catch (Exception e) {
            fail("Failed to set mock RestTemplate: " + e.getMessage());
        }
    }


    @Test
    void testPostThrowsReplicateApiExceptionWhenHttpError() {
        HttpStatusCodeException mockException = mock(HttpStatusCodeException.class);
        when(mockException.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
        when(mockException.getResponseBodyAsString()).thenReturn("{\"error\":\"Test error\"}");

        when(mockRestTemplate.exchange(
                anyString(),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                any(Class.class)
        )).thenThrow(mockException);

        ReplicateApiException exception = assertThrows(ReplicateApiException.class, () -> {
            replicateRestClient.post("test-endpoint", new Object(), Object.class);
        });

        assertEquals(400, exception.getStatusCode());
        assertEquals("{\"error\":\"Test error\"}", exception.getResponseBody());
        assertTrue(exception.getMessage().contains("Error while post method on Replicate API"));

    }

    @Test
    void testPostCustomHeadersThrowsReplicateApiExceptionWhenHttpError() {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Custom-Header", "Test-Value");

        HttpStatusCodeException mockException = mock(HttpStatusCodeException.class);
        when(mockException.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
        when(mockException.getResponseBodyAsString()).thenReturn("{\"error\":\"Test error\"}");

        when(mockRestTemplate.exchange(
                anyString(),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                any(Class.class)
        )).thenThrow(mockException);

        ReplicateApiException exception = assertThrows(ReplicateApiException.class, () -> {
            replicateRestClient.post("test-endpoint", new Object(), headers, Object.class);
        });

        assertEquals(400, exception.getStatusCode());
        assertEquals("{\"error\":\"Test error\"}", exception.getResponseBody());
        assertTrue(exception.getMessage().contains("Error while post method on Replicate API"));

    }

    @Test
    void testPostWithoutRequestBodyThrowsReplicateApiExceptionWhenHttpError() {
        HttpStatusCodeException mockException = mock(HttpStatusCodeException.class);
        when(mockException.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
        when(mockException.getResponseBodyAsString()).thenReturn("{\"error\":\"Test error\"}");

        when(mockRestTemplate.exchange(
                anyString(),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                any(Class.class)
        )).thenThrow(mockException);

        ReplicateApiException exception = assertThrows(ReplicateApiException.class, () -> {
            replicateRestClient.post("test-endpoint", Object.class);
        });

        assertEquals(400, exception.getStatusCode());
        assertEquals("{\"error\":\"Test error\"}", exception.getResponseBody());
        assertTrue(exception.getMessage().contains("Error while post method on Replicate API"));

    }

    @Test
    void testPatchThrowsReplicateApiExceptionWhenHttpError() {
        HttpStatusCodeException mockException = mock(HttpStatusCodeException.class);
        when(mockException.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
        when(mockException.getResponseBodyAsString()).thenReturn("{\"error\":\"Test error\"}");

        when(mockRestTemplate.exchange(
                anyString(),
                eq(HttpMethod.PATCH),
                any(HttpEntity.class),
                any(Class.class)
        )).thenThrow(mockException);

        ReplicateApiException exception = assertThrows(ReplicateApiException.class, () -> {
            replicateRestClient.patch("test-endpoint", new DeploymentConfiguration(), Object.class);
        });

        assertEquals(400, exception.getStatusCode());
        assertEquals("{\"error\":\"Test error\"}", exception.getResponseBody());
        assertTrue(exception.getMessage().contains("Error while patch method on Replicate API"));

    }





    @Test
    void testDeleteThrowsReplicateApiExceptionWhenHttpError () {
        HttpStatusCodeException mockException = mock(HttpStatusCodeException.class);
        when(mockException.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
        when(mockException.getResponseBodyAsString()).thenReturn("{\"error\":\"Test error\"}");

        when(mockRestTemplate.exchange(
                anyString(),
                eq(HttpMethod.DELETE),
                any(HttpEntity.class),
                any(Class.class)
        )).thenThrow(mockException);

        ReplicateApiException exception = assertThrows(ReplicateApiException.class, () -> {
            replicateRestClient.delete("test-endpoint",  Object.class);
        });

        assertEquals(400, exception.getStatusCode());
        assertEquals("{\"error\":\"Test error\"}", exception.getResponseBody());
        assertTrue(exception.getMessage().contains("Error while delete method on Replicate API"));

    }







    @Test
    void testSanitizeEndpoint() throws Exception {

        // access private mehtod via reflection
        Method method = ReplicateRestClient.class.getDeclaredMethod("sanitizeEndpoint", String.class);
        method.setAccessible(true);

        assertEquals("", method.invoke(replicateRestClient, new Object[]{null}));
        assertEquals("", method.invoke(replicateRestClient, ""));
        assertEquals("/test", method.invoke(replicateRestClient, "/test"));
        assertEquals("/test", method.invoke(replicateRestClient, "test"));


    }
}

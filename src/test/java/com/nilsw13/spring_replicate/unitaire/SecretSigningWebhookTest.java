package com.nilsw13.spring_replicate.unitaire;

import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.impl.AccountServiceImpl;
import com.nilsw13.spring_replicate.impl.SecretSigningWebhookServiceImpl;
import com.nilsw13.spring_replicate.model.SecretSigningWebhookResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SecretSigningWebhookTest {

    @Mock
    private ReplicateRestClient replicateRestClient;

    private SecretSigningWebhookServiceImpl secretSigningWebhookService;


    @BeforeEach
    void setUp() {
    secretSigningWebhookService = new SecretSigningWebhookServiceImpl(replicateRestClient);

    }

    @Test
    void testSecretSigningWebhook() {
        SecretSigningWebhookResponse mockResponse = new SecretSigningWebhookResponse();
        mockResponse.setKey("test_key");

        when(replicateRestClient.get("webhooks/default/secret", SecretSigningWebhookResponse.class))
                .thenReturn(Mono.just(mockResponse));

        StepVerifier.create(secretSigningWebhookService.getDefaultSecretSigningWebhook())
                .assertNext(secret -> {
                    assertThat(secret.getKey()).isEqualTo("test_key");
                })
                .verifyComplete();
    }

}
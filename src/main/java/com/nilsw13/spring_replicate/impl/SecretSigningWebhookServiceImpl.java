package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.ResponseType.webhook.SecretSigningWebhookResponse;
import com.nilsw13.spring_replicate.service.SecretWebhook.SecretSigningWebhookService;
import org.springframework.stereotype.Service;

@Service
public class SecretSigningWebhookServiceImpl implements SecretSigningWebhookService {

    private final ReplicateRestClient restClient;

    public SecretSigningWebhookServiceImpl(ReplicateRestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public SecretSigningWebhookResponse getDefaultSecretSigningWebhook() {
        return restClient.get("webhooks/default/secret", SecretSigningWebhookResponse.class);
    }
}
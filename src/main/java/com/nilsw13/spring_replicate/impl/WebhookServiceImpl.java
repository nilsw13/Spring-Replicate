package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.ResponseType.webhook.SecretSigningWebhook;
import com.nilsw13.spring_replicate.service.SecretSigningWebhookService;
import org.springframework.stereotype.Service;

@Service
public class SecretSigningWebhookServiceImpl implements SecretSigningWebhookService {

    private final ReplicateRestClient restClient;

    public SecretSigningWebhookServiceImpl(ReplicateRestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public SecretSigningWebhook getDefaultSecretSigningWebhook() {
        return restClient.get("webhooks/default/secret", SecretSigningWebhook.class);
    }
}
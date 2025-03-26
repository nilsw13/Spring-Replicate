package com.nilsw13.spring_replicate.service;

import com.nilsw13.spring_replicate.ResponseType.webhook.SecretSigningWebhook;

public interface SecretSigningWebhookService {
    SecretSigningWebhook getDefaultSecretSigningWebhook();

}

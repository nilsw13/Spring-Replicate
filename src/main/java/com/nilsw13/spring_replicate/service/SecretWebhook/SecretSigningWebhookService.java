package com.nilsw13.spring_replicate.service.SecretWebhook;

import com.nilsw13.spring_replicate.ResponseType.webhook.SecretSigningWebhookResponse;

public interface SecretSigningWebhookService {
    SecretSigningWebhookResponse getDefaultSecretSigningWebhook();

}

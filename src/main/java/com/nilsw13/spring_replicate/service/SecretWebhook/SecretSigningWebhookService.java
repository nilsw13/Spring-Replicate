package com.nilsw13.spring_replicate.service.SecretWebhook;

import com.nilsw13.spring_replicate.model.SecretSigningWebhookResponse;
import reactor.core.publisher.Mono;

public interface SecretSigningWebhookService {
    SecretSigningWebhookResponse getDefaultSecretSigningWebhook();

}

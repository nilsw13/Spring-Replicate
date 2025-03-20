package com.nilsw13.spring_replicate.service.actions;

import com.nilsw13.spring_replicate.model.SecretSigningWebhookResponse;
import reactor.core.publisher.Mono;

public interface SecretSigningWebhookService {
    Mono<SecretSigningWebhookResponse> getDefaultSecretSigningWebhook();

}

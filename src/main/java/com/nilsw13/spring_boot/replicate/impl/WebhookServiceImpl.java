package com.nilsw13.spring_boot.replicate.impl;

import com.nilsw13.spring_boot.replicate.api.ReplicateRestClient;
import com.nilsw13.spring_boot.replicate.ResponseType.webhook.SigningSecretDefaultWebhook;
import com.nilsw13.spring_boot.replicate.config.ReplicateProperties;
import com.nilsw13.spring_boot.replicate.service.WebhookService;
import org.springframework.stereotype.Service;

/**
 * Implementation of the WebhookService interface for managing webhook operations
 * in the Replicate platform.
 *
 * This class provides the concrete implementation for retrieving webhook signing
 * secrets by communicating with the Replicate API through the ReplicateRestClient.
 * The signing secret is used to verify the authenticity of webhook requests sent
 * by Replicate to your server.
 *
 * This implementation is automatically registered as a Spring bean through its @Service
 * annotation and is used by the auto-configuration system unless overridden by a custom
 * implementation provided by the user.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see WebhookService
 * @see ReplicateRestClient
 * @see SigningSecretDefaultWebhook
 */
@Service
public class WebhookServiceImpl implements WebhookService {

    private final ReplicateRestClient restClient;

    /**
     * Constructs a new WebhookServiceImpl with the required REST client.
     *
     * The REST client is automatically injected by Spring's dependency injection
     * system. This client is configured with the API key from the "replicate.api-key"
     * property and optionally uses a custom API URL if "replicate.api-url" is specified
     * in the application configuration. These properties are managed by the
     * ReplicateProperties class and automatically bound by Spring Boot.
     *
     * @param restClient The client used to communicate with the Replicate API
     * @see ReplicateProperties
     */
    public WebhookServiceImpl(ReplicateRestClient restClient) {
        this.restClient = restClient;
    }

    /**
     * {@inheritDoc}
     *
     * This implementation sends a GET request to the "/webhooks/default/secret" endpoint
     * and deserializes the response into a SigningSecretDefaultWebhook object.
     *
     * The retrieved signing secret should be used in webhook verification logic to ensure
     * that incoming webhook requests are authentic and have not been tampered with. For
     * optimal performance, consider caching this secret rather than fetching it for each
     * webhook verification.
     *
     * @see SigningSecretDefaultWebhook
     */
    @Override
    public SigningSecretDefaultWebhook getDefaultSecretSigningWebhook() {
        return restClient.get("webhooks/default/secret", SigningSecretDefaultWebhook.class);
    }
}
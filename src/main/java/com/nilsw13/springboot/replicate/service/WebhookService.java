package com.nilsw13.springboot.replicate.service;

import com.nilsw13.springboot.replicate.responsetype.webhook.SigningSecretDefaultWebhook;

/**
 * Service interface for managing webhook operations in the Replicate platform.
 * Get the signing secret for the default webhook endpoint. This is used to verify that webhook requests are coming from Replicate.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see SigningSecretDefaultWebhook
 */
public interface WebhookService {

    /**
     * Retrieves the default signing secret for webhooks.
     *
     * This method fetches the signing secret associated with the authenticated
     * user or organization. This secret is used to verify that webhook requests
     * received by your server are authentically from Replicate.
     *
     * To verify a webhook, you need to:
     * 1. Extract the webhook-id, webhook-timestamp, and webhook-signature headers
     * 2. Construct the signed content: webhookId + "." + webhookTimestamp + "." + requestBody
     * 3. Calculate an HMAC-SHA256 signature of this content using the signing secret as the key
     * 4. Compare your calculated signature with the one in the webhook-signature header
     *
     * For optimal performance, consider caching this signing secret rather than
     * fetching it for each webhook verification.
     *
     * @return The default signing secret for webhooks
     * @see SigningSecretDefaultWebhook
     */
    SigningSecretDefaultWebhook getDefaultSecretSigningWebhook();

}

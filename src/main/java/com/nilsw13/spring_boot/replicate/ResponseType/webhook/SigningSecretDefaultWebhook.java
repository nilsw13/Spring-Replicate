package com.nilsw13.spring_boot.replicate.ResponseType.webhook;


/**
 * Represents the signing secret for the default webhook endpoint in the Replicate platform.
 *
 * This class corresponds to the JSON response structure when retrieving the signing secret
 * used to verify the authenticity of webhook requests. The signing secret is a cryptographic
 * key that Replicate uses to sign webhook payloads, allowing recipients to verify that
 * the webhooks are genuinely from Replicate and have not been tampered with.
 *
 * The signing secret is used in the HMAC-SHA256 verification process for webhooks.
 *
 * @author Nilsw13
 * @since 1.0.0
 */
public class SigningSecretDefaultWebhook {

    private String key;

    public SigningSecretDefaultWebhook(String key) {
        this.key = key;
    }

    public SigningSecretDefaultWebhook() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
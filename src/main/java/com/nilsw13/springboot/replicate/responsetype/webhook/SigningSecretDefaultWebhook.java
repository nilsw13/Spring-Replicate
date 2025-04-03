package com.nilsw13.springboot.replicate.responsetype.webhook;


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
        /**
         * Default constructor for SigningSecretDefaultWebhook class.
         *
         * This empty constructor exists for the following reasons:
         * 1. Required by JSON/Jackson deserialization process when mapping API responses
         * 2. Enables library users to instantiate response objects when needed
         * 3. Supports serialization/deserialization in various client implementations
         *
         * Although empty, this constructor is essential for the proper functioning
         * of the API client library and should not be removed.
         */
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
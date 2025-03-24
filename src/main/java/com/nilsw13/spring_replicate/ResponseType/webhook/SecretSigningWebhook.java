package com.nilsw13.spring_replicate.ResponseType.webhook;

public class SecretSigningWebhookResponse {

    private String key;

    public SecretSigningWebhookResponse(String key) {
        this.key = key;
    }

    public SecretSigningWebhookResponse() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
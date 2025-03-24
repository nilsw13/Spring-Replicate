package com.nilsw13.spring_replicate.ResponseType.webhook;

public class SecretSigningWebhook {

    private String key;

    public SecretSigningWebhook(String key) {
        this.key = key;
    }

    public SecretSigningWebhook() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
package com.nilsw13.spring_replicate.ResponseType.webhook;

public enum WebhookEvent {

    START("start"),
    OUTPUT("output"),
    LOGS("logs"),
    COMPLETED("completed");

    private final String value;

    WebhookEvent(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    @Override
    public String toString() {
        return value;
    }
}

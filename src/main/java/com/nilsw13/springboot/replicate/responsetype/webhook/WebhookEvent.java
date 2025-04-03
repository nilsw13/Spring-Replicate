package com.nilsw13.springboot.replicate.responsetype.webhook;

/**
 * Represents the different types of events that can trigger a webhook notification
 * from the Replicate platform.
 *
 * When configuring webhooks for predictions, you can specify which event types should
 * trigger notifications to your webhook endpoint. This allows filtering the events
 * to receive only the notifications that are relevant to your application.
 *
 * Each enum constant corresponds to a specific stage in a prediction's lifecycle:
 * - START: When a prediction begins processing
 * - OUTPUT: When a prediction produces partial or complete output
 * - LOGS: When new logs are available for a prediction
 * - COMPLETED: When a prediction has finished (successfully or with errors)
 *
 * @author Nilsw13
 * @since 1.0.0
 */
public enum WebhookEvent {

    /**
     * Triggered when a prediction begins processing.
     *
     * This event is sent when the prediction transitions from the "starting" state
     * to the "processing" state.
     */
    START("start"),


    /**
     * Triggered when a prediction produces partial or complete output.
     *
     * This event is sent whenever the prediction generates any output, which may
     * happen multiple times during processing for models that produce streaming
     * or incremental results.
     */
    OUTPUT("output"),

    /**
     * Triggered when new logs are available for a prediction.
     *
     * This event is sent when the model produces log messages during execution.
     * These logs can be useful for debugging or monitoring the model's internal processes.
     */
    LOGS("logs"),

    /**
     * Triggered when a prediction has finished processing.
     *
     * This event is sent when the prediction reaches a terminal state, either
     * "succeeded" or "failed". It indicates that no further processing will occur
     * for this prediction.
     */
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

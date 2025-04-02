package com.nilsw13.spring.boot.replicate.impl;

import com.nilsw13.spring.boot.replicate.ResponseType.Training.Training;
import com.nilsw13.spring.boot.replicate.ResponseType.webhook.WebhookEvent;
import com.nilsw13.spring.boot.replicate.api.ReplicateRestClient;
import com.nilsw13.spring.boot.replicate.service.TrainingBuilderService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Implementation of the TrainingBuilderService for configuring and executing training jobs.
 *
 * This class provides the concrete implementation of the builder pattern for creating
 * and executing training jobs against the Replicate API. It handles the construction of
 * training job requests with configurable inputs, destination models, webhook settings,
 * and execution options.
 *
 * The class maintains the source model information (owner, name, version) that will be
 * used as the base for fine-tuning, while allowing configuration of the destination
 * model where the fine-tuned version will be stored.
 *
 * This implementation is automatically registered as a Spring bean through its @Service
 * annotation, though it is typically created and returned by other services rather than
 * being directly injected.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see TrainingBuilderService
 * @see ReplicateRestClient
 */
@Service
public class TrainingBuilderServiceImpl implements TrainingBuilderService {

    private final ReplicateRestClient replicateRestClient;
    private final String modelOwner;
    private final String modelName;
    private final String modelversion;

    private final Map<String, Object> inputs = new HashMap<>();
    private String webhookUrl;
    private String destination;
    private List<String> webhookEventFilter;

    /**
     * Constructs a new TrainingBuilderServiceImpl with the required dependencies.
     * @param replicateRestClient The REST client for API communication
     * @param modelOwner The username or organization that owns the source model
     * @param modelName The name of the source model
     * @param modelversion The version ID of the source model
     */
    public TrainingBuilderServiceImpl(ReplicateRestClient replicateRestClient, String modelOwner, String modelName, String modelversion) {
        this.replicateRestClient = replicateRestClient;
        this.modelOwner = modelOwner;
        this.modelName = modelName;
        this.modelversion = modelversion;
    }


    /**
     * {@inheritDoc}
     *
     * This implementation sets the destination model by combining the owner name
     * and model name into a single string in the format "owner/name". This is mandatory
     * for creating a training job.
     *
     * @param modelOwnerName The username or organization that will own the fine-tuned model
     * @param modelName The name for the fine-tuned model
     * @return This builder instance for method chaining
     */
    @Override
    public TrainingBuilderService destination(String modelOwnerName, String modelName) {
        this.destination = modelOwnerName + "/" + modelName;
        return this;
    }


    /**
     * {@inheritDoc}
     *
     * This implementation adds the specified key-value pair to the inputs map
     * that will be used for the training job.
     *
     * @param key The name of the input parameter
     * @param value The value of the input parameter
     * @return This builder instance for method chaining
     */
    @Override
    public TrainingBuilderService input(String key, Object value) {
        inputs.put(key, value);
        return this;
    }


    /**
     * {@inheritDoc}
     *
     * This implementation adds all entries from the specified map to the inputs map
     * that will be used for the training job. If the provided map is null,
     * no changes are made to the existing inputs.
     *
     * @param inputs A map of input parameter names to their values
     * @return This builder instance for method chaining
     */
    @Override
    public TrainingBuilderService inputs(Map<String, Object> inputs) {
        if(inputs != null ){
            this.inputs.putAll(inputs);
        }
        return this;
    }


    /**
     * {@inheritDoc}
     *
     * This implementation sets the webhook URL that will receive notifications
     * about training job events.
     *
     * @param url The URL to receive webhook notifications
     * @return This builder instance for method chaining
     */
    @Override
    public TrainingBuilderService webhook(String url) {
        this.webhookUrl = url;
        return this;
    }


    /**
     * {@inheritDoc}
     *
     * This implementation sets the filter for webhook events, converting the
     * WebhookEvent enum values to their string representations.
     *
     * @param events A list of event types to trigger webhook notifications
     * @return This builder instance for method chaining
     * @see WebhookEvent
     */
    @Override
    public TrainingBuilderService webhookEventFilter(List<WebhookEvent> events) {
        this.webhookEventFilter = events.stream()
                .map(WebhookEvent::getValue)
                .collect(Collectors.toList());
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * This implementation calls the execute method with a default
     * timeout of 300 seconds if wait is true, or 0 seconds if wait is false.
     *
     * @param wait Whether to wait for the training to complete before returning
     * @return The created training job with its status and results (if wait is true)
     * @throws InterruptedException If the waiting thread is interrupted
     * @see Training
     */
    @Override
    public Training execute(boolean wait) throws InterruptedException {
        return execute(wait, wait ? 300 : 0);
    }

    /**
     * {@inheritDoc}
     *

     * @param wait Whether to wait for the training to complete before returning
     * @param timeoutSeconds The maximum number of seconds to wait for completion
     * @return The created training job with its status and results (if wait is true)
     * @throws InterruptedException If the waiting thread is interrupted
     * @see Training
     */
    @Override
    public Training execute(boolean wait, int timeoutSeconds) throws InterruptedException {
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        requestBody.put("destination", destination);
        requestBody.put("input", inputs);

        if (webhookUrl != null && !webhookUrl.isEmpty()){
            requestBody.put("webhook", webhookUrl);
            if(webhookEventFilter != null && !webhookEventFilter.isEmpty()) {
                requestBody.put("webhook_events_filter", webhookEventFilter);
            }
        }

        if (wait) {
            headers.put("Prefer", "wait=" + timeoutSeconds);
        }

        return replicateRestClient.post(
                "models/" + modelOwner + "/" + modelName + "/versions/" + modelversion + "/trainings",
                requestBody,
                headers,
                Training.class
        );
    }
}

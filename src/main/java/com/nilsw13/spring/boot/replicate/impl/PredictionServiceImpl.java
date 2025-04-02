package com.nilsw13.spring.boot.replicate.impl;

import com.nilsw13.spring.boot.replicate.ResponseType.Prediction.PredictionsList;
import com.nilsw13.spring.boot.replicate.api.ReplicateRestClient;
import com.nilsw13.spring.boot.replicate.ResponseType.Prediction.Prediction;
import com.nilsw13.spring.boot.replicate.config.ReplicateProperties;
import com.nilsw13.spring.boot.replicate.service.PredictionBuilderService;
import com.nilsw13.spring.boot.replicate.service.PredictionService;
import org.springframework.stereotype.Service;

/**
 * Implementation of the PredictionService interface for managing Replicate predictions.
 *
 * This class provides the concrete implementation of prediction-related operations
 * by communicating with the Replicate API through the ReplicateRestClient. It handles
 * the construction of API endpoint paths for prediction operations and the transformation
 * of API responses into domain model objects.
 *
 * This implementation is automatically registered as a Spring bean through its @Service
 * annotation and is used by the auto-configuration system unless overridden by a custom
 * implementation provided by the user.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see PredictionService
 * @see ReplicateRestClient
 */
@Service
public class PredictionServiceImpl implements PredictionService {

    private final ReplicateRestClient restClient;

    /**
     * Constructs a new PredictionServiceImpl with the required REST client.
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
    public PredictionServiceImpl(ReplicateRestClient restClient) {
        this.restClient = restClient;

    }

    private String modelOwner;
    private String modelName;


    /**
     * {@inheritDoc}
     *
     * This implementation sends a GET request to the "/predictions" endpoint
     * and deserializes the response into a PredictionsList object.
     *
     * @see PredictionsList
     */
    @Override
    public PredictionsList list() {
        return restClient.get("predictions", PredictionsList.class);
    }

    /**
     * {@inheritDoc}
     *
     * This implementation sends a GET request to the "/predictions/{id}" endpoint
     * and deserializes the response into a Prediction object.
     *
     * @param id The unique identifier of the prediction
     * @see Prediction
     */
    @Override
    public Prediction get(String id) {
        return restClient.get("predictions/" + id, Prediction.class);
    }

    /**
     * {@inheritDoc}
     *
     * This implementation creates a new PredictionBuilderServiceImpl instance configured
     * with the provided model version and the current model owner and name (if set).
     * The builder can then be used to set inputs and execute the prediction.
     *
     * @param modelVersion The ID of the model version to use for the prediction
     * @see PredictionBuilderServiceImpl
     */
    @Override
    public PredictionBuilderService create(String modelVersion) {
        return new PredictionBuilderServiceImpl(restClient, modelVersion, modelOwner, modelName);
    }

    /**
     * {@inheritDoc}
     *
     * This implementation sends a POST request to the "/predictions/{id}/cancel" endpoint
     * and deserializes the response into a Prediction object with updated status information.
     *
     * If the prediction is already in a terminal state (succeeded, failed, or canceled),
     * this operation has no effect and the prediction's details are simply returned.
     *
     * @param id The unique identifier of the prediction to cancel
     * @see Prediction
     */
    @Override
    public Prediction cancel(String id) {
        return restClient.post("predictions/" + id + "/cancel", Prediction.class);
    }


}

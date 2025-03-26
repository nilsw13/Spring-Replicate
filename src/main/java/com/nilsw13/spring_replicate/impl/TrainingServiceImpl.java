package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.ResponseType.Training.Training;
import com.nilsw13.spring_replicate.ResponseType.Training.TrainingList;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.config.ReplicateProperties;
import com.nilsw13.spring_replicate.service.TrainingBuilderService;
import com.nilsw13.spring_replicate.service.TrainingService;
import org.springframework.stereotype.Service;

/**
 * Implementation of the TrainingService interface for managing training jobs
 * in the Replicate platform.
 *
 * This class provides the concrete implementation of training-related operations
 * by communicating with the Replicate API through the ReplicateRestClient. It handles
 * the construction of API endpoint paths for training operations and the transformation
 * of API responses into domain model objects.
 *
 * This implementation is automatically registered as a Spring bean through its @Service
 * annotation and is used by the auto-configuration system unless overridden by a custom
 * implementation provided by the user.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see TrainingService
 * @see Training
 * @see TrainingList
 * @see ReplicateRestClient
 */
@Service
public class TrainingServiceImpl implements TrainingService {
    private final ReplicateRestClient replicateRestClient;


    /**
     * Constructs a new TrainingServiceImpl with the required REST client.
     *
     * The REST client is automatically injected by Spring's dependency injection
     * system. This client is configured with the API key from the "replicate.api-key"
     * property and optionally uses a custom API URL if "replicate.api-url" is specified
     * in the application configuration. These properties are managed by the
     * ReplicateProperties class and automatically bound by Spring Boot.
     *
     * @param replicateRestClient The client used to communicate with the Replicate API
     * @see ReplicateProperties
     */
    public TrainingServiceImpl(ReplicateRestClient replicateRestClient) {
        this.replicateRestClient = replicateRestClient;
    }

    /**
     * {@inheritDoc}
     *
     * This implementation creates a new TrainingBuilderServiceImpl instance configured
     * with the specified model owner, name, and version. The builder can then be used
     * to set inputs and execute the training job.
     *
     * @param modelOwner The username or organization that owns the model
     * @param modelName The name of the model
     * @param modelVersion The ID of the model version to use as a base for training
     * @return A builder for configuring and executing the training job
     * @see TrainingBuilderServiceImpl
     */
    @Override
    public TrainingBuilderService create(String modelOwner, String modelName, String modelVersion) {
        return new TrainingBuilderServiceImpl(replicateRestClient, modelOwner, modelName, modelVersion);
    }

    /**
     * {@inheritDoc}
     *
     * This implementation sends a POST request to the "/trainings/{id}/cancel" endpoint
     * and deserializes the response into a Training object with updated status information.
     *
     * If the training job is already in a terminal state (succeeded, failed, or canceled),
     * this operation has no effect and the training job's details are simply returned.
     *
     * @param trainingId The unique identifier of the training job to cancel
     * @return The training job with updated status information
     * @see Training
     */
    @Override
    public Training cancel(String trainingId) {
        return replicateRestClient.post("trainings/" + trainingId + "/" + "cancel", Training.class);
    }

    /**
     * {@inheritDoc}
     *
     * This implementation sends a GET request to the "/trainings/{id}" endpoint
     * and deserializes the response into a Training object.
     *
     *
     * @param trainingId The unique identifier of the training job
     * @return The requested training job with all its details
     * @see Training
     */
    @Override
    public Training get(String trainingId) {
        return replicateRestClient.get("trainings/" + trainingId, Training.class);
    }

    /**
     * {@inheritDoc}
     *
     * This implementation sends a GET request to the "/trainings" endpoint
     * and deserializes the response into a TrainingList object.
     *
     * @return A paginated list of training jobs
     * @see TrainingList
     */
    @Override
    public TrainingList list() {
        return replicateRestClient.get("trainings", TrainingList.class);
    }
}

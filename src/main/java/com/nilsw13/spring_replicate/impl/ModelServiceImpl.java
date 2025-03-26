package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.ResponseType.Model.Model;
import com.nilsw13.spring_replicate.ResponseType.Model.ModelList;
import com.nilsw13.spring_replicate.ResponseType.Model.ModelVersionList;
import com.nilsw13.spring_replicate.ResponseType.Model.Version;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.service.ModelService;
import com.nilsw13.spring_replicate.service.PredictionBuilderService;
import org.springframework.stereotype.Service;

/**
 * Implementation of the ModelService interface for managing Replicate models.
 *
 * This class provides the concrete implementation of model-related operations
 * by communicating with the Replicate API through the ReplicateRestClient. It handles
 * the construction of API endpoint paths for model operations and the transformation
 * of API responses into domain model objects.
 *
 * This implementation is automatically registered as a Spring bean through its @Service
 * annotation and is used by the auto-configuration system unless overridden by a custom
 * implementation provided by the user.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see ModelService
 * @see ReplicateRestClient
 */
@Service
public class ModelServiceImpl implements ModelService {

    private final ReplicateRestClient replicateRestClient;
    private String modelVersion;


    /**
     * Constructs a new ModelServiceImpl with the required REST client.
     *
     * The REST client is automatically injected by Spring's dependency injection
     * system. This client is configured with the API key from the "replicate.api-key"
     * property and optionally uses a custom API URL if "replicate.api-url" is specified
     * in the application configuration. These properties are managed by the
     * ReplicateProperties class and automatically bound by Spring Boot.
     *
     * @param replicateRestClient The client used to communicate with the Replicate API
     * @see com.nilsw13.spring_replicate.config.ReplicateProperties
     */
    public ModelServiceImpl(ReplicateRestClient replicateRestClient) {
        this.replicateRestClient = replicateRestClient;
    }


    /**
     * {@inheritDoc}
     *
     * This implementation sends a POST request to the "/models" endpoint
     * with the provided model object serialized as JSON in the request body.
     */
    @Override
    public Model create(Model request) {
        return replicateRestClient.post("models", request, Model.class);
    }

    /**
     * {@inheritDoc}
     *
     * This implementation creates a new PredictionBuilderServiceImpl instance
     * configured with the model owner and name. The modelVersion field is passed
     * to allow predictions against a specific version if set, otherwise the
     * latest version will be used.
     */
    @Override
    public PredictionBuilderService createModelPrediction(String modelOwner, String modelName) {
        return new PredictionBuilderServiceImpl(replicateRestClient, modelVersion, modelOwner, modelName);
    }


    /**
     * {@inheritDoc}
     *
     * This implementation sends a GET request to the "/models/{owner}/{name}" endpoint
     * and deserializes the response into a Model object.
     *
     * @see Model
     */
    @Override
    public Model get(String modelOwner, String modelName) {
        return replicateRestClient.get("models/" + modelOwner + "/" + modelName , Model.class);
    }


    /**
     * {@inheritDoc}
     *
     * This implementation sends a DELETE request to the "/models/{owner}/{name}" endpoint
     * and deserializes the response into a Model object containing the deleted model's details.
     *
     * @see Model
     */
    @Override
    public Model delete(String modelOwner, String modelName) {
        return replicateRestClient.delete("models/" + modelOwner + "/" + modelName, Model.class );
    }

    /**
     * {@inheritDoc}
     *
     * This implementation sends a GET request to the "/models" endpoint
     * and deserializes the response into a ModelList object.
     *
     * @see ModelList
     */
    @Override
    public ModelList list() {
        return replicateRestClient.get("models", ModelList.class);
    }


    /**
     * {@inheritDoc}
     *
     * This implementation sends a GET request to the "/models/{owner}/{name}/versions" endpoint
     * and deserializes the response into a ModelVersionList object.
     *
     * @see ModelVersionList
     */
    @Override
    public ModelVersionList listModelVersions(String modelOwner, String modelName) {
        return replicateRestClient.get("models/" + modelOwner + "/" + modelName + "/" +"versions", ModelVersionList.class);
    }


    /**
     * {@inheritDoc}
     *
     * This implementation sends a GET request to the "/models/{owner}/{name}/versions/{versionId}" endpoint
     * and deserializes the response into a Version object.
     *
     * @see Version
     */
    @Override
    public Version getModelVersion(String modelOwner, String modelName, String versionId) {
        return replicateRestClient.get("models/" + modelOwner + "/" + modelName + "/"  + "versions/"  + versionId, Version.class);
    }

    /**
     * {@inheritDoc}
     *
     * This implementation sends a DELETE request to the "/models/{owner}/{name}/versions/{versionId}" endpoint
     * and deserializes the response into a Version object containing the deleted version's details.
     *
     * @see Version
     */
    @Override
    public Version deleteModelVersion(String modelOwner, String modelName, String versionId) {
        return replicateRestClient.delete("models/" + modelOwner + "/" + modelName + "/"  + "versions/" + versionId, Version.class);
    }

    /**
     * {@inheritDoc}
     *
     * This implementation sends a GET request to the "/models/{owner}/{name}/readme" endpoint
     * and returns the response as a String containing the README content.
     */
    @Override
    public String getModelReadme(String modelOwner, String modelName) {
        return replicateRestClient.get("models/" + modelOwner + "/" + modelName + "/" + "readme", String.class);
    }
}

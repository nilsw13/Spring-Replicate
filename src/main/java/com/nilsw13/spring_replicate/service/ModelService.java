package com.nilsw13.spring_replicate.service;

import com.nilsw13.spring_replicate.ResponseType.Model.Model;
import com.nilsw13.spring_replicate.ResponseType.Model.ModelList;
import com.nilsw13.spring_replicate.ResponseType.Model.ModelVersionList;
import com.nilsw13.spring_replicate.ResponseType.Model.Version;

/**
 * Service interface for managing models in the Replicate platform.
 *
 * This service provides methods to create, retrieve, list, and delete models and
 * model versions, as well as access their associated metadata. It also offers
 * functionality to initiate predictions using specific models or versions.
 *
 * Models in Replicate represent machine learning models that can be run to perform
 * various tasks like image generation, text processing, or other AI functions.
 * Each model can have multiple versions, which represent different trained instances
 * with potentially different capabilities or performance characteristics.

 * @author Nilsw13
 * @since 1.0.0
 * @see Model
 * @see Version
 * @see PredictionBuilderService
 */
public interface ModelService {

    /**
     * Creates a new model on the Replicate platform.
     *
     * This method enables users to create their own models in Replicate.
     * The model request should include essential information like the model name,
     * visibility settings, and other required metadata.
     *
     * @param request The model creation request containing model details
     * @return The newly created model with all server-assigned fields populated
     */
    Model create(Model request);

    /**
     * Creates a prediction builder for running a prediction on a specific model.
     *
     * This method returns a builder that allows you to configure and execute
     * a prediction using the specified model. The builder pattern makes it
     * easy to set inputs and other parameters before executing the prediction.
     *
     * @param modelOwner The username or organization that owns the model
     * @param modelName The name of the model
     * @return A builder for configuring and executing the prediction
     * @see PredictionBuilderService
     */
    PredictionBuilderService createModelPrediction(String modelOwner, String modelName);

    /**
     * Retrieves a specific model by its owner and name.
     *
     * This method fetches detailed information about a model, including its
     * description, visibility, and metadata about its latest version.
      *
     * @param modelOwner The username or organization that owns the model
     * @param modelName The name of the model
     * @return The requested model with all its details
     */
    Model get(String modelOwner, String modelName);

    /**
     * Deletes a specific model from the Replicate platform.
     *
     * This method permanently removes a model and all its versions. It's important
     * to note that this operation can only be performed by the model owner.
     *
     * @param modelOwner The username or organization that owns the model
     * @param modelName The name of the model
     * @return The deleted model
     */
    Model delete(String modelOwner, String modelName);

    /**
     * Lists all models available to the authenticated user.
     *
     * This method returns a paginated list of public models on Replicate.
     * The list displays public models .
     *
     * @return A paginated list of models
     * @see ModelList
     */
    ModelList list();

    /**
     * Lists all versions of a specific model.
     *
     * This method returns a paginated list of all versions that have been
     * created for the specified model. Each version represents a specific
     * trained instance of the model.
     *
     * @param modelOwner The username or organization that owns the model
     * @param modelName The name of the model
     * @return A paginated list of model versions
     * @see ModelVersionList
     */
    ModelVersionList listModelVersions(String modelOwner, String modelName);


    /**
     * Retrieves a specific version of a model.
     *
     * This method fetches detailed information about a specific version of a model,
     * including its OpenAPI schema which describes the inputs and outputs for
     * running predictions.
     *
     * @param modelOwner The username or organization that owns the model
     * @param modelName The name of the model
     * @param versionId The ID of the version to retrieve
     * @return The requested model version with all its details
     * @see Version
     */
    Version getModelVersion(String modelOwner, String modelName, String versionId);


    /**
     * Deletes a specific version of a model.
     *
     * This method permanently removes a version of a model. It's important to note
     * that this operation can only be performed by the model owner, and only if
     * the version is not being used by any deployments.
     *
     * @param modelOwner The username or organization that owns the model
     * @param modelName The name of the model
     * @param versionId The ID of the version to delete
     * @return The deleted model version
     * @see Version
     */
    Version deleteModelVersion(String modelOwner, String modelName, String versionId);

    /**
     * Retrieves the README content for a model.
     *
     * The README typically contains detailed documentation about the model,
     * including its purpose, how to use it, examples, and any important notes
     * or warnings. This content is usually formatted in Markdown.
     *
     * @param modelOwner The username or organization that owns the model
     * @param modelName The name of the model
     * @return The README content as a string, typically in Markdown format
     */
    String getModelReadme(String modelOwner, String modelName);

}

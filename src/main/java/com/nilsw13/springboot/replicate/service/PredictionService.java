package com.nilsw13.springboot.replicate.service;

import com.nilsw13.springboot.replicate.responsetype.prediction.Prediction;
import com.nilsw13.springboot.replicate.responsetype.prediction.PredictionsList;

/**
 * Service interface for managing predictions in the Replicate platform.
 *
 * This service provides methods to create, retrieve, list, and cancel predictions.
 * Predictions represent specific executions of models with inputs and outputs.
 * They are the primary way to run machine learning models on Replicate.
 *
 * Predictions go through several states during their lifecycle:
 * - starting: The prediction is being prepared for execution
 * - processing: The model is running and generating output
 * - succeeded: The prediction completed successfully
 * - failed: The prediction encountered an error
 * - canceled: The prediction was manually canceled
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see Prediction
 * @see PredictionBuilderService
 */
public interface PredictionService {

    /**
     * Lists all predictions owned by the authenticated user.
     *
     * This method returns a paginated list of predictions created by the
     * authenticated user, sorted by creation time with the most recent predictions first.
     * Use the pagination links in the response to navigate through the results.
     *
     * @return A paginated list of predictions
     * @see PredictionsList
     */
    PredictionsList list();

    /**
     * Retrieves a specific prediction by its ID.
     *
     * This method fetches detailed information about a prediction, including
     * its current status, inputs, outputs (if available), and timing information.
     *
     * @param id The unique identifier of the prediction
     * @return The requested prediction with all its details
     * @see Prediction
     */
    Prediction get(String id);

    /**
     * Creates a builder for configuring and executing a new prediction.
     *
     * This method returns a builder that allows you to set inputs and other parameters
     * for a prediction, and then execute it. The builder pattern makes it easy to
     * configure complex predictions in a readable way.
     *
     * @param modelVersion The ID of the model version to use for the prediction
     * @return A builder for configuring and executing the prediction
     * @see PredictionBuilderService
     */
    PredictionBuilderService create(String modelVersion);

    /**
     * Cancels a running prediction.
     *
     * This method attempts to cancel a prediction that is in the "starting" or "processing"
     * state. Once canceled, the prediction's status will change to "canceled" and it will
     * no longer consume compute resources.
     *
     * If the prediction is already in a terminal state (succeeded, failed, or canceled),
     * this operation has no effect and the prediction's details are simply returned.
     *
     * @param id The unique identifier of the prediction to cancel
     * @return The prediction with updated status information
     * @see Prediction
     */
    Prediction cancel(String id);
}

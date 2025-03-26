package com.nilsw13.spring_replicate.service;

import com.nilsw13.spring_replicate.ResponseType.Training.Training;
import com.nilsw13.spring_replicate.ResponseType.Training.TrainingList;


/**
 * Service interface for managing training jobs in the Replicate platform.
 *
 * This service provides methods to create, retrieve, list, and cancel training jobs.
 * Training jobs are used to fine-tune existing models with custom data to create
 * new model versions that are tailored to specific use cases.
 *
 * Training jobs typically go through several states during their lifecycle:
 * - starting: The training is being prepared
 * - processing: The training is running
 * - succeeded: The training completed successfully
 * - failed: The training encountered an error
 * - canceled: The training was manually canceled
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see Training
 * @see TrainingList
 * @see TrainingBuilderService
 */
public interface TrainingService {


        /**
         * Creates a builder for configuring and executing a new training job.
         *
         * This method returns a builder that allows you to set inputs and other parameters
         * for a training job, and then execute it. The builder pattern makes it easy to
         * configure complex training jobs in a readable way.
         *
         * @param modelOwner The username or organization that owns the model
         * @param modelName The name of the model
         * @param modelVersion The ID of the model version to use as a base for training
         * @return A builder for configuring and executing the training job
         * @see TrainingBuilderService
         */
        TrainingBuilderService create(String modelOwner, String modelName, String modelVersion);

        /**
         * Cancels a running training job.
         *
         * This method attempts to cancel a training job that is in the "starting" or "processing"
         * state. Once canceled, the training job's status will change to "canceled" and it will
         * no longer consume compute resources.
         *
         * If the training job is already in a terminal state (succeeded, failed, or canceled),
         * this operation has no effect and the training job's details are simply returned.
         *
         * @param trainingId The unique identifier of the training job to cancel
         * @return The training job with updated status information
         * @see Training
         */
        Training cancel(String trainingId);


        /**
         * Retrieves a specific training job by its ID.
         *
         * This method fetches detailed information about a training job, including
         * its current status, inputs, outputs (if available), and timing information.
         *
         * @param trainingId The unique identifier of the training job
         * @return The requested training job with all its details
         * @see Training
         */
        Training get(String trainingId);

        /**
         * Lists all training jobs owned by the authenticated user.
         *
         * This method returns a paginated list of training jobs created by the
         * authenticated user, sorted by creation time with the most recent training jobs first.
         * Use the pagination links in the response to navigate through the results.
         *
         * @return A paginated list of training jobs
         * @see TrainingList
         */
        TrainingList list();
}

package com.nilsw13.springboot.replicate.service;

import com.nilsw13.springboot.replicate.responsetype.training.Training;
import com.nilsw13.springboot.replicate.responsetype.webhook.WebhookEvent;

import java.util.List;
import java.util.Map;


/**
 * Service interface for building and executing training jobs in the Replicate platform.
 *
 * This interface provides a fluent builder pattern for configuring training job requests
 * before execution. It allows setting the destination ( which is mandatory )  model, input parameters, webhook
 * configurations, and execution options in a chainable manner.
 *
 * Training jobs are used to fine-tune existing models with custom data to create
 * new model versions that are tailored to specific use cases. The builder pattern
 * makes it easy to configure complex training jobs with multiple parameters.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see Training
 * @see TrainingService
 */
public interface TrainingBuilderService {

    /**
     * Sets the destination model for the training job.
     *
     * The destination specifies where the resulting fine-tuned model version will be stored.
     * This is your own model that will be created or updated based on the training results.
     * The source model for training is specified separately when creating the builder via
     * TrainingService.create(modelOwner, modelName, modelVersion).
     *
     * @param modelOwnerName The username or organization that will own the fine-tuned model
     * @param modelName The name for the fine-tuned model
     * @return This builder instance for method chaining
     */
    TrainingBuilderService destination(String modelOwnerName, String modelName);


    /**
     * Sets a single input parameter for the training job.
     *
     * Each model's training process expects specific input parameters.
     * Consult the model's documentation to determine the required training inputs.
     * Common inputs include training data, validation data, and hyperparameters.
     *
     * @param key The name of the input parameter
     * @param value The value of the input parameter
     * @return This builder instance for method chaining
     */
    TrainingBuilderService input (String key, Object value);

    /**
     * Sets multiple input parameters for the training job at once.
     *
     * This method adds all entries from the provided map to the current inputs.
     * If the map is null, no changes are made.
     *
     * @param inputs A map of input parameter names to their values
     * @return This builder instance for method chaining
     */
    TrainingBuilderService inputs(Map<String, Object> inputs);

    /**
     * Sets a webhook URL to receive notifications about training job events.
     *
     * When a webhook is configured, Replicate will send HTTP POST requests to this URL
     * when certain events occur, such as when a training job starts, completes, or fails.
     *
     * @param url The URL to receive webhook notifications
     * @return This builder instance for method chaining
     *
     */
    TrainingBuilderService webhook(String url);

    /**
     * Sets a filter for webhook events to limit which events trigger notifications.
     *
     * By default, all events trigger webhook notifications. This method allows
     * specifying which event types should trigger notifications.
     *
     * @param events A list of event types to trigger webhook notifications
     * @return This builder instance for method chaining
     * @see WebhookEvent
     */
    TrainingBuilderService webhookEventFilter(List<WebhookEvent> events);

    /**
     * Executes the training job with default timeout options.
     *
     * This method submits the training job to Replicate and returns the result.
     * If wait is true, the method will wait for up to 300 seconds for the training
     * to complete before returning. If wait is false, the method returns immediately
     * with a training job in the "starting" state.
     *
     * Note that training jobs typically take much longer than 300 seconds to complete.
     * For  training jobs, it's recommended to set wait to false and poll the
     * status separately with the id returned or use webhooks.
     *
     * @param wait Whether to wait for the training to complete before returning
     * @return The created training job with its status and results (if wait is true)
     * @throws InterruptedException If the waiting thread is interrupted
     */
    Training execute(boolean wait) throws InterruptedException;

    /**
     * Executes the training job with a custom timeout.
     *
     * This method submits the training job to Replicate and returns the result.
     * If wait is true, the method will wait for up to the specified number of seconds
     * for the training to complete before returning. If wait is false, the method
     * returns immediately with a training job in the "starting" state.
     *
     * Note that training jobs can take a long time to complete. For longer training
     * jobs, consider using a larger timeout value or set wait to false and poll the
     * status separately or use webhooks.
     *
     * @param wait Whether to wait for the training to complete before returning
     * @param timeoutSeconds The maximum number of seconds to wait for completion
     * @return The created training job with its status and results (if wait is true)
     * @throws InterruptedException If the waiting thread is interrupted
     */
    Training execute(boolean wait, int timeoutSeconds) throws InterruptedException;
}

package com.nilsw13.spring_boot.replicate.service;

import com.nilsw13.spring_boot.replicate.ResponseType.Prediction.Prediction;
import com.nilsw13.spring_boot.replicate.ResponseType.webhook.WebhookEvent;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Service interface for building and executing predictions in the Replicate platform.
 *
 * This interface provides a fluent builder pattern for configuring prediction requests
 * before execution. It allows setting input parameters, webhook configurations, and
 * execution options in a chainable manner.
 *
 * The service supports three different prediction workflows:
 * - Direct predictions using a specific model version
 * - Model-based predictions using a model owner and name
 * - Deployment-based predictions using a deployment owner and name
 *
 * Each workflow has its own execution method with options for synchronous or asynchronous execution.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see Prediction
 */
public interface PredictionBuilderService {



    /**
     * Sets a file input parameter for the prediction using a data URL encoding.
     *
     * This method reads the provided file, converts it to a data URL format
     * (base64-encoded with appropriate MIME type), and adds it to the prediction inputs.
     * This approach is suitable for smaller files (generally under 256KB).
     *
     * For larger files, consider hosting them externally and providing their URL
     * using the standard input() method instead.
     *
     * @param key The name of the input parameter
     * @param file The file to be encoded and included in the prediction
     * @return This builder instance for method chaining
     * @throws IOException If the file cannot be read or an error occurs during encoding
     */
    PredictionBuilderService file(String key, File file)throws IOException;



    /**
     * Sets an image input parameter for the prediction using a data URL encoding.
     *
     * This method is specifically optimized for image files. It reads the provided image,
     * automatically detects its format (JPEG, PNG, etc.), converts it to an appropriate
     * data URL format, and adds it to the prediction inputs.
     *
     * This approach is suitable for smaller images (generally under 256KB). For larger
     * images, consider hosting them externally and providing their URL using the
     * standard input() method instead.
     *
     * Many vision models in Replicate require image inputs in specific formats.
     * This method ensures proper formatting of the image data for compatibility
     * with such models.
     *
     * @param key The name of the input parameter
     * @param imageFile The image file to be encoded and included in the prediction
     * @return This builder instance for method chaining
     * @throws IOException If the image cannot be read or an error occurs during encoding
     */
    PredictionBuilderService image(String key, File imageFile) throws IOException;


    /**
     * Sets a single input parameter for the prediction.
     *
     * Each model or deployment expects specific input parameters with appropriate types.
     * Consult the model's documentation to determine the required inputs.
     *
     * @param key The name of the input parameter
     * @param value The value of the input parameter
     * @return This builder instance for method chaining
     */
    PredictionBuilderService input(String key, Object value);

    /**
     * Sets multiple input parameters for the prediction at once.
     *
     * This method adds all entries from the provided map to the current inputs.
     * If the map is null, no changes are made.
     *
     * @param inputs A map of input parameter names to their values
     * @return This builder instance for method chaining
     */



    PredictionBuilderService inputs(Map<String, Object> inputs);


    /**
     * Sets a webhook URL to receive notifications about prediction events.
     *
     * When a webhook is configured, Replicate will send HTTP POST requests to this URL
     * when certain events occur, such as when a prediction starts, completes, or fails.
     *
     * @param url The URL to receive webhook notifications
     * @return This builder instance for method chaining
     */
    PredictionBuilderService webhook(String url);

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
    PredictionBuilderService webhookEventFilter(List<WebhookEvent> events);


    /**
     * Executes the prediction using a deployment with default timeout options.
     *
     * This method submits the prediction to the specified deployment and returns the result.
     * If wait is true, the method will wait for up to 300 seconds for the prediction to complete
     * before returning. If wait is false, the method returns immediately with a prediction id
     * in the "starting" state.
     *
     * @param wait Whether to wait for the prediction to complete before returning
     * @return The created prediction with its status and results (if wait is true)
     * @throws InterruptedException If the waiting thread is interrupted
     */
    Prediction executeFromDeployment(boolean wait) throws InterruptedException;

    /**
     * Executes the prediction using a deployment with a custom timeout.
     *
     * This method submits the prediction to the specified deployment and returns the result.
     * If wait is true, the method will wait for up to the specified number of seconds for
     * the prediction to complete before returning. If wait is false, the method returns
     * immediately with a prediction in the "starting" state.
     *
     * @param wait Whether to wait for the prediction to complete before returning
     * @param timeoutSeconds The maximum number of seconds to wait for completion
     * @return The created prediction with its status and results (if wait is true)
     * @throws InterruptedException If the waiting thread is interrupted
     */
    Prediction executeFromDeployment(boolean wait, int timeoutSeconds) throws InterruptedException;

    /**
     * Executes the prediction using a model with default timeout options.
     *
     * This method submits the prediction to the specified model and returns the result.
     * If wait is true, the method will wait for up to 300 seconds for the prediction to complete
     * before returning. If wait is false, the method returns immediately with a prediction
     * in the "starting" state.
     *
     * @param wait Whether to wait for the prediction to complete before returning
     * @return The created prediction with its status and results (if wait is true)
     * @throws InterruptedException If the waiting thread is interrupted
     */
    Prediction executeFromModel(boolean wait) throws InterruptedException;

    /**
     * Executes the prediction using a model with a custom timeout.
     *
     * This method submits the prediction to the specified model and returns the result.
     * If wait is true, the method will wait for up to the specified number of seconds for
     * the prediction to complete before returning. If wait is false, the method returns
     * immediately with a prediction in the "starting" state.
     *
     * @param wait Whether to wait for the prediction to complete before returning
     * @param timeoutSeconds The maximum number of seconds to wait for completion
     * @return The created prediction with its status and results (if wait is true)
     * @throws InterruptedException If the waiting thread is interrupted
     */
    Prediction executeFromModel(boolean wait, int timeoutSeconds) throws InterruptedException;

    /**
     * Executes the prediction using a specific model version with default timeout options.
     *
     * This method submits the prediction to the specified model version and returns the result.
     * If wait is true, the method will wait for up to 300 seconds for the prediction to complete
     * before returning. If wait is false, the method returns immediately with a prediction
     * in the "starting" state.
     *
     * @param wait Whether to wait for the prediction to complete before returning
     * @return The created prediction with its status and results (if wait is true)
     * @throws InterruptedException If the waiting thread is interrupted
     */
    Prediction execute(boolean wait) throws InterruptedException;


    /**
     * Executes the prediction using a specific model version with a custom timeout.
     *
     * This method submits the prediction to the specified model version and returns the result.
     * If wait is true, the method will wait for up to the specified number of seconds for
     * the prediction to complete before returning. If wait is false, the method returns
     * immediately with a prediction in the "starting" state.
     *
     * @param wait Whether to wait for the prediction to complete before returning
     * @param timeoutSeconds The maximum number of seconds to wait for completion
     * @return The created prediction with its status and results (if wait is true)
     * @throws InterruptedException If the waiting thread is interrupted
     */
    Prediction execute(boolean wait, int timeoutSeconds) throws InterruptedException;
}

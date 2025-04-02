package com.nilsw13.spring.boot.replicate.impl;

import com.nilsw13.spring.boot.replicate.ResponseType.webhook.WebhookEvent;
import com.nilsw13.spring.boot.replicate.api.ReplicateRestClient;
import com.nilsw13.spring.boot.replicate.ResponseType.Prediction.Prediction;

import com.nilsw13.spring.boot.replicate.service.FileUtilsService;
import com.nilsw13.spring.boot.replicate.service.PredictionBuilderService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



/**
 * Implementation of the PredictionBuilderService for configuring and executing predictions.
 *
 * This class is designed to handle  direct model predictions (which require a model version)
 * model based predictions,  deployment-based predictions (which don't require a version). The class provides specialized
 * execution methods for each use case:
 * - execute(): For direct predictions using the generic /predictions endpoint
 * - executeFromModel(): For predictions using the /models/{owner}/{name}/predictions endpoint
 * - executeFromDeployment(): For predictions using the /deployments/{owner}/{name}/predictions endpoint
 *
 * When using this class for deployment-based predictions, the modelVersion parameter can be null
 * as it is not used in the deployment prediction flow.
 */
@Service
public class PredictionBuilderServiceImpl implements PredictionBuilderService {

    private final ReplicateRestClient restClient;
    private final String modelVersion;
    private final String modelOwner;
    private final String modelName;


    private final Map<String, Object> inputs = new HashMap<>();
    private String webhookUrl;
    private List<String> webhookEventFilter;



    /**
     * Constructs a PredictionBuilderServiceImpl instance.
     *
     * @param restClient The REST client for API communication
     * @param modelVersion The model version (can be null when used for deployment predictions)
     * @param modelOwner The owner of the model or deployment
     * @param modelName The name of the model or deployment
     */
    public PredictionBuilderServiceImpl(ReplicateRestClient restClient, String modelVersion, String modelOwner, String modelName) {
        this.restClient = restClient;
        this.modelVersion = modelVersion;
        this.modelOwner = modelOwner;
        this.modelName = modelName;

    }


    /**
     * {@inheritDoc}
     *
     * This implementation converts the file to a data URL and adds it to the
     * inputs map that will be sent to the model during prediction execution.
     * Data URLs are suitable for files smaller than 256KB. For larger files,
     * consider hosting them externally and using their HTTP URL instead.
     *
     * @param key The name of the input parameter
     * @param file The file to upload
     * @return This builder instance for method chaining
     * @throws IOException If an error occurs while reading or encoding the file
     * @see FileUtilsService#fileToDataUrl(File)
     */
    @Override
    public PredictionBuilderService file(String key, File file) throws IOException {
     String dataUrl = FileUtilsService.fileToDataUrl(file);
     inputs.put(key, dataUrl);
       return this;
    }

    /**
     * {@inheritDoc}
     *
     * This implementation converts the image file to a data URL with the appropriate
     * MIME type and adds it to the inputs map that will be sent to the model during
     * prediction execution. This method is optimized for image files and ensures the
     * correct image format is detected.
     *
     * Data URLs are suitable for images smaller than 256KB. For larger images,
     * consider hosting them externally and using their HTTP URL instead.
     *
     * @param key The name of the input parameter
     * @param imageFile The image file to upload
     * @return This builder instance for method chaining
     * @throws IOException If an error occurs while reading or encoding the image file
     * @see FileUtilsService#imageToDataUrl(File)
     */
    @Override
    public PredictionBuilderService image(String key, File imageFile) throws IOException {
        String dataUrl = FileUtilsService.imageToDataUrl(imageFile);
        inputs.put(key, dataUrl);
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * This implementation adds the specified key-value pair to the inputs map
     * that will be sent to the model during prediction execution.
     */
    @Override
    public PredictionBuilderService input(String key, Object value) {
       inputs.put(key, value);
       return this;
    }

    /**
     * {@inheritDoc}
     *
     * This implementation adds all entries from the specified map to the inputs map
     * that will be sent to the model during prediction execution. If the provided
     * map is null, no changes are made to the existing inputs.
     */
    @Override
    public PredictionBuilderService inputs(Map<String, Object> inputs) {
        if(inputs != null ){
            this.inputs.putAll(inputs);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * This implementation sets the webhook URL that will receive notifications
     * about prediction events.
     */
    @Override
    public PredictionBuilderService webhook(String url) {
        this.webhookUrl = url;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * This implementation sets the filter for webhook events, converting the
     * WebhookEvent enum values to their string representations.
     */
    @Override
    public PredictionBuilderService webhookEventFilter(List<WebhookEvent> events) {
        this.webhookEventFilter = events.stream()
                .map(WebhookEvent::getValue)
                .collect(Collectors.toList());
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * This implementation sends a POST request to the "/deployments/{owner}/{name}/predictions"
     * endpoint with the configured inputs and webhook settings. If wait is true,
     * it adds a Prefer header to make the request synchronous with the specified timeout.
     *
     * Note: There appears to be a bug in the implementation where the synchronous response
     * is not being returned when wait is true. This should be fixed to return the response
     * from the synchronous call.
     */
    @Override
    public Prediction executeFromDeployment(boolean wait) throws InterruptedException {
        return executeFromDeployment(wait, wait ? 300 : 0);
    }

    @Override
    public Prediction executeFromDeployment(boolean wait, int timeoutSeconds) throws InterruptedException {
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, String> headers = new HashMap<>();

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

        return restClient.post("deployments/" + modelOwner + "/" + modelName + "/predictions",
                requestBody, headers, Prediction.class);

    }

    /**
     * {@inheritDoc}
     *
     * This implementation calls the executeFromModel method with a default
     * timeout of 300 seconds if wait is true, or 0 seconds if wait is false.
     */
    @Override
    public Prediction executeFromModel(boolean wait) throws InterruptedException {
        return executeFromModel(wait, wait? 300 : 0);
    }

    /**
     * {@inheritDoc}
     *
     * This implementation sends a POST request to the "/models/{owner}/{name}/predictions"
     * endpoint with the configured inputs and webhook settings. If wait is true,
     * it adds a Prefer header to make the request synchronous with the specified timeout.
     *
     * Note: There appears to be a bug in the implementation where the synchronous response
     * is not being returned when wait is true. This should be fixed to return the response
     * from the synchronous call.
     */
    @Override
    public Prediction executeFromModel(boolean wait, int timeoutSeconds) throws InterruptedException {
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
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

        return restClient.post("models/" + modelOwner + "/" + modelName + "/predictions",
                requestBody, headers, Prediction.class);

    }

    /**
     * {@inheritDoc}
     *
     * This implementation calls the execute method with a default
     * timeout of 300 seconds if wait is true, or 0 seconds if wait is false.
     */
    @Override
    public Prediction execute(boolean wait) throws InterruptedException {
        return execute(wait, wait? 300 :0);
    }

    /**
     * {@inheritDoc}
     *
     * This implementation sends a POST request to the "/predictions" endpoint
     * with the configured inputs, model version, and webhook settings. If wait is true,
     * it adds a Prefer header to make the request synchronous with the specified timeout.
     *
     * Note: There appears to be a bug in the implementation where the synchronous response
     * is not being returned when wait is true. This should be fixed to return the response
     * from the synchronous call.
     */
    @Override
    public Prediction execute(boolean wait, int timeoutSeconds) throws InterruptedException {
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        requestBody.put("version", modelVersion);
        System.out.println(inputs);
        requestBody.put("input", inputs);
        System.out.println(inputs);

        if (webhookUrl != null && !webhookUrl.isEmpty()){
            requestBody.put("webhook", webhookUrl);
            if(webhookEventFilter != null && !webhookEventFilter.isEmpty()) {
                requestBody.put("webhook_events_filter", webhookEventFilter);
            }
        }


        if (wait) {
            headers.put("Prefer", "wait=" + timeoutSeconds);
        }

        return restClient.post("predictions", requestBody, headers, Prediction.class);
    }
}

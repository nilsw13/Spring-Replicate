package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.ResponseType.webhook.WebhookEvent;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.ResponseType.Prediction.Prediction;
import com.nilsw13.spring_replicate.service.PredictionBuilderService;
import org.springframework.stereotype.Service;

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



    @Override
    public PredictionBuilderService input(String key, Object value) {
       inputs.put(key, value);
       return this;
    }

    @Override
    public PredictionBuilderService inputs(Map<String, Object> inputs) {
        if(inputs != null ){
            this.inputs.putAll(inputs);
        }
        return this;
    }

    @Override
    public PredictionBuilderService webhook(String url) {
        this.webhookUrl = url;
        return this;
    }

    @Override
    public PredictionBuilderService webhookEventFilter(List<WebhookEvent> events) {
        this.webhookEventFilter = events.stream()
                .map(WebhookEvent::getValue)
                .collect(Collectors.toList());
        return this;
    }

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


        // add Prefer header if wait is true
        if (wait) {
            headers.put("Prefer", "wait=" + timeoutSeconds);
            Prediction response = restClient.post("deployments/" + modelOwner + "/" + modelName + "/" + "predictions", requestBody, headers,  Prediction.class);
        }


        return restClient.post("deployments/" + modelOwner + "/" + modelName + "/" + "predictions"  , requestBody, Prediction.class);

    }

    @Override
    public Prediction executeFromModel(boolean wait) throws InterruptedException {
        return executeFromModel(wait, wait? 300 : 0);
    }

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


        // add Prefer header if wait is true
        if (wait) {
            headers.put("Prefer", "wait=" + timeoutSeconds);
            Prediction response = restClient.post("models/" + modelOwner + "/" + modelName + "/" + "predictions", requestBody, headers,  Prediction.class);
        }


        return restClient.post("models/" + modelOwner + "/" + modelName + "/" + "predictions"  , requestBody, Prediction.class);

    }


    @Override
    public Prediction execute(boolean wait) throws InterruptedException {
        return execute(wait, wait? 300 :0);
    }


    @Override
    public Prediction execute(boolean wait, int timeoutSeconds) throws InterruptedException {
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        requestBody.put("version", modelVersion);
        requestBody.put("input", inputs);

        if (webhookUrl != null && !webhookUrl.isEmpty()){
            requestBody.put("webhook", webhookUrl);
            if(webhookEventFilter != null && !webhookEventFilter.isEmpty()) {
                requestBody.put("webhook_events_filter", webhookEventFilter);
            }
        }


        // add Prefer header if wait is true
        if (wait) {
           headers.put("Prefer", "wait=" + timeoutSeconds);
            Prediction response = restClient.post("predictions", requestBody, headers,  Prediction.class);
        }


        return restClient.post("predictions" , requestBody, Prediction.class);
    }
}

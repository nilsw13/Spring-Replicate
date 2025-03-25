package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.ResponseType.Training.Training;
import com.nilsw13.spring_replicate.ResponseType.webhook.WebhookEvent;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.service.TrainingBuilderService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrainingBuilderServiceImpl implements TrainingBuilderService {

    private final ReplicateRestClient replicateRestClient;
    private final String modelOwner;
    private final String modelName;
    private final String modelversion;

    private final Map<String, Object> inputs = new HashMap<>();
    private String webhookUrl;
    private String destination;
    private List<String> webhookEventFilter;

    public TrainingBuilderServiceImpl(ReplicateRestClient replicateRestClient, String modelOwner, String modelName, String modelversion) {
        this.replicateRestClient = replicateRestClient;
        this.modelOwner = modelOwner;
        this.modelName = modelName;
        this.modelversion = modelversion;
    }


    @Override
    public TrainingBuilderService destination(String modelOwnerName, String modelName) {
        this.destination = modelOwnerName + "/" + modelName;
        return this;
    }

    @Override
    public TrainingBuilderService input(String key, Object value) {
        inputs.put(key, value);
        return this;
    }

    @Override
    public TrainingBuilderService inputs(Map<String, Object> inputs) {
        if(inputs != null ){
            this.inputs.putAll(inputs);
        }
        return this;
    }

    @Override
    public TrainingBuilderService webhook(String url) {
        this.webhookUrl = url;
        return this;
    }

    @Override
    public TrainingBuilderService webhookEventFilter(List<WebhookEvent> events) {
        this.webhookEventFilter = events.stream()
                .map(WebhookEvent::getValue)
                .collect(Collectors.toList());
        return this;
    }

    @Override
    public Training execute(boolean wait) throws InterruptedException {
        return execute(wait, wait ? 300 : 0);
    }

    @Override
    public Training execute(boolean wait, int timeoutSeconds) throws InterruptedException {
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        requestBody.put("destination", destination);
        requestBody.put("input", inputs);

        if (webhookUrl != null && !webhookUrl.isEmpty()){
            requestBody.put("webhook", webhookUrl);
            if(webhookEventFilter != null && !webhookEventFilter.isEmpty()) {
                requestBody.put("webhook_events_filter", webhookEventFilter);
            }
        }

        if (wait) {
            headers.put("Prefer", "wait=" + timeoutSeconds);
            Training response = replicateRestClient.post("models/" + modelOwner + "/" + modelName + "/" + "versions/" + modelversion + "/" + "trainings" , requestBody, headers,  Training.class);
        }


        return replicateRestClient.post("models/" + modelOwner + "/" + modelName + "/" + "versions/" + modelversion + "/" + "trainings" , requestBody, Training.class);
    }
}

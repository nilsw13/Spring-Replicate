package com.nilsw13.spring_replicate.service;

import com.nilsw13.spring_replicate.ResponseType.Training.Training;
import com.nilsw13.spring_replicate.ResponseType.webhook.WebhookEvent;

import java.util.List;
import java.util.Map;

public interface TrainingBuilderService {

    TrainingBuilderService destination(String modelOwnerName, String modelName);
    TrainingBuilderService input (String key, Object value);
    TrainingBuilderService inputs(Map<String, Object> inputs);
    TrainingBuilderService webhook(String url);
    TrainingBuilderService webhookEventFilter(List<WebhookEvent> events);

    // launch training
    Training execute(boolean wait) throws InterruptedException;
    Training execute(boolean wait, int timeoutSeconds) throws InterruptedException;
}

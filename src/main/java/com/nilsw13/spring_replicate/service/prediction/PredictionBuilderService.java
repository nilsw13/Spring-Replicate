package com.nilsw13.spring_replicate.service.prediction;

import com.nilsw13.spring_replicate.ResponseType.Prediction.Prediction;
import com.nilsw13.spring_replicate.ResponseType.webhook.WebhookEvent;

import java.util.List;
import java.util.Map;

public interface PredictionBuilderService {

    // add parameter
    PredictionBuilderService input(String key, Object value);

    PredictionBuilderService inputs(Map<String, Object> inputs);
    // config webhook
    PredictionBuilderService webhook(String url);

    PredictionBuilderService webhookEventFilter(List<WebhookEvent> events);

    // run prediction and return result
    Prediction execute(boolean wait) throws InterruptedException;
    Prediction execute(boolean wait, int timeoutSeconds) throws InterruptedException;
}

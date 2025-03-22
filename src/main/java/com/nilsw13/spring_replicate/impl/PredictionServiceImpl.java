package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.ResponseType.Prediction.PredictionResponse;
import com.nilsw13.spring_replicate.ResponseType.Prediction.PredictionsListResponse;
import com.nilsw13.spring_replicate.service.prediction.PredictionBuilderService;
import com.nilsw13.spring_replicate.service.prediction.PredictionService;
import org.springframework.stereotype.Service;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final ReplicateRestClient restClient;

    public PredictionServiceImpl(ReplicateRestClient restClient) {
        this.restClient = restClient;

    }


    @Override
    public PredictionsListResponse list() {
        return restClient.get("predictions", PredictionsListResponse.class);
    }

    @Override
    public PredictionResponse get(String id) {
        return restClient.get("predictions/" + id, PredictionResponse.class);
    }

    @Override
    public PredictionBuilderService create(String modelVersion) {
        return new PredictionBuilderServiceImpl(restClient, modelVersion);
    }

    @Override
    public PredictionResponse cancel(String id) {
        return restClient.post("predictions/" + id + "/cancel", PredictionResponse.class);
    }


}

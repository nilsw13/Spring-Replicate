package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.ResponseType.Prediction.PredictionsList;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.ResponseType.Prediction.Prediction;
import com.nilsw13.spring_replicate.service.PredictionBuilderService;
import com.nilsw13.spring_replicate.service.PredictionService;
import org.springframework.stereotype.Service;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final ReplicateRestClient restClient;

    public PredictionServiceImpl(ReplicateRestClient restClient) {
        this.restClient = restClient;

    }

    private String modelOwner;
    private String modelName;


    @Override
    public PredictionsList list() {
        return restClient.get("predictions", PredictionsList.class);
    }

    @Override
    public Prediction get(String id) {
        return restClient.get("predictions/" + id, Prediction.class);
    }

    @Override
    public PredictionBuilderService create(String modelVersion) {
        return new PredictionBuilderServiceImpl(restClient, modelVersion, modelOwner, modelName);
    }

    @Override
    public Prediction cancel(String id) {
        return restClient.post("predictions/" + id + "/cancel", Prediction.class);
    }


}

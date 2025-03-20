package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.model.Prediction.Prediction;
import com.nilsw13.spring_replicate.model.Prediction.PredictionResponse;
import com.nilsw13.spring_replicate.model.Prediction.PredictionsListResponse;
import com.nilsw13.spring_replicate.service.prediction.PredictionBuilderService;
import com.nilsw13.spring_replicate.service.prediction.PredictionService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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
        return null;
    }

    @Override
    public Prediction cancel(String id) {
        return null;
    }


    // Convert internal model into public model
    private Prediction mapToPrediction(PredictionResponse response) {
        return new Prediction(
                response.getId(),
                response.getStatus(),
                response.getModel(),
                response.getVersion(),
                response.getOutput(),
                response.getInput(),
                response.getCreatedAt(),
                response.getCompletedAt()
        );
    }
}

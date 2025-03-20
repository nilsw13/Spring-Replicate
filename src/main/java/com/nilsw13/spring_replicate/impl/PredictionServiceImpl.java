package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.model.PredictionResponse;
import com.nilsw13.spring_replicate.model.PredictionsListResponse;
import com.nilsw13.spring_replicate.service.actions.PredictionService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final ReplicateRestClient restClient;

    public PredictionServiceImpl(ReplicateRestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Mono<PredictionsListResponse> getPredictionsList() {
        return restClient.get("predictions", PredictionsListResponse.class);
    }

    @Override
    public Mono<PredictionResponse> getPrediction(String id) {
        return restClient.get("predictions/"+id , PredictionResponse.class);
    }
}

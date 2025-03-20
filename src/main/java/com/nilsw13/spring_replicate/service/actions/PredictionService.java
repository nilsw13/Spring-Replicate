package com.nilsw13.spring_replicate.service.actions;

import com.nilsw13.spring_replicate.model.PredictionResponse;
import com.nilsw13.spring_replicate.model.PredictionsListResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PredictionService {

    Mono<PredictionsListResponse> getPredictionsList() ;
    Mono<PredictionResponse> getPrediction(String id);
}

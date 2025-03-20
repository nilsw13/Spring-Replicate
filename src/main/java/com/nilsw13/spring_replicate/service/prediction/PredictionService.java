package com.nilsw13.spring_replicate.service.prediction;

import com.nilsw13.spring_replicate.model.Prediction.Prediction;
import com.nilsw13.spring_replicate.model.Prediction.PredictionResponse;
import com.nilsw13.spring_replicate.model.Prediction.PredictionsListResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PredictionService {

//    Mono<PredictionsListResponse> getPredictionsList() ;
//    Mono<PredictionResponse> getPrediction(String id);

    // List all predictions
    PredictionsListResponse list();

    // get prediction by it's id
    PredictionResponse get(String id);

    // Create a prediction
    PredictionBuilderService create(String modelVersion);

    // Cancel a prediction

    Prediction cancel(String id);
}

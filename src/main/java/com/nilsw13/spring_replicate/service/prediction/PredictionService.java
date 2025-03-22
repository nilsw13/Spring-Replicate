package com.nilsw13.spring_replicate.service.prediction;

import com.nilsw13.spring_replicate.ResponseType.Prediction.PredictionResponse;
import com.nilsw13.spring_replicate.ResponseType.Prediction.PredictionsListResponse;

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

    PredictionResponse cancel(String id);
}

package com.nilsw13.spring_replicate.service;

import com.nilsw13.spring_replicate.ResponseType.Prediction.Prediction;
import com.nilsw13.spring_replicate.ResponseType.Prediction.PredictionsList;

public interface PredictionService {

//    Mono<PredictionsList> getPredictionsList() ;
//    Mono<Prediction> getPrediction(String id);

    // List all predictions
    PredictionsList list();

    // get prediction by it's id
    Prediction get(String id);

    // Create a prediction
    PredictionBuilderService create(String modelVersion);

    // Cancel a prediction

    Prediction cancel(String id);
}

package com.nilsw13.spring_replicate.service;

import com.nilsw13.spring_replicate.ResponseType.Training.Training;
import com.nilsw13.spring_replicate.ResponseType.Training.TrainingList;

public interface TrainingService {

        TrainingBuilderService create(String modelOwner, String modelName, String modelVersion);
        Training cancel(String trainingId);
        Training get(String trainingId);
        TrainingList list();
}

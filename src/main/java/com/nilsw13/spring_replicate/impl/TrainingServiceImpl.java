package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.ResponseType.Training.Training;
import com.nilsw13.spring_replicate.ResponseType.Training.TrainingList;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.service.Training.TrainingBuilderService;
import com.nilsw13.spring_replicate.service.Training.TrainingService;
import org.springframework.stereotype.Service;

@Service
public class TrainingServiceImpl implements TrainingService {
    private final ReplicateRestClient replicateRestClient;

    public TrainingServiceImpl(ReplicateRestClient replicateRestClient) {
        this.replicateRestClient = replicateRestClient;
    }

    @Override
    public TrainingBuilderService create(String modelOwner, String modelName, String modelVersion) {
        return new TrainingBuilderServiceImpl(replicateRestClient, modelOwner, modelName, modelVersion);
    }

    @Override
    public Training cancel(String trainingId) {
        return replicateRestClient.post("trainings/" + trainingId + "/" + "cancel", Training.class);
    }

    @Override
    public Training get(String trainingId) {
        return replicateRestClient.get("trainings/ " + trainingId, Training.class);
    }

    @Override
    public TrainingList list() {
        return replicateRestClient.get("trainings", TrainingList.class);
    }
}

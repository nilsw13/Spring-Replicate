package com.nilsw13.spring_replicate.service.Model;

import com.nilsw13.spring_replicate.ResponseType.Model.Model;
import com.nilsw13.spring_replicate.ResponseType.Model.ModelList;
import com.nilsw13.spring_replicate.ResponseType.Model.ModelVersionList;
import com.nilsw13.spring_replicate.ResponseType.Model.Version;
import com.nilsw13.spring_replicate.service.prediction.PredictionBuilderService;

public interface ModelService {

    Model create(Model request);
    PredictionBuilderService createModelPrediction(String modelOwner, String modelName);
    Model get(String modelOwner, String modelName);
    Model delete(String modelOwner, String modelName);
    ModelList list();
    ModelVersionList listModelVersions(String modelOwner, String modelName);
    Version getModelVersion(String modelOwner, String modelName, String versionId);
    Version deleteModelVersion(String modelOwner, String modelName, String versionId);
    String getModelReadme(String modelOwner, String modelName);

}

package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.ResponseType.Model.Model;
import com.nilsw13.spring_replicate.ResponseType.Model.ModelList;
import com.nilsw13.spring_replicate.ResponseType.Model.ModelVersionList;
import com.nilsw13.spring_replicate.ResponseType.Model.Version;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.service.Model.ModelService;
import com.nilsw13.spring_replicate.service.prediction.PredictionBuilderService;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService {

    private final ReplicateRestClient replicateRestClient;
    private String modelVersion;

    public ModelServiceImpl(ReplicateRestClient replicateRestClient) {
        this.replicateRestClient = replicateRestClient;
    }


    @Override
    public Model create(Model request) {
        return replicateRestClient.post("models", request, Model.class);
    }

    @Override
    public PredictionBuilderService createModelPrediction(String modelOwner, String modelName) {
        return new PredictionBuilderServiceImpl(replicateRestClient, modelVersion, modelOwner, modelName);
    }


    @Override
    public Model get(String modelOwner, String modelName) {
        return replicateRestClient.get("models/" + modelOwner + "/" + modelName , Model.class);
    }

    @Override
    public Model delete(String modelOwner, String modelName) {
        return replicateRestClient.delete("models/" + modelOwner + "/" + modelName, Model.class );
    }

    @Override
    public ModelList list() {
        return replicateRestClient.get("models", ModelList.class);
    }



    @Override
    public ModelVersionList listModelVersions(String modelOwner, String modelName) {
        return replicateRestClient.get("models/" + modelOwner + "/" + modelName + "/" +"versions", ModelVersionList.class);
    }

    @Override
    public Version getModelVersion(String modelOwner, String modelName, String versionId) {
        return replicateRestClient.get("models/" + modelOwner + "/" + modelName + "/"  + "versions/"  + versionId, Version.class);
    }

    @Override
    public Version deleteModelVersion(String modelOwner, String modelName, String versionId) {
        return replicateRestClient.delete("models/" + modelOwner + "/" + modelName + "/"  + "versions/" + versionId, Version.class);
    }

    @Override
    public String getModelReadme(String modelOwner, String modelName) {
        return replicateRestClient.get("models/" + modelOwner + "/" + modelName + "/" + "readme", String.class);
    }
}

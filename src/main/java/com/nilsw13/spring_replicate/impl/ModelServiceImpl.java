package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.ResponseType.Model.ModelListResponse;
import com.nilsw13.spring_replicate.ResponseType.Model.ModelResponse;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.service.Model.ModelService;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService {

    private final ReplicateRestClient replicateRestClient;

    public ModelServiceImpl(ReplicateRestClient replicateRestClient) {
        this.replicateRestClient = replicateRestClient;
    }


    @Override
    public ModelResponse get(String modelOwner, String modelName) {
        return replicateRestClient.get("models/" + modelOwner + "/" + modelName , ModelResponse.class);
    }

    @Override
    public ModelListResponse list() {
        return replicateRestClient.get("models", ModelListResponse.class);
    }
}

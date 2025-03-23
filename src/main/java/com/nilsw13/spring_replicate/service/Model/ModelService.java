package com.nilsw13.spring_replicate.service.Model;

import com.nilsw13.spring_replicate.ResponseType.Model.ModelListResponse;
import com.nilsw13.spring_replicate.ResponseType.Model.ModelResponse;

public interface ModelService {

    ModelResponse get(String modelOwner, String modelName);
    ModelListResponse list();

}

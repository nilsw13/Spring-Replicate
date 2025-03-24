package com.nilsw13.spring_replicate.ResponseType.Model;

import java.util.List;

public class ModelListResponse {

    private String previous;
    private String next;
    private List<ModelResponse> results;

    public ModelListResponse() {
    }

    public String getPrevious() {
        return previous;
    }

    public String getNext() {
        return next;
    }

    public List<ModelResponse> getResults() {
        return results;
    }
}

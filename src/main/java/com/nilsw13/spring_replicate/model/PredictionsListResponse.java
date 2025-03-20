package com.nilsw13.spring_replicate.model;

import java.util.List;

public class PredictionsListResponse {
    private String next;
    private String previous;
    private List<PredictionResponse> results;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<PredictionResponse> getResults() {
        return results;
    }

    public void setResults(List<PredictionResponse> results) {
        this.results = results;
    }
}

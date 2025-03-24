package com.nilsw13.spring_replicate.ResponseType.Prediction;

import java.util.List;

public class PredictionsList {
    private String next;
    private String previous;
    private List<Prediction> results;

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

    public List<Prediction> getResults() {
        return results;
    }

    public void setResults(List<Prediction> results) {
        this.results = results;
    }
}

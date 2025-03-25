package com.nilsw13.spring_replicate.ResponseType.Training;

import java.util.List;

public class TrainingList {

    private String previous;
    private String next;
    private List<Training> results;

    public String getPrevious() {
        return previous;
    }

    public String getNext() {
        return next;
    }

    public List<Training> getResults() {
        return results;
    }
}

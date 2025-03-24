package com.nilsw13.spring_replicate.ResponseType.Model;

import java.util.List;

public class ModelList {

    private String previous;
    private String next;
    private List<Model> results;

    public ModelList() {
    }

    public String getPrevious() {
        return previous;
    }

    public String getNext() {
        return next;
    }

    public List<Model> getResults() {
        return results;
    }
}

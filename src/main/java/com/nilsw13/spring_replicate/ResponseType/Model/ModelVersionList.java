package com.nilsw13.spring_replicate.ResponseType.Model;

import java.util.List;

public class ModelVersionList {
    private String previous;
    private String next;
    private List<Version> results;

    public String getPrevious() {
        return previous;
    }

    public String getNext() {
        return next;
    }

    public List<Version> getResults() {
        return results;
    }
}

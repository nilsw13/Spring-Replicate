package com.nilsw13.spring_replicate.ResponseType.Deployment;

import java.util.List;

public class DeploymentList {

    private String previous;
    private String next;
    private List<Deployment> results;

    public String getPrevious() {
        return previous;
    }

    public String getNext() {
        return next;
    }

    public List<Deployment> getResults() {
        return results;
    }
}

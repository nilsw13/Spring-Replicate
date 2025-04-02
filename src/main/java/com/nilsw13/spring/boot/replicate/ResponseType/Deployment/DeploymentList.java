package com.nilsw13.spring.boot.replicate.ResponseType.Deployment;

import java.util.List;

/**
 * Represents a paginated list of deployments from the Replicate API.
 *
 * This class encapsulates the response structure returned when listing deployments,
 * including the collection of deployment objects and pagination links for navigating
 * through multiple pages of results.
 *
 * @author Nilsw13
 * @since 1.0.0
 */
public class DeploymentList {

    private String previous;
    private String next;
    private List<Deployment> results;


    public DeploymentList() {
    }

    public String getPrevious() {
        return previous;
    }

    public String getNext() {
        return next;
    }

    public List<Deployment> getResults() {
        return results;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public void setResults(List<Deployment> results) {
        this.results = results;
    }
}

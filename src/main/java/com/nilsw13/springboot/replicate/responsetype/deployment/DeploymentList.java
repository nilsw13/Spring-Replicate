package com.nilsw13.springboot.replicate.responsetype.deployment;

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
        /**
         * Default constructor for DeploymentList class.
         *
         * This empty constructor exists for the following reasons:
         * 1. Required by JSON/Jackson deserialization process when mapping API responses
         * 2. Enables library users to instantiate response objects when needed
         * 3. Supports serialization/deserialization in various client implementations
         *
         * Although empty, this constructor is essential for the proper functioning
         * of the API client library and should not be removed.
         */
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

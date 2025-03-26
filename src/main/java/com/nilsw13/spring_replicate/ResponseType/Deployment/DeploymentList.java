package com.nilsw13.spring_replicate.ResponseType.Deployment;

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

package com.nilsw13.spring_boot.replicate.ResponseType.Model;

import java.util.List;


/**
 * Represents a paginated list of model versions returned by the Replicate API.
 *
 * This class corresponds to the structure of the JSON response when listing versions
 * of a specific model. It includes pagination information (previous and next page URLs)
 * as well as the actual list of versions in the current page.
 *
 * The pagination mechanism allows efficient retrieval of large sets of versions
 * by fetching them in smaller chunks or pages. The next and previous URLs can
 * be used to navigate through these pages.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see Version
 * @see Model
 */
public class ModelVersionList {
    private String previous;
    private String next;
    private List<Version> results;

    /**
     * Default constructor for ModelVersionList class.
     *
     * This empty constructor exists for the following reasons:
     * 1. Required by JSON/Jackson deserialization process when mapping API responses
     * 2. Enables library users to instantiate response objects when needed
     * 3. Supports serialization/deserialization in various client implementations
     *
     * Although empty, this constructor is essential for the proper functioning
     * of the API client library and should not be removed.
     */
    public ModelVersionList() {
    }

    public String getPrevious() {
        return previous;
    }

    public String getNext() {
        return next;
    }

    public List<Version> getResults() {
        return results;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public void setResults(List<Version> results) {
        this.results = results;
    }
}

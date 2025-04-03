package com.nilsw13.springboot.replicate.responsetype.prediction;

import java.util.List;

/**
 * Represents a paginated list of predictions returned by the Replicate API.
 *
 * This class corresponds to the structure of the JSON response when listing predictions.
 * It includes pagination information (previous and next page URLs) as well as
 * the actual list of predictions in the current page.
 *
 * The pagination mechanism allows efficient retrieval of large sets of predictions
 * by fetching them in smaller chunks or pages. The next and previous URLs can
 * be used to navigate through these pages.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see Prediction
 */
public class PredictionsList {
    private String next;
    private String previous;
    private List<Prediction> results;


    public PredictionsList() {
        /**
         * Default constructor for PredictionList class.
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

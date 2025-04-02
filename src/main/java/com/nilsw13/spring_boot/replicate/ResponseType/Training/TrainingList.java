package com.nilsw13.spring_boot.replicate.ResponseType.Training;

import java.util.List;

/**
 * Represents a paginated list of training jobs returned by the Replicate API.
 *
 * This class corresponds to the structure of the JSON response when listing training jobs.
 * It includes pagination information (previous and next page URLs) as well as
 * the actual list of training jobs in the current page.
 *
 * The pagination mechanism allows efficient retrieval of large sets of training jobs
 * by fetching them in smaller chunks or pages. The next and previous URLs can
 * be used to navigate through these pages.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see Training
 */
public class TrainingList {

    private String previous;
    private String next;
    private List<Training> results;


    public TrainingList() {
        /**
         * Default constructor for TrainingList class.
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

    public List<Training> getResults() {
        return results;
    }
}

package com.nilsw13.spring_replicate.ResponseType.Model;

import java.util.List;

/**
 * Represents a paginated list of models returned by the Replicate API.
 *
 * This class corresponds to the structure of the JSON response when listing models.
 * It includes pagination information (previous and next page URLs) as well as
 * the actual list of models in the current page.
 *
 * The pagination mechanism allows efficient retrieval of large sets of models
 * by fetching them in smaller chunks or pages. The next and previous URLs can
 * be used to navigate through these pages.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see Model
 */
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

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public void setResults(List<Model> results) {
        this.results = results;
    }
}

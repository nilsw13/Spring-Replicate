package com.nilsw13.spring_replicate.ResponseType.Collection;


import java.util.List;

/**
 * Represents a paginated list of collections returned by the Replicate API.
 *
 * This class wraps the collection results with pagination metadata, allowing
 * for efficient navigation through large sets of collections. When the API
 * returns more collections than can be efficiently processed in a single
 * request, it divides the results into pages and provides URLs to navigate
 * to the next or previous page.
 *
 * The pagination links (next and previous) are fully-qualified URLs that can
 * be directly used to retrieve the corresponding page of results. When there
 * are no more pages in a particular direction, the corresponding link will be null.
 *
 * @author Nilsw13
 * @since 1.0.0
 */
public class CollectionModelList {

    private String next;
    private String previous;
    List<CollectionModel> results;

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<CollectionModel> getResults() {
        return results;
    }
}

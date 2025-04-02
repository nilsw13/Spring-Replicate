package com.nilsw13.spring.boot.replicate.service;

import com.nilsw13.spring.boot.replicate.ResponseType.Collection.CollectionModel;
import com.nilsw13.spring.boot.replicate.ResponseType.Collection.CollectionModelList;
import com.nilsw13.spring.boot.replicate.exception.ReplicateApiException;


/**
 * Service interface for accessing and retrieving model collections from the Replicate API.
 *
 * This service acts as the  entry point for clients to interact with Replicate's
 * curated collections. It abstracts away the underlying HTTP communication details and
 * provides a clean, domain-focused API for collection operations.
 *
 * The service supports two main operations:
 * - Retrieving a paginated list of all available collections
 * - Fetching a specific collection by its unique identifier
 *
 * Usage example:
 *
 * // List all available collections
 * CollectionModelList collections = replicate.collections().list();
 *
 * // Get a specific collection by its slug
 * CollectionModel imageGen = replicate.collections().get("image-generation");
 *
 * @author Nilsw13
 * @since 1.0.0
 */
public interface CollectionService {

    /**
     * Retrieves a paginated list of all available collections.
     *
     * This method fetches the first page of collections from the Replicate API.
     * No filtering or sorting parameters are supported in the current implementation.
     *
     * @return A CollectionModelList containing the first page of collection results
     * @throws ReplicateApiException If the API request fails
     * @see CollectionModelList
     */
    CollectionModelList list();

    /**
     * Retrieves a single collection by its unique slug identifier.
     *
     * @param collection_slug The slug that uniquely identifies the desired collection
     * @return The requested CollectionModel if found
     * @throws ReplicateApiException If the collection does not exist or the API request fails
     */
    CollectionModel get(String collection_slug);


}

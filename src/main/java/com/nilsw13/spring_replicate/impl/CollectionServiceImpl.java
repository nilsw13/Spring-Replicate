package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.ResponseType.Collection.CollectionModel;
import com.nilsw13.spring_replicate.ResponseType.Collection.CollectionModelList;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.config.ReplicateAutoConfig;
import com.nilsw13.spring_replicate.config.ReplicateProperties;
import com.nilsw13.spring_replicate.exception.ReplicateApiException;
import com.nilsw13.spring_replicate.service.CollectionService;
import org.springframework.stereotype.Service;
/**
 * Standard implementation of the CollectionService interface.
 *
 * This class provides the concrete implementation of collection-related operations
 * by communicating with the Replicate API through the ReplicateRestClient. It handles
 * the details of constructing API endpoint paths and deserializing responses into
 * the appropriate domain objects.
 *
 * This implementation is automatically discovered and instantiated by Spring's
 * component scanning due to its @Service annotation. Additionally, it is explicitly
 * registered as a bean in the ReplicateAutoConfig class, making it available for
 * injection throughout the application when the Spring Boot starter is included.
 *
 * The auto-configuration ensures this implementation is used unless another bean
 * of type CollectionService is explicitly defined by the user. The service relies
 * on configuration properties defined in ReplicateProperties, particularly the
 * "replicate.api-key" which is required for authentication with the Replicate API.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see CollectionService
 * @see ReplicateRestClient
 * @see ReplicateAutoConfig
 * @see ReplicateProperties
 */
@Service
public class CollectionServiceImpl implements CollectionService {

    private final ReplicateRestClient replicateRestClient;

    /**
     * Constructs a new CollectionServiceImpl with the required REST client.
     *
     * The REST client is automatically injected by Spring's dependency injection
     * system. This client is configured with the API key from the "replicate.api-key"
     * property and optionally uses a custom API URL if "replicate.api-url" is specified
     * in the application configuration. These properties are managed by the
     * ReplicateProperties class and automatically bound by Spring Boot.
     *
     * @param replicateRestClient The client used to communicate with the Replicate API
     * @see ReplicateProperties
     */
    public CollectionServiceImpl(ReplicateRestClient replicateRestClient) {
        this.replicateRestClient = replicateRestClient;
    }


    /**
     * {@inheritDoc}
     *
     * This implementation makes a GET request to the "/collections" endpoint
     * and deserializes the response into a CollectionModelList.
     */
    @Override
    public CollectionModelList list() {
        return replicateRestClient.get("collections", CollectionModelList.class);
    }

    /**
     * {@inheritDoc}
     *
     * This implementation makes a GET request to the "/collections/{collection_slug}"
     * endpoint and deserializes the response into a CollectionModel.
     * @param collection_slug The unique identifier of the collection to retrieve
     */
    @Override
    public CollectionModel get(String collection_slug) {
        return replicateRestClient.get("collections/" + collection_slug, CollectionModel.class);
    }
}

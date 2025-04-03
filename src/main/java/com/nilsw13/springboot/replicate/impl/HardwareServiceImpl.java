package com.nilsw13.springboot.replicate.impl;

import com.nilsw13.springboot.replicate.responsetype.hardware.Hardware;
import com.nilsw13.springboot.replicate.api.ReplicateRestClient;
import com.nilsw13.springboot.replicate.config.ReplicateProperties;
import com.nilsw13.springboot.replicate.service.HardwareService;


/**
 * Implementation of the HardwareService interface for accessing hardware information
 * from the Replicate API.
 *
 * This implementation delegates API calls to the ReplicateRestClient, which handles
 * the HTTP communication with the Replicate API endpoints. The service converts the
 * raw API responses into strongly-typed Hardware objects that can be easily used
 * throughout the application.
 *
 * The implementation follows a simple delegation pattern where each service method
 * translates to a specific REST API call. This approach maintains a clear separation
 * between the service interface contract and the underlying API communication details.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see HardwareService
 * @see Hardware
 * @see ReplicateRestClient
 */
public class HardwareServiceImpl implements HardwareService {

    private final ReplicateRestClient replicateRestClient;

    /**
     * Constructs a new HardwareServiceImpl with the required REST client.
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
    public HardwareServiceImpl(ReplicateRestClient replicateRestClient) {
        this.replicateRestClient = replicateRestClient;
    }

    /**
     * {@inheritDoc}
     *
     * This implementation sends a GET request to the "/hardware" endpoint
     * and deserializes the response into an array of Hardware objects.
     *
     * @see Hardware
     */
    @Override
    public Hardware[] list() {
        return replicateRestClient.get("hardware", Hardware[].class);
    }
}

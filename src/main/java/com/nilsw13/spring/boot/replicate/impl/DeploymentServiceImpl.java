package com.nilsw13.spring.boot.replicate.impl;


import com.nilsw13.spring.boot.replicate.ResponseType.Deployment.Deployment;
import com.nilsw13.spring.boot.replicate.ResponseType.Deployment.DeploymentConfiguration;
import com.nilsw13.spring.boot.replicate.ResponseType.Deployment.DeploymentList;
import com.nilsw13.spring.boot.replicate.api.ReplicateRestClient;
import com.nilsw13.spring.boot.replicate.config.ReplicateProperties;
import com.nilsw13.spring.boot.replicate.service.DeploymentService;
import com.nilsw13.spring.boot.replicate.service.PredictionBuilderService;
import org.springframework.stereotype.Service;


/**
 * Implementation of the DeploymentService interface for managing Replicate deployments.
 *
 * This class provides the concrete implementation of deployment-related operations
 * by communicating with the Replicate API through the ReplicateRestClient. It handles
 * the construction of API endpoint paths for deployment operations and the transformation
 * of API responses into domain model objects.
 *
 * This implementation is automatically registered as a Spring bean through its @Service
 * annotation and is used by the auto-configuration system unless overridden by a custom
 * implementation provided by the user.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see DeploymentService
 * @see ReplicateRestClient
 */
@Service
public class DeploymentServiceImpl implements DeploymentService {

    private final ReplicateRestClient replicateRestClient;
    private String modelVersion;


    /**
     * Constructs a new DeploymentServiceImpl with the required REST client.
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
    public DeploymentServiceImpl(ReplicateRestClient replicateRestClient) {
        this.replicateRestClient = replicateRestClient;
    }


    /**
     * {@inheritDoc}
     *
     * This implementation sends a POST request to the "/deployments" endpoint
     * with the provided configuration object serialized as JSON in the request body.
     *
     * @param configuration The deployment configuration to use
     */
    @Override
    public Deployment create(DeploymentConfiguration configuration) {
        return replicateRestClient.post("deployments", configuration, Deployment.class);
    }

    /**
     * {@inheritDoc}
     *
     * This implementation creates a new PredictionBuilderServiceImpl instance configured
     * to run predictions against the specified deployment. It passes null for the modelVersion
     * parameter since version information is not required for deployment-based predictions.
     *
     * @param deploymentOwner The username or organization that owns the deployment
     * @param deploymentName The name of the deployment
     */
    @Override
    public PredictionBuilderService createDeploymentPrediction(String deploymentOwner, String deploymentName) {
        return new PredictionBuilderServiceImpl(replicateRestClient, modelVersion, deploymentOwner, deploymentName);
    }


    /**
     * {@inheritDoc}
     *
     * This implementation sends a GET request to the "/deployments/{owner}/{name}" endpoint
     * and deserializes the response into a Deployment object.
     *
     * @param deploymentOwner The username or organization that owns the deployment
     * @param deploymentName The name of the deployment
     * @see Deployment
     */
    @Override
    public Deployment get(String deploymentOwner, String deploymentName) {
        return replicateRestClient.get("deployments/" + deploymentOwner + "/" + deploymentName, Deployment.class);
    }

    /**
     * {@inheritDoc}
     *
     * This implementation sends a GET request to the "/deployments" endpoint
     * and deserializes the response into a DeploymentList object.
     * @see DeploymentList
     */
    @Override
    public DeploymentList list() {
        return replicateRestClient.get("deployments", DeploymentList.class );
    }

    /**
     * {@inheritDoc}
     *
     * This implementation sends a PATCH request to the "/deployments/{owner}/{name}" endpoint
     * with the provided changes object serialized as JSON in the request body.
     *
     * The PATCH method is used to apply partial updates to the deployment configuration,
     * allowing users to modify only specific properties without affecting others.
     *
     * @param owner The username or organization that owns the deployment
     * @param name The name of the deployment
     * @param changes The configuration changes to apply
     * @see DeploymentConfiguration
     */
    @Override
    public DeploymentConfiguration update(String owner, String name, DeploymentConfiguration changes) {
        return replicateRestClient.patch("deployments/" + owner + "/" + name, changes, DeploymentConfiguration.class);
    }

    /**
     * {@inheritDoc}
     *
     * This implementation sends a DELETE request to the "/deployments/{owner}/{name}" endpoint.
     *
     * Remember that according to Replicate API restrictions, you can only delete deployments
     * that have been offline and unused for at least 15 minutes. Attempting to delete an active
     * deployment or one that has been recently used will result in an error.
     *
     * @param owner The username or organization that owns the deployment
     * @param name The name of the deployment
     */
    @Override
    public Deployment delete(String owner, String name) {
        return replicateRestClient.delete("deployments/" + owner + "/" + name, Deployment.class );
    }
}

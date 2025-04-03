package com.nilsw13.springboot.replicate.service;

import com.nilsw13.springboot.replicate.responsetype.deployment.Deployment;
import com.nilsw13.springboot.replicate.responsetype.deployment.DeploymentConfiguration;
import com.nilsw13.springboot.replicate.responsetype.deployment.DeploymentList;
import com.nilsw13.springboot.replicate.exception.ReplicateApiException;

/**
 * Service interface for managing model deployments in the Replicate API.
 *
 * According to the official Replicate documentation, "Deployments allow you to control
 * the configuration of a model with a private, fixed API endpoint. You can control the
 * version of the model, the hardware it runs on, and how it scales."
 *
 * Deployments represent production-ready instances of models that are always available
 * for predictions with defined scaling parameters and hardware configurations. This service
 * provides methods for creating, retrieving, updating, and deleting deployments, as well
 * as running predictions against deployed models.
 *
 * Deployments differ from standard predictions in that they:
 * - Have consistent endpoint URLs that remain stable
 * - Can auto-scale based on demand within configured limits
 * - Are optimized for production usage with SLAs
 * - Incur different billing charges based on usage and capacity
 *
 * Usage example:
 *
 * // Create a new deployment
 * DeploymentConfiguration config = new DeploymentConfiguration();
 * config.setName("my-image-generator");
 * config.setModel("stability-ai/sdxl");
 * config.setVersion("da77bc59ee60423279fd632efb4795ab731d9e3ca9705ef3341091fb989b7eaf");
 * config.setHardware("gpu-t4");
 * config.setMinInstances(1);
 * config.setMaxInstances(5);
 *
 * Deployment deployment = replicate.deployments().create(config);
 *
 * // Run a prediction against the deployment
 * Prediction prediction = replicate.deployments()
 *     .createDeploymentPrediction("my-username", "my-image-generator")
 *     .withInput("prompt", "A cosmic landscape")
 *     .execute();
 *
 * @author Nilsw13
 * @since 1.0.0
 */
public interface DeploymentService {

    /**
     * Creates a new deployment with the specified configuration.
     *
     * This method provisions a new deployment of a model version with the specified
     * hardware configuration and scaling parameters. The deployment will be created
     * under the authenticated user's account.
     *
     * @param configuration The deployment configuration including model, version, hardware,
     *                      and scaling parameters
     * @return The created Deployment object containing details about the new deployment
     * @throws ReplicateApiException If the deployment creation fails due to invalid
     *         parameters or insufficient permissions
     * @see Deployment
     */
    Deployment create(DeploymentConfiguration configuration );

    /**
     * Creates a prediction builder for running predictions against a specific deployment.
     *
     * This method returns a builder that allows configuration of prediction inputs and
     * parameters before executing the prediction against the specified deployment.
     *
     * @param deploymentOwner The username or organization that owns the deployment
     * @param deploymentName The name of the deployment
     * @return A PredictionBuilderService for configuring and executing the prediction
     * @throws ReplicateApiException If the specified deployment doesn't exist or is
     *         not accessible to the authenticated user
     */
    PredictionBuilderService createDeploymentPrediction(String deploymentOwner, String deploymentName);

    /**
     * Retrieves a specific deployment by its owner and name.
     *
     * @param deploymentOwner The username or organization that owns the deployment
     * @param deploymentName The name of the deployment
     * @return The Deployment object containing details about the deployment
     * @throws ReplicateApiException If the deployment doesn't exist or is not
     * @see Deployment
     */
    Deployment get(String deploymentOwner, String deploymentName);

    /**
     * Lists all deployments accessible to the authenticated user.
     *
     * This method returns a paginated list of deployments. The deployments include
     * both those owned by the authenticated user and any shared with them.
     *
     * @return A DeploymentList containing the deployments and pagination information
     * @throws ReplicateApiException If the API request fails
     * @see DeploymentList
     */
    DeploymentList list();

    /**
     * Updates an existing deployment's configuration.
     *
     * This method allows modifying various aspects of a deployment, such as scaling
     * parameters (min/max instances), hardware type, or the underlying model version.
     * Only the properties provided in the changes object will be updated; others will
     * remain unchanged.
     *
     * @param owner The username or organization that owns the deployment
     * @param name The name of the deployment
     * @param changes The configuration changes to apply
     * @return The updated DeploymentConfiguration
     * @throws ReplicateApiException If the update fails due to invalid parameters,
     *         insufficient permissions, or if the deployment doesn't exist
     * @see DeploymentConfiguration
     */
    DeploymentConfiguration update(String owner, String name, DeploymentConfiguration changes);

    /**
     * Deletes a deployment.
     *
     * This method permanently removes a deployment. Once deleted, the deployment
     * cannot be recovered, and any active predictions against it will fail.
     *
     * According to Replicate API restrictions, you can only delete deployments
     * that have been offline and unused for at least 15 minutes. Attempting to
     * delete an active deployment or one that has been recently used will result
     * in an error.
     *
     * @param owner The username or organization that owns the deployment
     * @param name The name of the deployment
     * @return The deleted Deployment object
     * @throws ReplicateApiException If the deletion fails due to insufficient permissions,
     *         if the deployment doesn't exist, or if the deployment has been active
     *         within the last 15 minutes
     */
    Deployment delete(String owner, String name);
}

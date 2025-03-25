package com.nilsw13.spring_replicate.service;

/**
 * Main entry point for the Replicate API.
 *
 * This interface provides access to all Replicate API functionalities
 * through specialized methods that return specific services.
 *
 * Set the API key in application.properties:
 * replicate.api-key=your-api-key
 * @author Nilsw13
 * @since 1.0.0
 */


public interface Replicate {



    /**
     * Accesses account-related functionalities.
     *
     * This service provides methods to retrieve account information
     * @return An AccountService instance for interacting with account information
     */
    AccountService account();



    /**
     * Provides access to webhook signature verification services with default settings.
     *
     Retrieves the default signing webhook secret from the user's account.
     *
     * This method fetches the webhook signing secret configured for your Replicate account.
     * The signing secret is used to verify that webhook calls are genuinely coming from
     * Replicate to ensure the security of your webhook implementation.
     *

     * @return A SecretSigningWebhookService for interacting with secret
     */
    SecretSigningWebhookService defaultSecretWebhook();


    /**
     * Accesses prediction-related functionalities.
     *
     * This service allows creating and managing predictions from models.
     *
     * @return A PredictionService instance for interacting with predictions
     */
    PredictionService predictions();




    /**
     * Accesses model-related functionalities.
     * This service allows retrieving, creating, updating, and deleting models.
     * @return A ModelService instance for interacting with models
     */
    ModelService models();




    /**
     * Accesses collection-related functionalities.
     *
     * This service allows browsing curated collections of models.
     *
     * @return A CollectionService instance for interacting with collections
     */
    CollectionService collections();


    /**
     * Accesses deployment-related functionalities.
     *
     * This service allows creating, updating, and managing model deployments
     * for production use.
     *
     * @return A DeploymentService instance for interacting with deployments
     */
    DeploymentService deployments();




    /**
     * Accesses training-related functionalities.
     *
     * This service allows creating and managing fine-tuning jobs.
     *
     * @return A TrainingService instance for interacting with training jobs
     */
    TrainingService trainings();



    /**
     * Accesses hardware-related functionalities.
     *
     * This service allows retrieving information about available hardware options.
     *
     * @return A HardwareService instance for interacting with hardware information
     */
    HardwareService hardware();
}

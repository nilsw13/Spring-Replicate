package com.nilsw13.spring_replicate.service;


/**
 * Default implementation of the Replicate interface.
 *
 * This class serves as the primary implementation of the Replicate interface,
 * providing access to all Replicate API services through delegation to specialized
 * service implementations. It acts as a facade that simplifies API interactions
 * by exposing a cohesive interface while hiding the complexity of individual service
 * implementations.

 * @author Nilsw13
 * @since 1.0.0
 * @see Replicate
 */

public class ReplicateService implements Replicate {

    private final AccountService accountService;
    private final SecretSigningWebhookService secretSigningWebhookService;
    private final PredictionService predictionService;
    private final ModelService modelService;
    private final CollectionService collectionService;
    private final DeploymentService deploymentService;
    private final TrainingService trainingService;
    private final HardwareService hardwareService;


    /**
     * Constructs a new ReplicateService with all required service implementations.
     *
     * This constructor is typically called by Spring's dependency injection container
     * to instantiate a fully configured Replicate service. All dependencies are
     * injected automatically when using Spring Boot's auto-configuration.
     *
     * @param accountService Service for account operations
     * @param secretSigningWebhookService Service for webhook signature verification
     * @param predictionService Service for prediction operations
     * @param modelService Service for model operations
     * @param collectionService Service for collection operations
     * @param deploymentService Service for deployment operations
     * @param trainingService Service for training operations
     * @param hardwareService Service for hardware information
     */

    public ReplicateService(AccountService accountService, SecretSigningWebhookService secretSigningWebhookService, PredictionService predictionService, ModelService modelService, CollectionService collectionService, DeploymentService deploymentService, TrainingService trainingService, HardwareService hardwareService) {
        this.accountService = accountService;
        this.secretSigningWebhookService = secretSigningWebhookService;
        this.predictionService = predictionService;
        this.modelService = modelService;
        this.collectionService = collectionService;
        this.deploymentService = deploymentService;
        this.trainingService = trainingService;
        this.hardwareService = hardwareService;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public AccountService account() {
        return accountService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecretSigningWebhookService defaultSecretWebhook() {
        return secretSigningWebhookService;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ModelService models() {
        return modelService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollectionService collections() {
        return collectionService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeploymentService deployments() {
        return deploymentService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrainingService trainings() {
        return trainingService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HardwareService hardware() {
        return hardwareService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PredictionService predictions() {
        return predictionService;
    }




}


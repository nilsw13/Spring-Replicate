package com.nilsw13.spring_replicate.service;

public class ReplicateService implements Replicate {

    private final AccountService accountService;
    private final SecretSigningWebhookService secretSigningWebhookService;
    private final PredictionService predictionService;
    private final ModelService modelService;
    private final CollectionService collectionService;
    private final DeploymentService deploymentService;
    private final TrainingService trainingService;
    private final HardwareService hardwareService;

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

    @Override
    public AccountService account() {
        return accountService;
    }

    @Override
    public SecretSigningWebhookService defaultSecretWebhook() {
        return secretSigningWebhookService;
    }



    @Override
    public ModelService models() {
        return modelService;
    }

    @Override
    public CollectionService collections() {
        return collectionService;
    }

    @Override
    public DeploymentService deployments() {
        return deploymentService;
    }

    @Override
    public TrainingService trainings() {
        return trainingService;
    }

    @Override
    public HardwareService hardware() {
        return hardwareService;
    }

    @Override
    public PredictionService predictions() {
        return predictionService;
    }




}


package com.nilsw13.spring_replicate.service;

public interface Replicate {

    AccountService account();
    SecretSigningWebhookService defaultSecretWebhook();
    PredictionService predictions();
    ModelService models();
    CollectionService collections();
    DeploymentService deployments();
    TrainingService trainings();
    HardwareService hardware();
}

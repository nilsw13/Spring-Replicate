package com.nilsw13.spring_replicate.service.actions;

import com.nilsw13.spring_replicate.Replicate;

public class ReplicateService implements Replicate {

    private final AccountService accountService;
    private final SecretSigningWebhookService secretSigningWebhookService;
    private final PredictionService predictionService;

    public ReplicateService(AccountService accountService, SecretSigningWebhookService secretSigningWebhookService, PredictionService predictionService) {
        this.accountService = accountService;
        this.secretSigningWebhookService = secretSigningWebhookService;
        this.predictionService = predictionService;
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
    public PredictionService prediction() {
        return predictionService;
    }


}
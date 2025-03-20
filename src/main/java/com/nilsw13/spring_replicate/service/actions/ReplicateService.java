package com.nilsw13.spring_replicate.service.actions;

import com.nilsw13.spring_replicate.Replicate;

public class ReplicateService implements Replicate {

    private final AccountService accountService;
    private final SecretSigningWebhookService secretSigningWebhookService;

    public ReplicateService(AccountService accountService, SecretSigningWebhookService secretSigningWebhookService) {
        this.accountService = accountService;
        this.secretSigningWebhookService = secretSigningWebhookService;
    }

    @Override
    public AccountService account() {
        return accountService;
    }

    @Override
    public SecretSigningWebhookService defaultSecretWebhook() {
        return secretSigningWebhookService;
    }


}
package com.nilsw13.spring_replicate;

import com.nilsw13.spring_replicate.service.actions.AccountService;
import com.nilsw13.spring_replicate.service.actions.PredictionService;
import com.nilsw13.spring_replicate.service.actions.SecretSigningWebhookService;

public interface Replicate {

    AccountService account();
    SecretSigningWebhookService defaultSecretWebhook();
    PredictionService prediction();



}

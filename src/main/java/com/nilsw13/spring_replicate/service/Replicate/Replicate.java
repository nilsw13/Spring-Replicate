package com.nilsw13.spring_replicate.service.Replicate;

import com.nilsw13.spring_replicate.service.Account.AccountService;
import com.nilsw13.spring_replicate.service.Model.ModelService;
import com.nilsw13.spring_replicate.service.SecretWebhook.SecretSigningWebhookService;
import com.nilsw13.spring_replicate.service.collection.CollectionService;
import com.nilsw13.spring_replicate.service.prediction.PredictionService;

public interface Replicate {

    AccountService account();
    SecretSigningWebhookService defaultSecretWebhook();
    PredictionService predictions();
    ModelService models();
    CollectionService collections();

}

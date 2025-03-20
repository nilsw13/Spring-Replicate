package com.nilsw13.spring_replicate.config;

import com.nilsw13.spring_replicate.Replicate;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.impl.AccountServiceImpl;
import com.nilsw13.spring_replicate.impl.PredictionServiceImpl;
import com.nilsw13.spring_replicate.impl.SecretSigningWebhookServiceImpl;
import com.nilsw13.spring_replicate.service.actions.AccountService;
import com.nilsw13.spring_replicate.service.actions.PredictionService;
import com.nilsw13.spring_replicate.service.actions.ReplicateService;
import com.nilsw13.spring_replicate.service.actions.SecretSigningWebhookService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(ReplicateProperties.class)
@ConditionalOnProperty(prefix = "replicate", name = "api-key")
public class ReplicateAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public WebClient.Builder webClientBuilder() {
        return  WebClient.builder();
    }

    @Bean
    @ConditionalOnMissingBean
    public ReplicateRestClient replicateRestClient(ReplicateProperties replicateProperties) {
        return  new ReplicateRestClient(replicateProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public AccountService accountService(ReplicateRestClient replicateRestClient) {
        return new AccountServiceImpl(replicateRestClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public SecretSigningWebhookService secretSigningWebhookService(ReplicateRestClient replicateRestClient) {
        return new SecretSigningWebhookServiceImpl(replicateRestClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public PredictionService predictionService(ReplicateRestClient replicateRestClient){
        return  new PredictionServiceImpl(replicateRestClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public Replicate replicate(AccountService accountService,
                               SecretSigningWebhookService secretSigningWebhookService,
                               PredictionService predictionService) {
        return new ReplicateService(accountService, secretSigningWebhookService, predictionService);
    }



}

package com.nilsw13.spring_replicate.config;

import com.nilsw13.spring_replicate.impl.*;
import com.nilsw13.spring_replicate.service.DeploymentService;
import com.nilsw13.spring_replicate.service.HardwareService;
import com.nilsw13.spring_replicate.service.ModelService;
import com.nilsw13.spring_replicate.service.Replicate;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.service.AccountService;
import com.nilsw13.spring_replicate.service.TrainingService;
import com.nilsw13.spring_replicate.service.CollectionService;
import com.nilsw13.spring_replicate.service.PredictionService;
import com.nilsw13.spring_replicate.service.ReplicateService;
import com.nilsw13.spring_replicate.service.SecretSigningWebhookService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ReplicateProperties.class)
@ConditionalOnProperty(prefix = "replicate", name = "api-key")
public class ReplicateAutoConfig {



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
    public ModelService modelService(ReplicateRestClient replicateRestClient) {
        return new ModelServiceImpl(replicateRestClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public CollectionService collectionService(ReplicateRestClient replicateRestClient){
        return  new CollectionServiceImpl(replicateRestClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public DeploymentService deploymentService(ReplicateRestClient replicateRestClient){
        return new DeploymentServiceImpl(replicateRestClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public TrainingService trainingService(ReplicateRestClient replicateRestClient) {
        return  new TrainingServiceImpl(replicateRestClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public HardwareService hardwareService(ReplicateRestClient replicateRestClient) {
        return new HardwareServiceImpl(replicateRestClient);
    }




    @Bean
    @ConditionalOnMissingBean
    public Replicate replicate(AccountService accountService,
                               SecretSigningWebhookService secretSigningWebhookService,
                               PredictionService predictionService,
                               ModelService modelService,
                               CollectionService collectionService,
                               DeploymentService deploymentService,
                               TrainingService trainingService,
                               HardwareService hardwareService) {
        return new ReplicateService(accountService,
                secretSigningWebhookService,
                predictionService,
                modelService,
                collectionService,
                deploymentService,
                trainingService,
                hardwareService);
    }



}

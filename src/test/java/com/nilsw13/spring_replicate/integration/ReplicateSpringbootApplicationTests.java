package com.nilsw13.spring_replicate.integration;

import com.nilsw13.spring_replicate.model.Prediction.PredictionsListResponse;
import com.nilsw13.spring_replicate.service.Replicate.Replicate;
import com.nilsw13.spring_replicate.config.ReplicateAutoConfig;
import com.nilsw13.spring_replicate.model.AccountResponse;
import com.nilsw13.spring_replicate.model.Prediction.Prediction;
import com.nilsw13.spring_replicate.model.SecretSigningWebhookResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

class ReplicateSpringbootApplicationTests extends BaseReplicateTest{





}
package com.nilsw13.spring_replicate.integration;

import com.nilsw13.spring_replicate.Replicate;
import com.nilsw13.spring_replicate.config.ReplicateAutoConfig;
import com.nilsw13.spring_replicate.model.AccountResponse;
import com.nilsw13.spring_replicate.model.SecretSigningWebhookResponse;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ReplicateAutoConfig.class)
class ReplicateSpringbootApplicationTests {

    @Autowired
    private Replicate replicate;

    @Test
    void testGetAccountDetails() {

        Mono<AccountResponse> accountResponseMono = replicate.account().getAccountDetails();
        StepVerifier.create(accountResponseMono)
                .assertNext(account -> {
                    assertThat(account).isNotNull();
                    assertThat(account.getUsername()).isNotEmpty();

                    System.out.println("Account fetched : " + account.getUsername() +  account.getName() + account.getGithubUrl() + account.getType() );
                })
                .verifyComplete();


    }

    @Test
    void testSecretSigningWebhook() {
        Mono<SecretSigningWebhookResponse> secretSigningWebhookResponseMono = replicate.defaultSecretWebhook().getDefaultSecretSigningWebhook();
        StepVerifier.create(secretSigningWebhookResponseMono)
                .assertNext(secret -> {
                    assertThat(secret).isNotNull();
                    assertThat(secret.getKey()).isNotEmpty();

                    System.out.println("Default secret signing webhook : " + secret.getKey());
                })
                .verifyComplete();
    }

}
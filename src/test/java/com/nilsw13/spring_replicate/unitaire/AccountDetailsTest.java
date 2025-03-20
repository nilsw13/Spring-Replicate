package com.nilsw13.spring_replicate.unitaire;

import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.impl.AccountServiceImpl;
import com.nilsw13.spring_replicate.model.AccountResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountDetailsTest {

    @Mock
    private ReplicateRestClient replicateRestClient;

    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImpl(replicateRestClient);
    }

    @Test
    void testGetAccountDetails() {
        // Préparation des données de test
        AccountResponse mockResponse = new AccountResponse();
        mockResponse.setUsername("test_user");
        mockResponse.setName("Test User");

        // Configuration du mock
        when(replicateRestClient.get("account", AccountResponse.class))
                .thenReturn(Mono.just(mockResponse));

        // Exécution et vérification
        StepVerifier.create(accountService.getAccountDetails())
                .assertNext(account -> {
                    assertThat(account.getUsername()).isEqualTo("test_user");
                    assertThat(account.getName()).isEqualTo("Test User");
                })
                .verifyComplete();
    }
}
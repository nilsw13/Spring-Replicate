package com.nilsw13.springboot.replicate.unitaire;

import com.nilsw13.springboot.replicate.responsetype.account.Account;
import com.nilsw13.springboot.replicate.api.ReplicateRestClient;
import com.nilsw13.springboot.replicate.exception.ReplicateApiException;
import com.nilsw13.springboot.replicate.impl.AccountServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@Tag("unit-test")
 class AccountServiceImplTest {

    @Mock
    private ReplicateRestClient mockRestClient;

     AccountServiceImpl accountService;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);

        accountService = new AccountServiceImpl(mockRestClient);
    }

    @Test
     void testGet_ShouldCallCorrectEndpointAndReturnAccount() {
        Account mockAccount = new Account();
        mockAccount.setUsername("test-user");
        mockAccount.setName("Test User");

        when(mockRestClient.get("account", Account.class)).thenReturn(mockAccount);

        Account result = accountService.get();

        assertNotNull(result, "Le résultat ne devrait pas être null");
        assertEquals("test-user", result.getUsername(), "Le nom d'utilisateur devrait correspondre");
        assertEquals("Test User", result.getName(), "Le nom devrait correspondre");

        verify(mockRestClient, times(1)).get("account", Account.class);
        verifyNoMoreInteractions(mockRestClient);
    }

    @Test
    void badRequestTest() {
        ReplicateApiException authException = new ReplicateApiException(
                "Error while calling Replicate api:  account",
                new RuntimeException(),
                401,
                "{\"error\":\"Authentication failed. Invalid API key.\"}"
        );

        when(mockRestClient.get("account", Account.class)).thenThrow(authException);

        ReplicateApiException thrown = assertThrows(
                ReplicateApiException.class,
                () -> accountService.get(),
                "A Replicate api exception should be thrown when auth fail "
        );

        assertEquals(401, thrown.getStatusCode(), "Code status should be 401");
        assertTrue(thrown.getResponseBody().contains("Authentication failed"),
                "Response body should contais authentication error message");

        verify(mockRestClient).get("account", Account.class);

    }
}
package com.nilsw13.spring_replicate.unitaire.account;

import com.nilsw13.spring_replicate.ResponseType.Account.Account;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.impl.AccountServiceImpl;
import com.nilsw13.spring_replicate.unitaire.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@Category(UnitTest.class)
public class AccountServiceImplTest {

    @Mock
    private ReplicateRestClient mockRestClient;

    private AccountServiceImpl accountService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        accountService = new AccountServiceImpl(mockRestClient);
    }

    @Test
    public void testGet_ShouldCallCorrectEndpointAndReturnAccount() {
        Account mockAccount = new Account();
        mockAccount.setUsername("test-user");
        mockAccount.setName("Test User");

        when(mockRestClient.get("account", Account.class)).thenReturn(mockAccount);

        Account result = accountService.get();

        assertNotNull("Le résultat ne devrait pas être null", result);
        assertEquals("Le nom d'utilisateur devrait correspondre", "test-user", result.getUsername());
        assertEquals("Le nom devrait correspondre", "Test User", result.getName());

        verify(mockRestClient, times(1)).get("account", Account.class);
        verifyNoMoreInteractions(mockRestClient);
    }
}
package com.nilsw13.spring_replicate.integration.account;

import com.nilsw13.spring_replicate.integration.BaseReplicateTest;
import com.nilsw13.spring_replicate.ResponseType.Account.AccountResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountActionsTest extends BaseReplicateTest {

    @Test
    void testGetAccountDetails() {
        AccountResponse account = replicate.account().get();

        assertThat(account).isNotNull();
        assertThat(account.getUsername()).isNotEmpty();


        System.out.println("Account fetched : " + account.getUsername() + account.getName() + account.getGithubUrl() + account.getType());
    }
}

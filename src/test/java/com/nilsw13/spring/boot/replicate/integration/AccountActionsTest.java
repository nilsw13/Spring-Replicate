package com.nilsw13.spring.boot.replicate.integration;

import com.nilsw13.spring.boot.replicate.ResponseType.Account.Account;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("integration-test")
public class AccountActionsTest extends BaseReplicateTest {

    @Test
    void testGetAccountDetails() {
        Account account = replicate.account().get();

        assertThat(account).isNotNull();
        assertThat(account.getUsername()).isNotEmpty();


        System.out.println("Account fetched : " + account.getUsername() + account.getName() + account.getGithubUrl() + account.getType());
    }
}

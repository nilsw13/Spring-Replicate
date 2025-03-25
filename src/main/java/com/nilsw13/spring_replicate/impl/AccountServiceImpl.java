package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.ResponseType.Account.Account;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * Implementation of the AccountService interface that communicates with the Replicate API.
 *
 * This service implementation retrieves account information by making HTTP requests
 * to the Replicate API endpoint through the ReplicateRestClient. It provides access
 * to the authenticated user's account details
 *
 * This class is annotated with @Service, making it eligible for Spring's component
 * scanning and automatic dependency injection when included in a Spring application
 * context.
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see AccountService
 * @see Account
 * @see ReplicateRestClient
 */

@Service
public class AccountServiceImpl implements AccountService {


    private final ReplicateRestClient restClient;

    public AccountServiceImpl(ReplicateRestClient restClient) {
        this.restClient = restClient;
    }


    @Override
    public Account get() {
        return restClient.get("account", Account.class);
    }


}

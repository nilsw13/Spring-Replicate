package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.model.AccountResponse;
import com.nilsw13.spring_replicate.service.Account.AccountService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {


    private final ReplicateRestClient restClient;

    public AccountServiceImpl(ReplicateRestClient restClient) {
        this.restClient = restClient;
    }


    @Override
    public AccountResponse get() {
        return restClient.get("account", AccountResponse.class);
    }


}

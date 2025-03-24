package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.ResponseType.Account.Account;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.service.Account.AccountService;
import org.springframework.stereotype.Service;

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

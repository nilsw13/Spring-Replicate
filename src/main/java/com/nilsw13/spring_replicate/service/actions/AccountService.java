package com.nilsw13.spring_replicate.service.actions;

import com.nilsw13.spring_replicate.model.AccountResponse;
import reactor.core.publisher.Mono;

public interface AccountService {

    Mono<AccountResponse> getAccountDetails();

}

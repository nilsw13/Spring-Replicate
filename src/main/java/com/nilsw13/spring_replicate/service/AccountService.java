package com.nilsw13.spring_replicate.service;
import com.nilsw13.spring_replicate.ResponseType.Account.Account;


/**
 * Interface for accessing account information from the Replicate API.
 *
 * This service provides methods to retrieve details about the authenticated user's
 * Replicate account
 *
 * Usage example:
 *
 * Account account = replicate.account().get();
 * System.out.println("Username: " + account.getUsername());
 * System.out.println("API Quota: " + account.getApiQuota());
 *
 * @author Nilsw13
 * @since 1.0.0
 */

public interface AccountService {

    Account get();

}

package com.nilsw13.spring_boot.replicate.service;
import com.nilsw13.spring_boot.replicate.ResponseType.Account.Account;
import com.nilsw13.spring_boot.replicate.exception.ReplicateApiException;


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


    /**
     * Retrieves the current authenticated user's account information.
     *
     * This method fetches basic profile information about the Replicate account
     * associated with the API token configured in the application. The returned
     * Account object contains details such as the username, name, account type,
     * and GitHub profile URL.
     *
     * The method requires a valid API token to be configured, otherwise it will
     * throw an authentication error.
     *
     * @return An Account object containing the user's account information
     * @throws ReplicateApiException If an authentication error occurs or if the API
     *         request fails for any other reason
     */
    Account get();

}

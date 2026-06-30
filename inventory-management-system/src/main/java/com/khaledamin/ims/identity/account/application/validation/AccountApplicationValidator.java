package com.khaledamin.ims.identity.account.application.validation;

import com.khaledamin.ims.identity.account.domain.model.Account;
import com.khaledamin.ims.identity.account.domain.repository.AccountRepository;
import com.khaledamin.ims.identity.account.api.dto.AccountCreateRequest;
import com.khaledamin.ims.identity.account.api.dto.ProfileUpdateRequest;
import com.khaledamin.ims.identity.account.exception.AccountBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AccountApplicationValidator {

    private final AccountRepository accountRepository;

    // ---------------- CREATE ---------------- //


    public void validateCreate(AccountCreateRequest request) {
        ensureUsernameUnique(request.getUsername());
        ensureEmailUnique(request.getEmailAddress());
    }
    // ---------------- UPDATE ---------------- //

    public void validateUpdate(Account account, ProfileUpdateRequest request) {
        String emailAddress = request.getEmailAddress();
        if (emailAddress != null && !account.getEmailAddress().equals(emailAddress)){
            ensureEmailUnique(emailAddress);
        }
    }


    // ---------------- PRIVATE ---------------- //

    private void ensureUsernameUnique(String username) {

        if (accountRepository.existsByUsername(username)) {
            throw AccountBusinessException.usernameAlreadyExists()
                    .withClientDetails("username", username);
        }
    }

    private void ensureEmailUnique(String email) {

        if (accountRepository.existsByEmail(email)) {
            throw AccountBusinessException.emailAlreadyExists()
                    .withClientDetails("emailAddress", email);
        }
    }


}
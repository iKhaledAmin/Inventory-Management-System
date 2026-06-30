package com.khaledamin.ims.identity.account.domain.value;

import com.khaledamin.ims.identity.account.exception.AccountValidationException;

public record AccountId(Long value) {

    public AccountId {

        if (value == null) {
            throw AccountValidationException.invalidId()
                    .withDebugDetails("reason", "Account id must not be null");
        }

        if (value <= 0) {
            throw AccountValidationException.invalidId()
                    .withDebugDetails("reason", "Account id must be greater than zero")
                    .withDebugDetails("receivedValue", value);
        }
    }

    public static AccountId of(Long value) {
        return new AccountId(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
package com.khaledamin.ims.email.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailTemplate {

    ACCOUNT_ACTIVATION("account_activation","Activate your account"),
    ACCOUNT_RESET_PASSWORD("account_reset_password","Reset your password"),
    SELLER_APPLICATION_APPROVAL("seller_application_approval","Approve seller application"),
    SELLER_APPLICATION_REJECT("seller_application_reject","Reject seller application")
    ;



    private final String name;
    private final String subject;

}

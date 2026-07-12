package com.khaledamin.ims.core.logging.definition;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SystemOperation {
    ROLE_SYNC("role synchronization"),
    CAPABILITY_SYNC("capability synchronization"),
    ROLE_CAPABILITIES_SYNC("role capabilities synchronization"),

    EMAIL_RETRY_JOB("email retry job"),

    RESERVATION_CLEANUP_JOB("reservation cleanup job"),

    ADMIN_ACCOUNT_INITIALIZATION("admin account initialization");

    private final String name;
}

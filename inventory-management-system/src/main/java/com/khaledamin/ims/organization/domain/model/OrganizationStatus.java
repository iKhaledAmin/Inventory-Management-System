package com.khaledamin.ims.organization.domain.model;

public enum OrganizationStatus {
    ACTIVE,
    SUSPENDED;

    public static OrganizationStatus getDefault() {
        return ACTIVE;
    }
}
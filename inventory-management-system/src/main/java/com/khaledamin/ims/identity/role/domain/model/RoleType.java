package com.khaledamin.ims.identity.role.domain.model;

import lombok.Getter;

@Getter
public enum RoleType {

    IDENTITY,  // identify the persona of the user
    RESPONSIBILITY // define the responsibilities of the user
    ;


    public boolean isIdentityRole() {
        return this == IDENTITY;
    }

    public boolean isResponsibilityRole() {
        return this == RESPONSIBILITY;
    }
}
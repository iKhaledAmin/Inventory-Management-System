package com.khaledamin.ims.identity.role.domain.definition;

import com.khaledamin.ims.identity.role.domain.model.RoleType;
import com.khaledamin.ims.identity.role.domain.value.RoleDescription;
import com.khaledamin.ims.identity.role.domain.value.RoleDisplayName;
import com.khaledamin.ims.identity.role.domain.value.RoleName;
import com.khaledamin.ims.identity.role.exception.RoleTechnicalException;

import lombok.Getter;

import java.util.Arrays;


@Getter
public enum RoleDefinition {

    // ------------------------------------ Identity Roles ------------------------------------  //

    USER(
            "USER",
            "User",
            "Default account identity role",
            RoleType.IDENTITY,
            true
    ),


    OWNER(
            "OWNER",
            "Organization Owner",
            "The owner of and organization can manage its inventory",
            RoleType.IDENTITY,
            false
    )


    // ------------------------------------ Responsibility Roles ------------------------------------  //




    ;


    private final RoleName name;
    private final RoleDisplayName displayName;
    private final RoleDescription description;
    private final RoleType roleType;
    private final boolean defaultRole;


    RoleDefinition(String name, String displayName, String description, RoleType roleType, boolean defaultRole) {

        this.name = RoleName.of(name);
        this.displayName = RoleDisplayName.of(displayName);
        this.description = RoleDescription.of(description);
        this.roleType = roleType;
        this.defaultRole = defaultRole;
    }

    // Execute validation during enum initialization
    static {
        validateDefaultRole();
    }

    private static void validateDefaultRole() {

        long defaultRoleCount = Arrays.stream(values())
                .filter(roleDefinition -> roleDefinition.defaultRole)
                .count();


        if (defaultRoleCount < 1) {
            throw RoleTechnicalException.defaultRoleNotConfigured();
        }

    }
}



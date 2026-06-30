package com.khaledamin.ims.identity.role.domain.command;

import com.khaledamin.ims.identity.role.domain.model.RoleType;
import com.khaledamin.ims.identity.role.domain.value.*;

public record RoleCreateCommand(
        RoleName name,
        RoleDisplayName displayName,
        RoleDescription description,
        RoleType roleType,
        boolean defaultRole

) {

    public static RoleCreateCommand of(
        String name,
        String displayName,
        String description,
        RoleType roleType,
        boolean defaultRole

    ){
        return new RoleCreateCommand(
                RoleName.of(name),
                RoleDisplayName.of(displayName),
                RoleDescription.of(description),
                roleType,
                defaultRole
        );

    }


}
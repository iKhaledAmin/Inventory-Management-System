package com.khaledamin.ims.identity.role.domain.command;

import com.khaledamin.ims.identity.role.domain.value.*;

public record RoleUpdateCommand(RoleDisplayName displayName , RoleDescription description) {

    public static RoleUpdateCommand of(
            String displayName,
            String description
    ){
        return new RoleUpdateCommand(
                RoleDisplayName.of(displayName),
                RoleDescription.of(description)
        );
    }
}
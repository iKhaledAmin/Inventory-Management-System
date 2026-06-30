package com.khaledamin.ims.organization.domain.command;


import com.khaledamin.ims.organization.api.dto.OrganizationUpdateRequest;
import com.khaledamin.ims.organization.domain.model.OrganizationStatus;
import com.khaledamin.ims.organization.domain.value.OrganizationDescription;
import com.khaledamin.ims.organization.domain.value.OrganizationName;

import java.util.Optional;

public record OrganizationUpdateCommand(
        Optional<OrganizationName> name,
        Optional<OrganizationDescription> description,
        Optional<OrganizationStatus> status
) {

    public static OrganizationUpdateCommand of(String name, String description, OrganizationStatus status){
        return new OrganizationUpdateCommand(
                Optional.ofNullable(name).map(OrganizationName::of),
                Optional.ofNullable(description).map(OrganizationDescription::of),
                Optional.ofNullable(status)
        );
    }

    public static OrganizationUpdateCommand of(OrganizationUpdateRequest request){
        return of(
                request.getName(),
                request.getDescription(),
                null
        );
    }
}

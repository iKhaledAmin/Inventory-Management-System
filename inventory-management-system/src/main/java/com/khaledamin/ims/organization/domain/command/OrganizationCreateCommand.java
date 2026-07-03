package com.khaledamin.ims.organization.domain.command;

import com.khaledamin.ims.organization.api.dto.OrganizationCreateRequest;
import com.khaledamin.ims.organization.domain.value.OrganizationDescription;
import com.khaledamin.ims.organization.domain.value.OrganizationName;

public record OrganizationCreateCommand(OrganizationName name, OrganizationDescription description) {

    public static OrganizationCreateCommand of(String name, String description) {
        return new OrganizationCreateCommand(
                OrganizationName.of(name),
                OrganizationDescription.of(description)
        );
    }

    public static OrganizationCreateCommand of(OrganizationCreateRequest request){
        return of(
                request.getName(),
                request.getDescription()
        );
    }
}

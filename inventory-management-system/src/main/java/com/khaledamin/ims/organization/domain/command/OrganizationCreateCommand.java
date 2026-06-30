package com.khaledamin.ims.organization.domain.command;


import com.khaledamin.ims.organization.domain.value.OrganizationCode;
import com.khaledamin.ims.organization.domain.value.OrganizationDescription;
import com.khaledamin.ims.organization.domain.value.OrganizationName;

public record OrganizationCreateCommand(
        OrganizationCode code,
        OrganizationName name,
        OrganizationDescription description
) {

    public static OrganizationCreateCommand of(String code , String name, String description) {
        return new OrganizationCreateCommand(
                OrganizationCode.of(code),
                OrganizationName.of(name),
                OrganizationDescription.of(description)
        );
    }


    public static OrganizationCreateCommand of(String code,OrganizationName name, OrganizationDescription description) {
        return new OrganizationCreateCommand(
                OrganizationCode.of(code),
                name,
                description
        );
    }
}

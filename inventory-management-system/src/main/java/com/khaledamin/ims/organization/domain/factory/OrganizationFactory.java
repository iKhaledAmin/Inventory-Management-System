package com.khaledamin.ims.organization.domain.factory;

import com.khaledamin.ims.identity.account.domain.model.Account;
import com.khaledamin.ims.organization.api.dto.OrganizationCreateRequest;
import com.khaledamin.ims.organization.domain.command.OrganizationCreateCommand;
import com.khaledamin.ims.organization.domain.model.Organization;
import com.khaledamin.ims.organization.domain.value.OrganizationDescription;
import com.khaledamin.ims.organization.domain.value.OrganizationName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OrganizationFactory {
    private final OrganizationCodeGenerator codeGenerator;

    public Organization create(OrganizationCreateRequest request, Account owner) {

        OrganizationCreateCommand command = OrganizationCreateCommand.of(
                codeGenerator.generate(),
                OrganizationName.of(request.getName()),
                OrganizationDescription.of(request.getDescription())
        );

        return Organization.create(command,owner);
    }

}

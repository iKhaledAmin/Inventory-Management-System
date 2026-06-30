package com.khaledamin.ims.organization.domain.factory;

import com.khaledamin.ims.identity.account.domain.model.Account;
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

    public Organization create(OrganizationName name , OrganizationDescription description, Account owner) {

        OrganizationCreateCommand command = OrganizationCreateCommand.of(
                codeGenerator.generate(),
                name,
                description
        );

        return Organization.create(command,owner);
    }

}

package com.khaledamin.ims.organization.domain.capability;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.identity.capability.domain.definition.CapabilityDefinition;
import com.khaledamin.ims.identity.capability.domain.value.*;
import com.khaledamin.ims.identity.core.model.ActorType;
import lombok.Getter;

@Getter
public enum OrganizationCapability implements CapabilityDefinition {

    ORGANIZATION_UPDATE(
            "ORGANIZATION_UPDATE",
            "organization",
            "update",
            "Update Organization",
            "Allows updating organization",
            "ACCOUNT"
    ),

    ORGANIZATION_READ(
            "ORGANIZATION_READ",
            "organization",
            "read",
            "Read Organization",
            "Allows viewing organization details",
            "ACCOUNT"
    ),

    ORGANIZATION_CREATE_CLIENT(
            "ORGANIZATION_CREATE_CLIENT",
            "organization",
            "create_client",
            "Create Organization Client",
            "Allows creating clients in specific organization",
            "ACCOUNT"
    ),

    ORGANIZATION_ROTATE_CLIENT_SECRET(
            "ORGANIZATION_ROTATE_CLIENT_SECRET",
            "organization",
            "rotate_client_secret",
            "Rotate Organization Client Secret",
            "Allows rotating client secret in specific organization",
            "ACCOUNT"
    )

    ;


    private final CapabilityCode code;
    private final CapabilityResource resource;
    private final CapabilityAction action;
    private final CapabilityName name;
    private final CapabilityDescription description;
    private final ActorType expectedActorType;

    OrganizationCapability(
            String code,
            String resource,
            String action,
            String name,
            String description,
            String expectedActorType
    ) {
        this.code = CapabilityCode.of(code);
        this.resource = CapabilityResource.of(resource);
        this.action = CapabilityAction.of(action);
        this.name = CapabilityName.of(name);
        this.description = CapabilityDescription.of(description);
        this.expectedActorType = ActorType.from(expectedActorType);
    }

    @Override
    public SystemDomain getDomain() {
        return SystemDomain.ORGANIZATION;
    }
}

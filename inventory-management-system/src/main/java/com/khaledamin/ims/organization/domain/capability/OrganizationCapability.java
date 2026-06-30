package com.khaledamin.ims.organization.domain.capability;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.identity.capability.domain.definition.CapabilityDefinition;
import com.khaledamin.ims.identity.capability.domain.value.*;
import lombok.Getter;

@Getter
public enum OrganizationCapability implements CapabilityDefinition {

    ORGANIZATION_UPDATE(
            "ORGANIZATION_UPDATE",
            "organization",
            "update",
            "Update Organization",
            "Allows updating organization"
    ),

    ORGANIZATION_READ(
            "ORGANIZATION_UPDATE",
            "organization",
            "read",
            "Read Organization",
            "Allows viewing organization details"
    ),


    ;


    private final CapabilityCode code;
    private final CapabilityResource resource;
    private final CapabilityAction action;
    private final CapabilityName name;
    private final CapabilityDescription description;
    OrganizationCapability(
            String code,
            String resource,
            String action,
            String name,
            String description
    ) {
        this.code = CapabilityCode.of(code);
        this.resource = CapabilityResource.of(resource);
        this.action = CapabilityAction.of(action);
        this.name = CapabilityName.of(name);
        this.description = CapabilityDescription.of(description);
    }

    @Override
    public SystemDomain getDomain() {
        return SystemDomain.ORGANIZATION;
    }
}

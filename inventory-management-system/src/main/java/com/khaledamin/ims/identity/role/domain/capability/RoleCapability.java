package com.khaledamin.ims.identity.role.domain.capability;

import com.khaledamin.ims.identity.capability.domain.definition.CapabilityDefinition;
import com.khaledamin.ims.identity.capability.domain.value.*;
import com.khaledamin.ims.core.constant.SystemDomain;
import lombok.Getter;

@Getter
public enum RoleCapability implements CapabilityDefinition {


    ;


    private final CapabilityCode code;
    private final CapabilityResource resource;
    private final CapabilityAction action;
    private final CapabilityName name;
    private final CapabilityDescription description;
    RoleCapability(
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
        return SystemDomain.ROLE;
    }
}
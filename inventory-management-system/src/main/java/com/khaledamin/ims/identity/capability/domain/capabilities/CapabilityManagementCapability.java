package com.khaledamin.ims.identity.capability.domain.capabilities;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.identity.capability.domain.definition.CapabilityDefinition;
import com.khaledamin.ims.identity.capability.domain.value.*;
import com.khaledamin.ims.identity.core.model.ActorType;
import lombok.Getter;


@Getter
public enum CapabilityManagementCapability implements CapabilityDefinition {


    ;

    private final CapabilityCode code;
    private final CapabilityResource resource;
    private final CapabilityAction action;
    private final CapabilityName name;
    private final CapabilityDescription description;
    private final ActorType expectedActorType;


    CapabilityManagementCapability(
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
        return SystemDomain.CAPABILITY;
    }
}
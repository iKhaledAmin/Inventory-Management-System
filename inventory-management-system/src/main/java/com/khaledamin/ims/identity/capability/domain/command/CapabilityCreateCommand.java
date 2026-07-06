package com.khaledamin.ims.identity.capability.domain.command;

import com.khaledamin.ims.identity.capability.domain.value.*;
import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.identity.core.model.ActorType;

public record CapabilityCreateCommand(
        CapabilityCode code,
        CapabilityResource resource,
        CapabilityAction action,
        CapabilityName name,
        CapabilityDescription description,
        SystemDomain domain,
        ActorType expectedActorType
) {
    public static CapabilityCreateCommand of(
            String code,
            String resource,
            String action,
            String name,
            String description,
            SystemDomain domain,
            ActorType expectedActorType
    ){
      return new CapabilityCreateCommand(
              CapabilityCode.of(code),
              CapabilityResource.of(resource),
              CapabilityAction.of(action),
              CapabilityName.of(name),
              CapabilityDescription.of(description),
              domain,
              expectedActorType
      )  ;
    }
}

package com.khaledamin.ims.identity.capability.domain.command;

import com.khaledamin.ims.identity.capability.domain.value.*;

public record CapabilityUpdateCommand(
        CapabilityName name,
        CapabilityDescription description
) {
    public static CapabilityUpdateCommand of(String name, String description) {
        return new CapabilityUpdateCommand(
                CapabilityName.of(name),
                CapabilityDescription.of(description)
        );
    }
}

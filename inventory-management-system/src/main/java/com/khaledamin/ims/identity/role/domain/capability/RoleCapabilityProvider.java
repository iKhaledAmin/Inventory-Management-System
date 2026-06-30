package com.khaledamin.ims.identity.role.domain.capability;

import com.khaledamin.ims.identity.capability.application.provider.CapabilityProvider;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RoleCapabilityProvider implements CapabilityProvider {

    @Override
    public Set<RoleCapability> getCapabilities() {
        return Set.of(RoleCapability.values());
    }
}
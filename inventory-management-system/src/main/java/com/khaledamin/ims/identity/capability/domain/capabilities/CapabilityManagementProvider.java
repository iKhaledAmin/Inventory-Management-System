package com.khaledamin.ims.identity.capability.domain.capabilities;

import com.khaledamin.ims.identity.capability.application.provider.CapabilityProvider;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CapabilityManagementProvider implements CapabilityProvider {

    @Override
    public Set<CapabilityManagementCapability> getCapabilities() {
        return Set.of(CapabilityManagementCapability.values());
    }
}

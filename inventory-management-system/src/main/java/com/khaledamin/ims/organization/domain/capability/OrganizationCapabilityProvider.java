package com.khaledamin.ims.organization.domain.capability;

import com.khaledamin.ims.identity.capability.application.provider.CapabilityProvider;
import org.springframework.stereotype.Component;


import java.util.Set;

@Component
public class OrganizationCapabilityProvider implements CapabilityProvider {

    @Override
    public Set<OrganizationCapability> getCapabilities() {
        return Set.of(OrganizationCapability.values());
    }
}

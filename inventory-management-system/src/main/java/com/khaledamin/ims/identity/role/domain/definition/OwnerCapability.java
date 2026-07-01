package com.khaledamin.ims.identity.role.domain.definition;

import com.khaledamin.ims.identity.capability.domain.value.CapabilityCode;
import com.khaledamin.ims.organization.domain.capability.OrganizationCapability;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OwnerCapability implements RoleCapabilityDefinition {
    @Override
    public RoleDefinition getRole() {
        return RoleDefinition.OWNER;
    }

    @Override
    public Set<CapabilityCode> getCapabilityCodes() {
        return Set.of(
                OrganizationCapability.ORGANIZATION_UPDATE.getCode(),
                OrganizationCapability.ORGANIZATION_READ.getCode()
        );
    }
}

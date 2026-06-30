package com.khaledamin.ims.identity.role.domain.definition;


import com.khaledamin.ims.identity.capability.domain.value.CapabilityCode;

import java.util.Set;

public interface RoleCapabilityDefinition {

    RoleDefinition getRole();

    Set<CapabilityCode> getCapabilityCodes();

}
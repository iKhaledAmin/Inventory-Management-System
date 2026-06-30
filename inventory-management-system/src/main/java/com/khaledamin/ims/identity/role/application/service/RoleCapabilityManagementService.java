package com.khaledamin.ims.identity.role.application.service;

import com.khaledamin.ims.identity.role.domain.value.RoleName;
import com.khaledamin.ims.identity.capability.domain.value.CapabilityCode;

public interface RoleCapabilityManagementService {
    void addCapability(RoleName roleName, CapabilityCode code);
    void removeCapability(RoleName roleName, CapabilityCode code);
    void cleanupCapability(CapabilityCode code);
}

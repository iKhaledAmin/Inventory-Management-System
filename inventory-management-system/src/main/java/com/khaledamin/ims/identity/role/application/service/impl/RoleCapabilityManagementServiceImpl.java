package com.khaledamin.ims.identity.role.application.service.impl;


import com.khaledamin.ims.core.logging.event.BusinessEventLogger;
import com.khaledamin.ims.identity.capability.application.service.CapabilityQueryService;
import com.khaledamin.ims.identity.capability.domain.model.Capability;
import com.khaledamin.ims.identity.capability.domain.value.CapabilityCode;
import com.khaledamin.ims.identity.role.application.service.RoleCapabilityManagementService;
import com.khaledamin.ims.identity.role.application.service.RoleQueryService;
import com.khaledamin.ims.identity.role.domain.model.Role;
import com.khaledamin.ims.identity.role.domain.repository.RoleRepository;
import com.khaledamin.ims.identity.role.domain.value.RoleName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class RoleCapabilityManagementServiceImpl implements RoleCapabilityManagementService {
    private final RoleQueryService roleQueryService;
    private final CapabilityQueryService capabilityQueryService;
    private final RoleRepository roleRepository;
    private final BusinessEventLogger businessEventLogger;

    @Transactional
    @Override
    public void addCapability(RoleName roleName, CapabilityCode code) {

        Role role = roleQueryService.getByName(roleName);

        Capability capability = capabilityQueryService.getByCode(code);

        // Domain logic
        role.addCapability(capability);

        // Persistence
        roleRepository.save(role);

        businessEventLogger.roleCapabilityAssigned(
                role.getName(),
                capability.getCode()
        );

    }


    @Transactional
    @Override
    public void removeCapability(RoleName roleName, CapabilityCode code) {

        Role role = roleQueryService.getByName(roleName);
        Capability capability = capabilityQueryService.getByCode(code);

        // Domain logic
        role.removeCapability(capability);

        // Persistence
        roleRepository.save(role);

        businessEventLogger.roleCapabilityRemoved(
                role.getName(),
                capability.getCode()
        );
    }

    @Transactional
    @Override
    public void cleanupCapability(CapabilityCode code) {
        List<Role> roles = roleQueryService.getAllByCapabilityCode(code);

        roles.forEach(role -> removeCapability(
                RoleName.of(role.getName()),
                code
        ));
    }

}

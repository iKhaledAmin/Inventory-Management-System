package com.khaledamin.ims.bootstrap;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.logging.event.SystemOperationLogger;
import com.khaledamin.ims.core.logging.definition.SystemOperation;
import com.khaledamin.ims.core.logging.definition.SystemOperationType;
import com.khaledamin.ims.core.utils.diff.DiffResult;
import com.khaledamin.ims.core.utils.diff.DiffUtils;
import com.khaledamin.ims.identity.capability.application.service.CapabilityQueryService;
import com.khaledamin.ims.identity.capability.domain.model.Capability;
import com.khaledamin.ims.identity.capability.domain.value.CapabilityCode;
import com.khaledamin.ims.identity.role.application.service.RoleCapabilityManagementService;
import com.khaledamin.ims.identity.role.application.service.RoleQueryService;
import com.khaledamin.ims.identity.role.domain.definition.RoleCapabilityDefinition;
import com.khaledamin.ims.identity.role.domain.model.Role;
import com.khaledamin.ims.identity.role.domain.value.RoleName;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Order(InitializerOrder.ROLE_CAPABILITY)
@RequiredArgsConstructor
public class RoleCapabilityInitializer implements CommandLineRunner {

    private final Set<RoleCapabilityDefinition> definitions;
    private final RoleCapabilityManagementService roleCapabilityManagementService;
    private final RoleQueryService roleQueryService;
    private final CapabilityQueryService capabilityQueryService;
    private final SystemOperationLogger systemOperationLogger;

    @Override
    @Transactional
    public void run(String... args) {

        systemOperationLogger.started(
                SystemOperation.ROLE_CAPABILITIES_SYNC,
                SystemOperationType.SYNCHRONIZATION,
                SystemDomain.IDENTITY
        );

        for (RoleCapabilityDefinition definition : definitions) {
            synchronize(definition);
        }

        systemOperationLogger.completed(
                SystemOperation.ROLE_CAPABILITIES_SYNC,
                SystemOperationType.SYNCHRONIZATION,
                SystemDomain.IDENTITY
        );
    }

    private void synchronize(RoleCapabilityDefinition definition) {

        Role role = roleQueryService.getByName(
                definition.getRole().getName()
        );

        // todo later get all capabilities of role in one query
        Set<Capability> newCapabilities = definition.getCapabilityCodes()
                        .stream()
                        .map(capabilityQueryService::getByCode)
                        .collect(Collectors.toSet());

        DiffResult<Capability> diff = DiffUtils.diff(
                role.getCapabilities(),
                newCapabilities,
                Capability::getCode
        );

        if (diff.isEmpty()) {
            return;
        }

        addMissingCapabilities(role, diff.getToAdd());
        removeObsoleteCapabilities(role, diff.getToRemove());
    }

    /**
     * Adds capabilities missing from the persisted role state.
     */
    private void addMissingCapabilities(Role role, List<Capability> capabilitiesToAdd) {

        if (capabilitiesToAdd.isEmpty()) {
            return;
        }

        // todo later add capabilities in one query
        for (Capability capability : capabilitiesToAdd) {

            roleCapabilityManagementService.addCapability(
                    RoleName.of(role.getName()),
                    CapabilityCode.of(capability.getCode())
            );
        }
    }

    /**
     * Removes obsolete capabilities that no longer exist
     * in the canonical system role definition.
     */
    private void removeObsoleteCapabilities(Role role, List<Capability> obsoleteCapabilities) {

        if (obsoleteCapabilities.isEmpty()) {
            return;
        }

        // todo later remove capabilities in one query
        for (Capability capability : obsoleteCapabilities) {

            roleCapabilityManagementService.removeCapability(
                    RoleName.of(role.getName()),
                    CapabilityCode.of(capability.getCode())
            );

        }
    }

}
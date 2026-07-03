package com.khaledamin.ims.bootstrap;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.logging.event.SystemOperationLogger;
import com.khaledamin.ims.core.logging.definition.SystemOperation;
import com.khaledamin.ims.core.logging.definition.SystemOperationType;
import com.khaledamin.ims.identity.capability.application.registry.CapabilityRegistry;
import com.khaledamin.ims.identity.capability.application.service.CapabilityManagementService;
import com.khaledamin.ims.identity.capability.application.service.CapabilityQueryService;
import com.khaledamin.ims.identity.capability.domain.definition.CapabilityDefinition;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(InitializerOrder.CAPABILITY)
@RequiredArgsConstructor
public class CapabilityInitializer implements CommandLineRunner {

    private final CapabilityRegistry capabilityRegistry;
    private final CapabilityManagementService capabilityManagementService;
    private final CapabilityQueryService capabilityQueryService;
    private final SystemOperationLogger systemOperationLogger;

    @Override
    @Transactional
    public void run(String... args) {

        systemOperationLogger.started(
                SystemOperation.CAPABILITY_SYNC,
                SystemOperationType.SYNCHRONIZATION,
                SystemDomain.IDENTITY
        );

        synchronize();

        systemOperationLogger.completed(
                SystemOperation.CAPABILITY_SYNC,
                SystemOperationType.SYNCHRONIZATION,
                SystemDomain.IDENTITY
        );
    }

    private void synchronize() {

        synchronizeRegistryCapabilities();
    }

    /**
     * Create missing capabilities
     * and update existing ones.
     */
    private void synchronizeRegistryCapabilities() {

        for (CapabilityDefinition definition : capabilityRegistry.getAll()) {

            capabilityQueryService.getOptionalByCode(
                    definition.getCode()
            ).ifPresentOrElse(
                    capability -> capabilityManagementService.update(
                            definition.getCode(),
                            definition
                    ),
                    () -> capabilityManagementService.create(
                            definition
                    )
            );
        }
    }








    // this is a very dangerous method need adjustment if you will be used
//    /**
//     * Remove capabilities that no longer exist
//     * in the canonical registry.
//     */
//    private void removeObsoleteCapabilities() {
//
//        for (Capability capability : capabilityQueryService.getAll()) {
//
//            if (!capabilityRegistry.contains(capability.getCode())) {
//                capabilityManagementService.delete(
//                        CapabilityCode.of(capability.getCode())
//                );
//            }
//
//        }
//    }
}
package com.khaledamin.ims.bootstrap;


import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.logging.event.SystemOperationLogger;
import com.khaledamin.ims.core.logging.definition.SystemOperation;
import com.khaledamin.ims.core.logging.definition.SystemOperationType;
import com.khaledamin.ims.identity.role.application.service.RoleManagementService;
import com.khaledamin.ims.identity.role.application.service.RoleQueryService;
import com.khaledamin.ims.identity.role.domain.definition.RoleDefinition;
import com.khaledamin.ims.identity.role.domain.value.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Order(InitializerOrder.ROLE)
@RequiredArgsConstructor
public class RoleInitializer implements CommandLineRunner {

    private final RoleManagementService roleManagementService;
    private final RoleQueryService roleQueryService;
    private final SystemOperationLogger systemOperationLogger;

    @Override
    @Transactional
    public void run(String... args) {

        systemOperationLogger.started(
                SystemOperation.ROLE_SYNC,
                SystemOperationType.SYNCHRONIZATION,
                SystemDomain.IDENTITY
        );

        synchronize();

        systemOperationLogger.completed(
                SystemOperation.ROLE_SYNC,
                SystemOperationType.SYNCHRONIZATION,
                SystemDomain.IDENTITY
        );
    }

    private void synchronize() {

        synchronizeDefinedRoles();
    }

    /**
     * Create missing roles
     * and update existing roles.
     */
    private void synchronizeDefinedRoles() {

        for (RoleDefinition definition : RoleDefinition.values()) {

            roleQueryService.getOptionalByName(
                    definition.getName().value()
            ).ifPresentOrElse(
                    role -> roleManagementService.update(
                            RoleName.of(role.getName()),
                            definition
                    ),
                    () -> roleManagementService.create(
                            definition
                    )
            );
        }
    }









    // // this is a very dangerous method need adjustment if you will be used
//    /**
//     * Remove roles that no longer exist
//     * in RoleDefinition.
//     */
//    private void removeObsoleteRoles() {
//
//        Set<String> definedRoles = Arrays.stream(RoleDefinition.values())
//                .map(definition -> definition.getName().value())
//                .collect(Collectors.toSet());
//
//        for (Role role : roleQueryService.getAll()) {
//
//            if (!definedRoles.contains(role.getName())) {
//                roleManagementService.delete(
//                        RoleName.of(role.getName())
//                );
//            }
//        }
//    }
}
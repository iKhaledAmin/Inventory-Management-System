package com.khaledamin.ims.bootstrap;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.logging.event.SystemOperationLogger;
import com.khaledamin.ims.core.logging.definition.SystemOperation;
import com.khaledamin.ims.core.logging.definition.SystemOperationType;
import com.khaledamin.ims.identity.account.api.dto.AccountCreateRequest;
import com.khaledamin.ims.identity.account.application.service.AccountManagementService;
import com.khaledamin.ims.identity.account.application.service.AccountQueryService;
import com.khaledamin.ims.identity.account.domain.model.Account;
import com.khaledamin.ims.identity.account.domain.value.Username;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.role.application.service.RoleQueryService;
import com.khaledamin.ims.identity.role.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@Order(InitializerOrder.ADMIN)
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final AccountManagementService accountManagementService;
    private final AccountQueryService accountQueryService;
    private final BootstrapProperties properties;
    private final SystemOperationLogger systemOperationLogger;
    private final RoleQueryService roleQueryService;


    @Override
    @Transactional
    public void run(String... args) {

        systemOperationLogger.started(
                SystemOperation.ADMIN_ACCOUNT_INITIALIZATION,
                SystemOperationType.INITIALIZATION,
                SystemDomain.BOOTSTRAP
        );

        String adminUsername = properties.admin().username();
        Username username = Username.of(adminUsername);

        if (accountQueryService.existsByUsername(username)) {

            systemOperationLogger.skipped(
                    SystemOperation.ADMIN_ACCOUNT_INITIALIZATION,
                    SystemOperationType.INITIALIZATION,
                    SystemDomain.BOOTSTRAP,
                    "One admin account already exists"
            );

            return;
        }

        List<Role> roles = roleQueryService.getAll();

        AccountCreateRequest request = AccountCreateRequest.builder()
                .username(properties.admin().username())
                .password(properties.admin().password())
                .emailAddress(properties.admin().email())
                .firstName("System")
                .lastName("Administrator")
                .build();

        Account account = accountManagementService.create(request, roles);

        ActorCode accountCode = ActorCode.of(account.getAccountCode());

        accountManagementService.activate(accountCode);

        systemOperationLogger.completed(
                SystemOperation.ADMIN_ACCOUNT_INITIALIZATION,
                SystemOperationType.INITIALIZATION,
                SystemDomain.BOOTSTRAP
        );
    }
}
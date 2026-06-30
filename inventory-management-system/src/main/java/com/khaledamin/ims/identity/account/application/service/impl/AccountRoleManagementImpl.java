package com.khaledamin.ims.identity.account.application.service.impl;


import com.khaledamin.ims.core.logging.event.BusinessEventLogger;
import com.khaledamin.ims.identity.account.application.service.AccountQueryService;
import com.khaledamin.ims.identity.account.application.service.AccountRoleManagement;
import com.khaledamin.ims.identity.account.domain.model.Account;
import com.khaledamin.ims.identity.account.domain.repository.AccountRepository;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.role.application.service.RoleQueryService;
import com.khaledamin.ims.identity.role.domain.model.Role;
import com.khaledamin.ims.identity.role.domain.value.RoleName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class AccountRoleManagementImpl implements AccountRoleManagement {

    private final AccountQueryService accountQueryService;
    private final RoleQueryService roleQueryService;
    private final AccountRepository accountRepository;
    private final BusinessEventLogger businessEventLogger;

    @Transactional
    @Override
    public Account assignRole(ActorCode accountCode, RoleName roleName) {

        Account target = accountQueryService.getByAccountCode(accountCode);
        Role role = roleQueryService.getByName(roleName);

        // Domain logic
        target.assignRole(role);

        // Persist
        Account saved = accountRepository.save(target);

        // Log the business operation event
        businessEventLogger.accountRoleAssigned(
                saved.getAccountCode(),
                role.getName()
        );

        return saved;
    }

    @Transactional
    @Override
    public Account assignRoles(ActorCode accountCode, List<RoleName> roleNames) {

        // todo verify roleNames not null or empty

        Account target = accountQueryService.getByAccountCode(accountCode);
        List<Role> fetchedRoles = roleQueryService.getAllByNames(roleNames);

        // Domain logic
        target.assignRoles(fetchedRoles);

        // Persist
        Account saved = accountRepository.save(target);

        // Log the business operation event
        businessEventLogger.accountRolesAssigned(
                saved.getAccountCode(),
                fetchedRoles.stream().map(Role::getName).toList()
        );

        return saved;
    }

    @Transactional
    @Override
    public Account removeRole(ActorCode accountCode, RoleName roleName) {

        Account target = accountQueryService.getByAccountCode(accountCode);
        Role role = roleQueryService.getByName(roleName);

        // Domain logic
        target.removeRole(role);

        // Persist
        Account saved = accountRepository.save(target);

        // Log the business operation event
        businessEventLogger.accountRoleRemoved(
                saved.getAccountCode(),
                role.getName()
        );

        return saved;
    }


    @Transactional
    @Override
    public Account replaceRoles(ActorCode accountCode, List<RoleName> roleNames) {

        Account target = accountQueryService.getByAccountCode(accountCode);

        List<Role> fetchedRoles = roleQueryService.getAllByNames(roleNames);

        // Domain logic
        target.replaceRoles(fetchedRoles);

        // Persist
        Account saved = accountRepository.save(target);

        // Log the business operation event
        businessEventLogger.accountRolesReplaced(
                saved.getAccountCode(),
                fetchedRoles.stream().map(Role::getName).toList()
        );

        return saved;
    }


    @Transactional
    @Override
    public void cleanupRole(RoleName roleName){
        List<Account> accounts = accountRepository.findAllByRoleName(roleName.value());
        accounts.forEach(
                account -> removeRole(
                        ActorCode.of(account.getAccountCode()),
                        roleName
                ));
    }



}

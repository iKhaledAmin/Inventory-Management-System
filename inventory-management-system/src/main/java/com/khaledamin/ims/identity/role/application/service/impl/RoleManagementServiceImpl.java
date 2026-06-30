package com.khaledamin.ims.identity.role.application.service.impl;




import com.khaledamin.ims.core.logging.event.BusinessEventLogger;
import com.khaledamin.ims.identity.account.application.service.AccountRoleManagement;
import com.khaledamin.ims.identity.role.application.constant.RoleConst;
import com.khaledamin.ims.identity.role.application.service.RoleManagementService;
import com.khaledamin.ims.identity.role.application.service.RoleQueryService;
import com.khaledamin.ims.identity.role.application.validation.RoleValidator;
import com.khaledamin.ims.identity.role.domain.command.RoleCreateCommand;
import com.khaledamin.ims.identity.role.domain.command.RoleUpdateCommand;
import com.khaledamin.ims.identity.role.domain.definition.RoleDefinition;
import com.khaledamin.ims.identity.role.domain.model.Role;
import com.khaledamin.ims.identity.role.domain.repository.RoleRepository;
import com.khaledamin.ims.identity.role.domain.value.RoleName;
import com.khaledamin.ims.identity.role.exception.RoleTechnicalException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@AllArgsConstructor
@Service
public class RoleManagementServiceImpl implements RoleManagementService {

    private final RoleQueryService roleQueryService;
    private final RoleRepository roleRepository;
    private AccountRoleManagement accountRoleManagement;
    private final RoleValidator roleValidator;
    private final BusinessEventLogger businessEventLogger;



    @Transactional
    @CacheEvict(value = RoleConst.DEFAULT_ROLES_CACHE, allEntries = true)
    @Override
    public Role create(RoleDefinition roleDefinition){
        if (roleDefinition == null){
           throw RoleTechnicalException.nullRoleDefinition();
        }

        // Application validation
        roleValidator.ensureCanBeCreate(roleDefinition);

        RoleCreateCommand command = RoleCreateCommand.of(
                roleDefinition.getName().toString(),
                roleDefinition.getDisplayName().toString(),
                roleDefinition.getDescription().toString(),
                roleDefinition.getRoleType(),
                roleDefinition.isDefaultRole()
        );

        // Domain logic
        Role role = Role.create(command);

        // Persistence
        Role savedRole = roleRepository.save(role);

        // Log business event
        businessEventLogger.roleCreated(
                savedRole.getName()
        );

        return savedRole;

    }

    @Transactional
    @CacheEvict(value = RoleConst.DEFAULT_ROLES_CACHE, allEntries = true)
    @Override
    public Role update(RoleName roleName, RoleDefinition roleDefinition) {
        if (roleDefinition == null){
            throw RoleTechnicalException.nullRoleDefinition();
        }

        Role existingRole = roleQueryService.getByName(roleName);

        if (!existingRole.requiresUpdate(roleDefinition)) {
            return existingRole;
        }

        // Application validation
        roleValidator.ensureCanBeUpdate(existingRole, roleDefinition);

        RoleUpdateCommand command = RoleUpdateCommand.of(
                roleDefinition.getDisplayName().toString(),
                roleDefinition.getDescription().toString()
        );

        // Domain logic
        existingRole.update(command);

        // Persistence
        Role savedRole = roleRepository.save(existingRole);

        // Log business event
        businessEventLogger.roleUpdated(
                savedRole.getName()
        );

        return savedRole;
    }

    // this is a very dangerous method need adjustment if you will be used
    @Transactional
    @CacheEvict(value = RoleConst.DEFAULT_ROLES_CACHE, allEntries = true)
    @Override
    public void delete(RoleName roleName) {

        Role role = roleQueryService.getByName(roleName);

        accountRoleManagement.cleanupRole(roleName);

        roleRepository.flash();

        roleRepository.delete(role);

        businessEventLogger.roleDeleted(
                role.getName()
        );
    }

    @Transactional(readOnly = true)
    public Role viewRole(RoleName roleName) {

        Role role = roleQueryService.getByName(roleName);

        businessEventLogger.roleViewed(
                role.getName()
        );

        return role;
    }


    @Transactional(readOnly = true)
    public List<Role> listRoles() {

        List<Role> roles = roleQueryService.getAll();

        businessEventLogger.roleListed();

        return roles;
    }


}




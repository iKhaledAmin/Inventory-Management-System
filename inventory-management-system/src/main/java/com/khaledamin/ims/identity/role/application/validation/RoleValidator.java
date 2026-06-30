package com.khaledamin.ims.identity.role.application.validation;

import com.khaledamin.ims.identity.role.application.service.RoleUsageService;
import com.khaledamin.ims.identity.role.domain.definition.RoleDefinition;
import com.khaledamin.ims.identity.role.domain.model.Role;
import com.khaledamin.ims.identity.role.domain.repository.RoleRepository;
import com.khaledamin.ims.identity.role.exception.RoleBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleValidator {

    private final RoleRepository roleRepository;
    private final RoleUsageService roleUsageService;


    public void ensureCanBeCreate(RoleDefinition roleDefinition) {
        validateNameUniquenessFor(roleDefinition.getName().toString());
        validateDisplayNameUniquenessFor(roleDefinition.getDisplayName().toString());

    }

    public void ensureCanBeUpdate(Role existing, RoleDefinition roleDefinition) {
        String newDisplayName = roleDefinition.getDisplayName().toString();
        if (!existing.getDisplayName().equals(newDisplayName)) {
            validateDisplayNameUniquenessFor(newDisplayName);
        }
    }

    public void ensureCanBeDelete(Role role) {
        ensureRoleIsDeletable(role);
    }
    



    // ------------------------------------- PRIVATE METHODS ------------------------------------- //

    private void validateNameUniquenessFor(String name) {

        if (roleRepository.existsByName(name)) {
            throw RoleBusinessException.nameAlreadyExists()
                    .withClientDetails("roleName", name);
        }
    }

    private void validateDisplayNameUniquenessFor(String displayName) {

        if (roleRepository.existsByDisplayName(displayName)) {
            throw RoleBusinessException.displayNameAlreadyExists()
                    .withClientDetails("displayName", displayName);
        }
    }

    private void ensureRoleIsDeletable(Role role){
        if (roleUsageService.isAssignedToAnyAccount(role.getId())) {
            throw RoleBusinessException.roleAssignedToAccounts()
                    .withDebugDetails("roleName", role.getName());
        }
    }

    // ------------------------------------- PRIVATE METHODS ------------------------------------- //
}
package com.khaledamin.ims.identity.role.application.service;

import com.khaledamin.ims.identity.role.domain.model.Role;
import com.khaledamin.ims.identity.role.domain.value.RoleName;
import com.khaledamin.ims.identity.role.domain.definition.RoleDefinition;

import java.util.List;

public interface RoleManagementService {


    Role create(RoleDefinition roleDefinition);
    Role update(RoleName roleName,RoleDefinition roleDefinition);
    void delete(RoleName roleName);

    Role viewRole(RoleName roleName);
    List<Role> listRoles();


}

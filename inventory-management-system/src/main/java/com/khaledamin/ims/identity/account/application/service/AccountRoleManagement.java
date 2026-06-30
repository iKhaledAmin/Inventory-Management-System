package com.khaledamin.ims.identity.account.application.service;

import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.role.domain.value.RoleName;
import com.khaledamin.ims.identity.account.domain.model.Account;

import java.util.List;

public interface AccountRoleManagement {

    Account assignRole(ActorCode accountCode, RoleName roleName);
    Account assignRoles(ActorCode accountCode, List<RoleName> roleNames);
    Account removeRole(ActorCode accountCode, RoleName roleName);
    Account replaceRoles(ActorCode accountCode, List<RoleName> roleNames);

    void cleanupRole(RoleName roleName);
}

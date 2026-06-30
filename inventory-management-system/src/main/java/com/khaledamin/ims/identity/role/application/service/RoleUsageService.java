package com.khaledamin.ims.identity.role.application.service;

public interface RoleUsageService {


    /**
     * Checks whether the role is currently assigned to any account.
     *
     * @param roleId the role identifier
     * @return true if the role is in use, false otherwise
     */
    boolean isAssignedToAnyAccount(Long roleId);
}

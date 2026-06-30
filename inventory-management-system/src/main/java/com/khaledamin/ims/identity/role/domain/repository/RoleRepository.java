package com.khaledamin.ims.identity.role.domain.repository;

import com.khaledamin.ims.identity.capability.domain.value.CapabilityCode;
import com.khaledamin.ims.identity.role.domain.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    Role save(Role role);
    void delete(Role role);

    Optional<Role> findByName(String name);

    List<Role> findDefaultRoles();

    boolean existsByName(String name);
    boolean existsByDisplayName(String displayName);

    List<Role> findAllByNameIn(List<String> names);


    List<Role> findAll();

    List<Role> findAllByCapabilityCode(CapabilityCode capabilityCode);

    void flash();
}

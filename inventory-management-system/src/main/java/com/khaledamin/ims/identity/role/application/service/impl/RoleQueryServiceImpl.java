package com.khaledamin.ims.identity.role.application.service.impl;


import com.khaledamin.ims.identity.capability.domain.value.CapabilityCode;
import com.khaledamin.ims.identity.role.application.constant.RoleConst;
import com.khaledamin.ims.identity.role.application.service.RoleQueryService;
import com.khaledamin.ims.identity.role.domain.model.Role;
import com.khaledamin.ims.identity.role.domain.repository.RoleRepository;
import com.khaledamin.ims.identity.role.domain.value.RoleName;
import com.khaledamin.ims.identity.role.exception.RoleBusinessException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RoleQueryServiceImpl implements RoleQueryService {
    private final RoleRepository roleRepository;

    @Cacheable(RoleConst.DEFAULT_ROLES_CACHE)
    @Override
    public List<Role> getDefaultRoles() {
        return roleRepository.findDefaultRoles();
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getOptionalByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public Role getByName(String roleName) {
        return getOptionalByName(roleName).orElseThrow(() -> RoleBusinessException.notFound()
                .withClientDetails("reason", "Role not found for given name")
        );
    }

    @Override
    public Role getByName(RoleName roleName) {
        return getByName(roleName.value());
    }

    @Override
    public List<Role> getAllByNames(List<RoleName> roleNames) {

        if (roleNames == null || roleNames.isEmpty()) {
            return List.of();
        }

        List<String> names = roleNames.
                stream()
                .filter(Objects::nonNull)
                .map(RoleName::value)
                .distinct()
                .toList();

        List<Role> roles = roleRepository.findAllByNameIn(names);

        if (roles.size() != roleNames.size()) {

            Set<String> foundNames = roles.stream()
                    .map(Role::getName)
                    .collect(Collectors.toSet());

            List<String> notFoundNames = names.stream()
                    .filter(name -> !foundNames.contains(name))
                    .toList();

            throw RoleBusinessException.someRolesNotFound()
                    .withClientDetails("requestedRoleNames", names)
                    .withClientDetails("notFoundRoleNames", notFoundNames);
        }

        return roles;
    }

    @Override
    public List<Role> getAllByCapabilityCode(CapabilityCode capabilityCode) {
        return roleRepository.findAllByCapabilityCode(capabilityCode);
    }

}

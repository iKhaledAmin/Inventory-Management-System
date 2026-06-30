package com.khaledamin.ims.identity.account.application.service.impl;

import com.khaledamin.ims.identity.account.domain.repository.AccountRepository;
import com.khaledamin.ims.identity.role.application.service.RoleUsageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleUsageServiceImpl implements RoleUsageService {

    private final AccountRepository accountRepository;

    @Override
    public boolean isAssignedToAnyAccount(Long roleId) {
        return accountRepository.existsByRoleId(roleId);
    }
}
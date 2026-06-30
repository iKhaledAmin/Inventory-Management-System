package com.khaledamin.ims.identity.account.infrastructure.persistence;


import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.core.api.pagination.PageResultFactory;
import com.khaledamin.ims.core.api.pagination.PageableFactory;
import com.khaledamin.ims.identity.account.api.dto.AccountPageRequest;
import com.khaledamin.ims.identity.account.domain.model.Account;
import com.khaledamin.ims.identity.account.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;

    @Override
    public Account save(Account account) {
        return accountJpaRepository.save(account);
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return accountJpaRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return accountJpaRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return accountJpaRepository.existsByEmailAddress(email);
    }

    @Override
    public boolean existsByRoleName(String roleName) {
        return accountJpaRepository.existsByAccountRolesRoleName(roleName);
    }

    @Override
    public boolean existsByRoleId(Long roleId) {
        return accountJpaRepository.existsByAccountRolesRoleId(roleId);
    }

    @Override
    public long countByRoleName(String roleName) {
        return accountJpaRepository.countByAccountRolesRoleName(roleName);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return accountJpaRepository.findByEmailAddress(email);
    }

    @Override
    public Optional<Account> findByAccountCode(String accountCode) {
        return accountJpaRepository.findByAccountCode(accountCode);
    }

    @Override
    public PageResult<Account> findAll(AccountPageRequest request) {

        Page<Account> page = accountJpaRepository.findAll(
                PageableFactory.from(request)
        );

        return PageResultFactory.from(page);
    }

    @Override
    public List<Account> findAllByRoleName(String roleName) {
        return accountJpaRepository.findAllByAccountRolesRoleName(roleName);
    }

}

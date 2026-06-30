package com.khaledamin.ims.identity.account.application.service.impl;


import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.identity.account.api.dto.AccountPageRequest;
import com.khaledamin.ims.identity.account.application.service.AccountQueryService;
import com.khaledamin.ims.identity.account.domain.model.Account;
import com.khaledamin.ims.identity.account.domain.repository.AccountRepository;
import com.khaledamin.ims.identity.account.domain.value.EmailAddress;
import com.khaledamin.ims.identity.account.domain.value.Username;
import com.khaledamin.ims.identity.account.exception.AccountBusinessException;
import com.khaledamin.ims.identity.account.exception.AccountTechnicalException;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.identity.role.domain.value.RoleName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AccountQueryServiceImpl implements AccountQueryService {
    private final AccountRepository accountRepository;

    @Override
    public Optional<Account> getOptionalByEmail(EmailAddress emailAddress) {
        return accountRepository.findByEmail(emailAddress.toString());
    }

    @Override
    public Account getByEmail(EmailAddress emailAddress) {
        return getOptionalByEmail(emailAddress).orElseThrow(() -> AccountBusinessException.notFound()
                .withClientDetails("reason", "Account not found for given email")
                .withDebugDetails("email", emailAddress)
        );
    }

    @Override
    public Optional<Account> getOptionalByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public Optional<Account> getOptionalByUsername(Username username) {
        return accountRepository.findByUsername(username.toString());
    }

    public Optional<Account> getOptionalByAccountCode(ActorCode accountCode) {
        return accountRepository.findByAccountCode(accountCode.toString());
    }

    public Account getByAccountCode(ActorCode accountCode){
        return getOptionalByAccountCode(accountCode).orElseThrow(() -> AccountBusinessException.notFound()
                .withClientDetails("reason", "Account not found for given code")
                .withDebugDetails("accountCode", accountCode.toString())
        );
    }

    @Override
    public Account getByIdentity(ActorIdentity identity) {

        if (identity == null) {
            throw AccountTechnicalException.nullActorIdentity();
        }

        ActorCode accountCode = identity.getActorCode();

        Account account = getOptionalByAccountCode(accountCode)
                .orElseThrow(() -> AccountBusinessException.notFound()
                        .withClientDetails("reason", "Account not found for given identity")
                        .withClientDetails("actorType", identity.getActorType().name())
                        .withClientDetails("actorCode", identity.getActorCode().toString())
                );

        if (!account.getActorIdentity().sameAs(identity)) {
            throw AccountBusinessException.notFound()
                    .withClientDetails("reason", "Account not found for given identity")
                    .withClientDetails("actorType", identity.getActorType().name())
                    .withClientDetails("actorCode", identity.getActorCode().toString());
        }

        return account;
    }


    public PageResult<Account> getAll(AccountPageRequest request) {
        return accountRepository.findAll(request);
    }

    @Override
    public List<Account> getAllByRoleName(RoleName roleName) {
        return accountRepository.findAllByRoleName(
                roleName.toString()
        );
    }

    @Override
    public boolean existsByUsername(Username username) {
        return accountRepository.existsByUsername(username.toString());
    }


}

package com.khaledamin.ims.identity.account.application.service;



import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.identity.account.api.dto.AccountPageRequest;
import com.khaledamin.ims.identity.account.domain.model.Account;
import com.khaledamin.ims.identity.account.domain.value.EmailAddress;
import com.khaledamin.ims.identity.account.domain.value.Username;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.identity.role.domain.value.RoleName;

import java.util.List;
import java.util.Optional;

public interface AccountQueryService {

    Optional<Account> getOptionalByEmail(EmailAddress emailAddress);
    Account getByEmail(EmailAddress emailAddress);

    Optional<Account> getOptionalByUsername(String username);
    Optional<Account> getOptionalByUsername(Username username);

    Optional<Account> getOptionalByAccountCode(ActorCode accountCode);
    Account getByAccountCode(ActorCode accountCode);

    Account getByIdentity(ActorIdentity identity);

    PageResult<Account> getAll(AccountPageRequest request);

    List<Account> getAllByRoleName(RoleName roleName);

    boolean existsByUsername(Username username);
}

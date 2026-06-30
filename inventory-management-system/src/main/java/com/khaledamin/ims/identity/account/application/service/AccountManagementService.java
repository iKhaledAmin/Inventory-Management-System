package com.khaledamin.ims.identity.account.application.service;


import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.identity.account.api.dto.AccountCreateRequest;
import com.khaledamin.ims.identity.account.api.dto.AccountPageRequest;
import com.khaledamin.ims.identity.account.api.dto.ProfileUpdateRequest;
import com.khaledamin.ims.identity.account.domain.model.Account;
import com.khaledamin.ims.identity.account.domain.value.RawPassword;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.role.domain.model.Role;

import java.util.List;

public interface AccountManagementService {

    Account create(AccountCreateRequest request);
    Account create(AccountCreateRequest request, List<Role> roles);
    Account update(ActorCode accountCode, ProfileUpdateRequest request);

    Account activate(ActorCode accountCode);

    void resetPassword(ActorCode accountCode, RawPassword rawPassword);


    void login(ActorCode accountCode);

    Account viewAccount(ActorCode accountCode);
    Account viewMyAccount();
    PageResult<Account> listAccounts(AccountPageRequest request);



}

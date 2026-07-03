package com.khaledamin.ims.auth.account.api.mapper;

import com.khaledamin.ims.auth.account.api.dto.*;
import com.khaledamin.ims.identity.account.api.dto.AccountCreateRequest;
import com.khaledamin.ims.identity.account.domain.model.Account;
import com.khaledamin.ims.auth.security.principal.account.AccountPrincipal;
import com.khaledamin.ims.core.mapper.GlobalMapperConfig;
import com.khaledamin.ims.auth.security.jwt.JwtMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Mapper(config = GlobalMapperConfig.class,uses = JwtMapper.class)
public interface AccountAuthMapper {


    // ---------------- Registration ----------------

    @Mapping(target = "accountStatus", expression = "java(account.getAccountStatus().name())")
    @Mapping(target = "emailAddress", source = "emailAddress")
    AccountRegistrationResponse toRegistrationResponse(Account account);

    // ---------------- Activation ----------------

    @Mapping(target = "accountStatus", expression = "java(account.getAccountStatus().name())")
    @Mapping(target = "emailAddress", source = "emailAddress")
    AccountActivationResponse toActivationResponse(Account account);

    // ---------------- Login ----------------

    @Mapping(target = "account", source = "principal")
    @Mapping(target = "token", source = "jwtToken")
    AccountLoginResponse toLoginResponse(String jwtToken, AccountPrincipal principal);

    @Mapping(target = "accountCode", expression = "java(principal.getActorCode().toString())")
    @Mapping(target = "accountStatus", expression = "java(principal.getAccountStatus().name())")
    @Mapping(target = "username", source = "subject")
    @Mapping(target = "roles", expression = "java(mapRoles(principal.getRoles()))")
    @Mapping(target = "permissions", expression = "java(mapPermissions(principal.getPermissions()))")
    AccountLoginResponse.AccountInfo toAccountInfo(AccountPrincipal principal);

    // ---------------- Helpers ----------------


    default List<String> mapRoles(Set<String> roles) {
        return roles == null ? List.of() : new ArrayList<>(roles);
    }

    default List<String> mapPermissions(Set<String> permissions) {
        return permissions == null ? List.of() : new ArrayList<>(permissions);
    }

    AccountCreateRequest toCreateRequest(AccountRegistrationRequest request);
}


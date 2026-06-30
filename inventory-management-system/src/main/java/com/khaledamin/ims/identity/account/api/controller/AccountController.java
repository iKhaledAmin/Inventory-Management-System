package com.khaledamin.ims.identity.account.api.controller;


import com.khaledamin.ims.core.api.response.ApiResponse;
import com.khaledamin.ims.core.api.response.ApiResponseFactory;
import com.khaledamin.ims.identity.account.api.documentation.annotations.AccountUpdateApiDocs;
import com.khaledamin.ims.identity.account.api.documentation.annotations.AccountViewApiDocs;
import com.khaledamin.ims.identity.account.api.dto.AccountResponse;
import com.khaledamin.ims.identity.account.api.dto.ProfileUpdateRequest;
import com.khaledamin.ims.identity.account.api.mapper.AccountMapper;
import com.khaledamin.ims.identity.account.application.service.AccountManagementService;
import com.khaledamin.ims.identity.account.domain.model.Account;
import com.khaledamin.ims.identity.core.model.Actor;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.provider.ActorProvider;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Account Management",
        description = """
                APIs for managing the authenticated account.

                Features:
                - View account profile
                - Update account profile
                """
)
@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountManagementService accountManagementService;
    private final AccountMapper accountMapper;
    private final ActorProvider actorProvider;


    @AccountUpdateApiDocs
    @PreAuthorize("hasAuthority('account_update')")
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<AccountResponse>> update(@Valid @ModelAttribute ProfileUpdateRequest request) {

        Actor authenticatedActor = actorProvider.getCurrent();

        ActorCode accountCode = authenticatedActor.getActorIdentity().getActorCode();

        Account updatedAccount = accountManagementService.update(accountCode, request);

        AccountResponse response = accountMapper.toResponse(updatedAccount);

        return ResponseEntity.ok(
                ApiResponseFactory.success(response)
        );
    }


    @AccountViewApiDocs
    @PreAuthorize("hasAuthority('account_read')")
    @GetMapping
    public ResponseEntity<ApiResponse<AccountResponse>> view() {

        Account account =
                accountManagementService.viewMyAccount();

        AccountResponse response =
                accountMapper.toResponse(account);

        return ResponseEntity.ok(
                ApiResponseFactory.success(response)
        );
    }
}
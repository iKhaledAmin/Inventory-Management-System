package com.khaledamin.ims.auth.account.api.controller;

import com.khaledamin.ims.auth.account.api.documentation.annotations.*;
import com.khaledamin.ims.auth.account.api.dto.*;
import com.khaledamin.ims.auth.account.application.service.AccountAuthService;
import com.khaledamin.ims.core.api.response.ApiActionResponse;
import com.khaledamin.ims.core.api.response.ApiResponse;
import com.khaledamin.ims.core.api.response.ApiResponseFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Authentication - Accounts",
        description = """
                Public account (Human-Actors) authentication APIs.

                Features:
                - Account registration
                - Account activation
                - Account login
                - Password reset request
                - Password reset confirmation

                Notes:
                - These APIs are public.
                - Authentication is not required.
                - This module supports account (Human-Actor) authentication.
                """
)
@RestController
@RequestMapping("/auth/accounts")
@RequiredArgsConstructor
public class AccountAuthController {

    private final AccountAuthService authService;

    @AccountRegistrationApiDocs
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AccountRegistrationResponse>> register(
            @RequestBody @Valid AccountRegistrationRequest request
    ) {

        AccountRegistrationResponse response = authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseFactory.success(response));
    }

    @AccountActivationApiDocs
    @PostMapping("/activate")
    public ResponseEntity<ApiResponse<AccountActivationResponse>> activate(
            @RequestBody @Valid AccountActivationRequest request
    ) {

        AccountActivationResponse response = authService.activate(request);

        return ResponseEntity.ok(
                ApiResponseFactory.success(response)
        );
    }

    @AccountLoginApiDocs
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AccountLoginResponse>> login(
            @RequestBody @Valid AccountLoginRequest request
    ) {

        AccountLoginResponse response = authService.login(request);

        return ResponseEntity.ok(
                ApiResponseFactory.success(response)
        );
    }

    @AccountResetPasswordRequestApiDocs
    @PostMapping("/reset-password-request")
    public ResponseEntity<ApiResponse<ApiActionResponse>> requestResetPassword(
            @RequestBody @Valid AccountResetPasswordRequest request
    ) {

        ApiActionResponse response = authService.requestResetPassword(request);

        return ResponseEntity.ok(
                ApiResponseFactory.success(response)
        );
    }

    @AccountConfirmResetPasswordApiDocs
    @PostMapping("/reset-password-confirm")
    public ResponseEntity<ApiResponse<ApiActionResponse>> confirmResetPassword(
            @RequestBody @Valid AccountConfirmResetPasswordRequest request
    ) {

        ApiActionResponse response = authService.resetPassword(request);

        return ResponseEntity.ok(
                ApiResponseFactory.success(response)
        );
    }
}
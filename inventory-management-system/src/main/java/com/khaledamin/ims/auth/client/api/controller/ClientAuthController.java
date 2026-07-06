package com.khaledamin.ims.auth.client.api.controller;


import com.khaledamin.ims.auth.client.api.documentation.annotations.ClientTokenApiDocs;
import com.khaledamin.ims.auth.client.api.dto.ClientTokenRequest;
import com.khaledamin.ims.auth.client.api.dto.ClientTokenResponse;
import com.khaledamin.ims.auth.client.application.service.ClientAuthService;
import com.khaledamin.ims.core.api.response.ApiResponse;
import com.khaledamin.ims.core.api.response.ApiResponseFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Authentication - Clients",
        description = """
                Machine-to-machine authentication APIs.

                Features:
                - Client authentication
                - JWT access token generation

                Notes:
                - These APIs are public.
                - Authentication is not required.
                - Intended for system integrations and machine clients.
                """
)
@RestController
@RequestMapping("/auth/clients")
@RequiredArgsConstructor
public class ClientAuthController {

    private final ClientAuthService authService;

    @ClientTokenApiDocs
    @PostMapping("/token")
    public ResponseEntity<ApiResponse<ClientTokenResponse>> generateToken(
            @Valid @RequestBody ClientTokenRequest request
    ) {

        ClientTokenResponse response = authService.generateToken(request);

        return ResponseEntity.ok(
                ApiResponseFactory.success(response)
        );
    }

}
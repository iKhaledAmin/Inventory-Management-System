package com.khaledamin.ims.organization.api.controller;

import com.khaledamin.ims.core.api.response.ApiResponse;
import com.khaledamin.ims.core.api.response.ApiResponseFactory;
import com.khaledamin.ims.identity.client.api.dto.ClientCreateRequest;
import com.khaledamin.ims.identity.client.api.dto.ClientResponse;
import com.khaledamin.ims.identity.client.api.dto.ClientSecretResponse;
import com.khaledamin.ims.identity.client.api.mapper.ClientMapper;
import com.khaledamin.ims.identity.client.domain.model.Client;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.organization.api.documentation.annotations.*;
import com.khaledamin.ims.organization.api.dto.OrganizationResponse;
import com.khaledamin.ims.organization.api.dto.OrganizationSettingsResponse;
import com.khaledamin.ims.organization.api.dto.OrganizationSettingsUpdateRequest;
import com.khaledamin.ims.organization.api.dto.OrganizationUpdateRequest;
import com.khaledamin.ims.organization.api.mapper.OrganizationMapper;
import com.khaledamin.ims.organization.api.mapper.OrganizationSettingsMapper;
import com.khaledamin.ims.organization.application.service.OrganizationManagementService;
import com.khaledamin.ims.organization.domain.model.Organization;
import com.khaledamin.ims.organization.domain.model.OrganizationSettings;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Organization Management",
        description = """
                APIs for managing the organization that own the inventory.

                Features:
                - View organization details
                - Update organization profile
                - Create organization client
                - Rotate organization client secret
                - Update organization settings
                """
)
@RestController
@RequestMapping("organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationManagementService organizationManagementService;
    private final OrganizationMapper organizationMapper;
    private final OrganizationSettingsMapper organizationSettingsMapper;
    private final ClientMapper clientMapper;



    @OrganizationUpdateApiDocs
    @PreAuthorize("hasAuthority('organization_update')")
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<OrganizationResponse>> update(
            @Valid @ModelAttribute OrganizationUpdateRequest request
    ) {

        Organization updatedOrganization = organizationManagementService.update(request);

        OrganizationResponse response = organizationMapper.toResponse(updatedOrganization);

        return ResponseEntity.ok(
                ApiResponseFactory.success(response)
        );
    }


    @OrganizationViewApiDocs
    @PreAuthorize("hasAuthority('organization_read')")
    @GetMapping
    public ResponseEntity<ApiResponse<OrganizationResponse>> view() {

        Organization organization = organizationManagementService.view();

        OrganizationResponse response = organizationMapper.toResponse(organization);

        return ResponseEntity.ok(
                ApiResponseFactory.success(response)
        );
    }


    @OrganizationClientCreateApiDocs
    @PreAuthorize("hasAuthority('organization_create_client')")
    @PostMapping("/client")
    public ResponseEntity<ApiResponse<ClientResponse>> createClient(
            @Valid @RequestBody ClientCreateRequest request
    ) {

        Client client = organizationManagementService.createClient(request);

        ClientResponse response = clientMapper.toResponse(client);

        return ResponseEntity.ok(
                ApiResponseFactory.success(response)
        );
    }


    @OrganizationClientRotateSecretApiDocs
    @PreAuthorize("hasAuthority('organization_rotate_client_secret')")
    @PostMapping("/client/{clientCode}/rotate-secret")
    public ResponseEntity<ApiResponse<ClientSecretResponse>> rotateClientSecret(


            @Parameter(
                    description = "Client unique business identifier",
                    example = "CLI-01JY8A7R4W7KX2N8QF5M6P9T3",
                    required = true
            )
            @PathVariable
            String clientCode
    ) {

        String secret = organizationManagementService.rotateClientSecret(
                ActorCode.of(clientCode)
        );

        return ResponseEntity.ok(
                ApiResponseFactory.success(
                        ClientSecretResponse.builder()
                                .clientCode(clientCode)
                                .clientSecret(secret)
                                .build()
                )
        );
    }

    @OrganizationSettingsUpdateApiDocs
    @PreAuthorize("hasAuthority('organization_update_settings')")
    @PatchMapping("/settings")
    public ResponseEntity<ApiResponse<OrganizationSettingsResponse>> updateSettings(

            @Valid
            @RequestBody
            OrganizationSettingsUpdateRequest request
    ) {

        OrganizationSettings settings = organizationManagementService.updateSettings(request);

        OrganizationSettingsResponse response = organizationSettingsMapper.toResponse(settings);

        return ResponseEntity.ok(
                ApiResponseFactory.success(response)
        );
    }
}
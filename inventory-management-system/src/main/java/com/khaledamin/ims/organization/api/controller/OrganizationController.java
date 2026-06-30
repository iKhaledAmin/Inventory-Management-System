package com.khaledamin.ims.organization.api.controller;

import com.khaledamin.ims.core.api.response.ApiResponse;
import com.khaledamin.ims.core.api.response.ApiResponseFactory;
import com.khaledamin.ims.organization.api.documentation.annotations.OrganizationUpdateApiDocs;
import com.khaledamin.ims.organization.api.documentation.annotations.OrganizationViewApiDocs;
import com.khaledamin.ims.organization.api.dto.OrganizationResponse;
import com.khaledamin.ims.organization.api.dto.OrganizationUpdateRequest;
import com.khaledamin.ims.organization.api.mapper.OrganizationMapper;
import com.khaledamin.ims.organization.application.service.OrganizationManagementService;
import com.khaledamin.ims.organization.domain.model.Organization;
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
                """
)
@RestController
@RequestMapping("organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationManagementService organizationManagementService;
    private final OrganizationMapper organizationMapper;


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
}
package com.khaledamin.ims.organization.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.annotations.UnauthorizedApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthenticatedApiDocs;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
import com.khaledamin.ims.organization.api.documentation.examples.OrganizationClientCreateExamples;
import com.khaledamin.ims.organization.api.documentation.schema.ClientApiResponseSchema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented

@Operation(
        summary = "Create Organization Client",
        description = """
                Creates a machine client for the currently authenticated organization.

                Required Authority:
                - organization_create_client

                Business Rules:
                - Organization must exist.
                - Organization can own only one client during MVP phase.
                - Client ID must be unique across the system.
                - All client capabilities are automatically assigned.
                
                Returns:
                - Generated client code
                - Client ID
                - Client name
                - Client description
                - Client status
                """
)

@ApiResponse(
        responseCode = "200",
        description = "Organization client created successfully",
        content = @Content(
                schema = @Schema(
                        implementation = ClientApiResponseSchema.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Client Created",
                                summary = "Organization client created successfully",
                                value = OrganizationClientCreateExamples.SUCCESS_RESPONSE
                        )
                }
        )
)

@ApiResponse(
        responseCode = "400",
        description = "Validation failed or client ID already exists",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {

                        @ExampleObject(
                                name = "Invalid Client ID",
                                summary = "Client ID validation failed",
                                value = OrganizationClientCreateExamples.INVALID_CLIENT_ID
                        ),

                        @ExampleObject(
                                name = "Invalid Name",
                                summary = "Client name validation failed",
                                value = OrganizationClientCreateExamples.INVALID_NAME
                        ),

                        @ExampleObject(
                                name = "Multiple Validation Errors",
                                summary = "Request contains multiple validation violations",
                                value = OrganizationClientCreateExamples.MULTIPLE_VALIDATION_ERRORS
                        )

                }
        )
)

@ApiResponse(
        responseCode = "404",
        description = "Organization not found",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Organization Not Found",
                                summary = "Authenticated account does not own an organization",
                                value = OrganizationClientCreateExamples.ORGANIZATION_NOT_FOUND
                        )
                }
        )
)

@ApiResponse(
        responseCode = "409",
        description = "Organization already owns a client",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Organization Client Already Exists",
                                summary = "Organization already has a registered client",
                                value = OrganizationClientCreateExamples.ORGANIZATION_CLIENT_ALREADY_EXISTS
                        ),
                        @ExampleObject(
                                name = "Client ID Already Exists",
                                summary = "Client ID already exists in the system",
                                value = OrganizationClientCreateExamples.CLIENT_ID_ALREADY_EXISTS
                        )
                }
        )
)

@UnauthenticatedApiDocs
@UnauthorizedApiDocs
@InternalServerErrorApiDocs
public @interface OrganizationClientCreateApiDocs {
}
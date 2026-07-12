package com.khaledamin.ims.organization.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthenticatedApiDocs;
import com.khaledamin.ims.core.api.documentation.examples.UnauthorizedExamples;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
import com.khaledamin.ims.organization.api.documentation.schema.ClientSecretApiResponseSchema;
import com.khaledamin.ims.organization.api.documentation.examples.OrganizationClientRotateSecretExamples;
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
        summary = "Rotate Organization Client Secret",
        description = """
                Generates a new secret for a client that belongs to the current organization.

                Required Authority:
                - organization_rotate_client_secret

                Security Notes:
                - The previous secret becomes invalid immediately.
                - The new secret is returned only once.
                - Store the returned secret securely.

                Business Rules:
                - Client must exist.
                - Client must belong to the current organization.
                """
)

@ApiResponse(
        responseCode = "200",
        description = "Client secret rotated successfully",
        content = @Content(
                schema = @Schema(
                        implementation = ClientSecretApiResponseSchema.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Secret Rotated",
                                summary = "New client secret generated successfully",
                                value = OrganizationClientRotateSecretExamples.SUCCESS_RESPONSE
                        )
                }
        )
)

@ApiResponse(
        responseCode = "404",
        description = "Client not found",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Client Not Found",
                                summary = "Specified client does not exist",
                                value = OrganizationClientRotateSecretExamples.CLIENT_NOT_FOUND
                        )
                }
        )
)

@ApiResponse(
        responseCode = "403",
        description = "Access denied or rotate secret operation forbidden",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {

                        @ExampleObject(
                                name = "Access Denied",
                                summary = "Authenticated actor does not have required authority",
                                value = UnauthorizedExamples.FORBIDDEN
                        ),

                        @ExampleObject(
                                name = "Rotate Secret Forbidden",
                                summary = "Client does not belong to the current organization",
                                value = OrganizationClientRotateSecretExamples.ROTATE_SECRET_FORBIDDEN
                        )
                }
        )
)

@UnauthenticatedApiDocs
@InternalServerErrorApiDocs
public @interface OrganizationClientRotateSecretApiDocs {
}
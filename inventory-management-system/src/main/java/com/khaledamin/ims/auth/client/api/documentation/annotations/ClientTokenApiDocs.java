package com.khaledamin.ims.auth.client.api.documentation.annotations;

import com.khaledamin.ims.auth.client.api.documentation.examples.ClientTokenExamples;
import com.khaledamin.ims.auth.client.api.documentation.schema.ClientTokenApiResponseSchema;
import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthenticatedApiDocs;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
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
        summary = "Generate Client Access Token",
        description = """
                Authenticates a machine client using its client credentials.

                Authentication Flow:
                - Validates client credentials.
                - Verifies client status.
                - Generates JWT access token.
                - Records successful authentication activity.

                Business Rules:
                - Client ID must exist.
                - Client secret must match.
                - Client must be active.
                - Client must not be locked.

                Returned Information:
                - Client information
                - Granted authorities
                - JWT access token
                """
)

@ApiResponse(
        responseCode = "200",
        description = "Authentication successful",
        content = @Content(
                schema = @Schema(
                        implementation = ClientTokenApiResponseSchema.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Token Generated",
                                summary = "Client authenticated successfully and received JWT token",
                                value = ClientTokenExamples.SUCCESS_RESPONSE
                        )
                }
        )
)

@ApiResponse(
        responseCode = "400",
        description = "Validation failed",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {

                        @ExampleObject(
                                name = "Client ID Required",
                                summary = "Client ID is mandatory",
                                value = ClientTokenExamples.CLIENT_ID_REQUIRED
                        ),

                        @ExampleObject(
                                name = "Client Secret Required",
                                summary = "Client secret is mandatory",
                                value = ClientTokenExamples.CLIENT_SECRET_REQUIRED
                        ),

                        @ExampleObject(
                                name = "Multiple Validation Errors",
                                summary = "Request contains multiple validation violations",
                                value = ClientTokenExamples.MULTIPLE_VALIDATION_ERRORS
                        )
                }
        )
)

@ApiResponse(
        responseCode = "401",
        description = "Authentication failed",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {

                        @ExampleObject(
                                name = "Invalid Credentials",
                                summary = "Client ID or client secret is incorrect",
                                value = ClientTokenExamples.INVALID_CREDENTIALS
                        ),

                        @ExampleObject(
                                name = "Client Inactive",
                                summary = "Client exists but is not active",
                                value = ClientTokenExamples.CLIENT_INACTIVE
                        ),

                        @ExampleObject(
                                name = "Client Locked",
                                summary = "Client exists but is locked",
                                value = ClientTokenExamples.CLIENT_LOCKED
                        )
                }
        )
)

@InternalServerErrorApiDocs
public @interface ClientTokenApiDocs {
}
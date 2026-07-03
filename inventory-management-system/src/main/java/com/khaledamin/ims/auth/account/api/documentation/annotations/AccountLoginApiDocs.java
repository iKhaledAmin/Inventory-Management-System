package com.khaledamin.ims.auth.account.api.documentation.annotations;

import com.khaledamin.ims.auth.account.api.documentation.examples.AccountLoginExamples;
import com.khaledamin.ims.auth.account.api.documentation.schema.AccountLoginApiResponseSchema;
import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
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
        summary = "Authenticate Account",
        description = """
                Authenticates an account using username and password.

                Authentication Flow:
                - Validates credentials.
                - Verifies account status.
                - Generates JWT access token.
                - Records successful login activity.

                Business Rules:
                - Username must exist.
                - Password must match.
                - Account must be active.
                - Account must not be locked.

                Returned Information:
                - Account information
                - Roles
                - Permissions
                - JWT access token
                """
)

@ApiResponse(
        responseCode = "200",
        description = "Authentication successful",
        content = @Content(
                schema = @Schema(
                        implementation = AccountLoginApiResponseSchema.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Login Successful",
                                summary = "Authenticated successfully and received JWT token",
                                value = AccountLoginExamples.SUCCESS_RESPONSE
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
                                name = "Username Required",
                                summary = "Username is mandatory",
                                value = AccountLoginExamples.USERNAME_REQUIRED
                        ),

                        @ExampleObject(
                                name = "Password Required",
                                summary = "Password is mandatory",
                                value = AccountLoginExamples.PASSWORD_REQUIRED
                        ),

                        @ExampleObject(
                                name = "Multiple Validation Errors",
                                summary = "Request contains multiple validation violations",
                                value = AccountLoginExamples.MULTIPLE_VALIDATION_ERRORS
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
                                summary = "Username or password is incorrect",
                                value = AccountLoginExamples.INVALID_CREDENTIALS
                        ),

                        @ExampleObject(
                                name = "Account Inactive",
                                summary = "Account exists but is not active",
                                value = AccountLoginExamples.ACCOUNT_INACTIVE
                        ),

                        @ExampleObject(
                                name = "Account Locked",
                                summary = "Account exists but is locked",
                                value = AccountLoginExamples.ACCOUNT_LOCKED
                        )
                }
        )
)

@InternalServerErrorApiDocs
public @interface AccountLoginApiDocs {
}
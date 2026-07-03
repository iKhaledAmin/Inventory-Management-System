package com.khaledamin.ims.auth.account.api.documentation.annotations;

import com.khaledamin.ims.auth.account.api.documentation.examples.AccountRegistrationExamples;
import com.khaledamin.ims.auth.account.api.documentation.schema.AccountRegistrationApiResponseSchema;
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
        summary = "Register Account",
        description = """
                Creates a new account and initiates the account activation process.

                Registration Flow:
                - Creates a new account.
                - Creates the account profile.
                - Generates an activation code.
                - Sends an activation email.
                - Account remains pending until activation is completed.

                Business Rules:
                - Username must be unique.
                - Email address must be unique.
                - Password must satisfy password policy.
                - Username must satisfy username policy.
                - Email address must be valid.

                Next Step:
                - Use the account activation API to activate the account.
                """
)

@ApiResponse(
        responseCode = "201",
        description = "Account registered successfully",
        content = @Content(
                schema = @Schema(
                        implementation = AccountRegistrationApiResponseSchema.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Account Registered",
                                summary = "Account created successfully and awaiting activation",
                                value = AccountRegistrationExamples.SUCCESS
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
                                name = "Invalid Username",
                                summary = "Username violates validation rules",
                                value = AccountRegistrationExamples.INVALID_USERNAME
                        ),

                        @ExampleObject(
                                name = "Invalid Password",
                                summary = "Password violates security policy",
                                value = AccountRegistrationExamples.INVALID_PASSWORD
                        ),

                        @ExampleObject(
                                name = "Invalid Email Address",
                                summary = "Email address format is invalid",
                                value = AccountRegistrationExamples.INVALID_EMAIL
                        ),

                        @ExampleObject(
                                name = "Multiple Validation Errors",
                                summary = "Request contains multiple validation violations",
                                value = AccountRegistrationExamples.MULTIPLE_VALIDATION_ERRORS
                        )
                }
        )
)

@ApiResponse(
        responseCode = "409",
        description = "Business rule violation",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {

                        @ExampleObject(
                                name = "Username Already Exists",
                                summary = "Username uniqueness violation",
                                value = AccountRegistrationExamples.USERNAME_ALREADY_EXISTS
                        ),

                        @ExampleObject(
                                name = "Email Already Exists",
                                summary = "Email uniqueness violation",
                                value = AccountRegistrationExamples.EMAIL_ALREADY_EXISTS
                        )
                }
        )
)

@InternalServerErrorApiDocs
public @interface AccountRegistrationApiDocs {
}
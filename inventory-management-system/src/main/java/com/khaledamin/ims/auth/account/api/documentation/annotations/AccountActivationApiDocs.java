package com.khaledamin.ims.auth.account.api.documentation.annotations;


import com.khaledamin.ims.auth.account.api.documentation.examples.AccountActivationExamples;
import com.khaledamin.ims.auth.account.api.documentation.schema.AccountActivationApiResponseSchema;
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
        summary = "Activate Account",
        description = """
                Activates a previously registered account.

                Business Flow:
                - User submits email address and token activation code.
                - System validates the activation token.
                - System verifies token ownership.
                - System activates the account.
                - Activated account becomes eligible for login.

                Business Rules:
                - Activation code must be valid.
                - Activation code must not be expired.
                - Activation code must belong to the provided account.
                - Account must exist.
                """
)

@ApiResponse(
        responseCode = "200",
        description = "Account activated successfully",
        content = @Content(
                schema = @Schema(
                        implementation = AccountActivationApiResponseSchema.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Account Activated",
                                summary = "Successful account activation",
                                value = AccountActivationExamples.SUCCESS_RESPONSE
                        )
                }
        )
)

@ApiResponse(
        responseCode = "400",
        description = "Validation failed or activation failed",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {

                        @ExampleObject(
                                name = "Missing Activation Code",
                                summary = "Activation code is required",
                                value = AccountActivationExamples.MISSING_ACTIVATION_CODE
                        ),

                        @ExampleObject(
                                name = "Invalid Email Address",
                                summary = "Email address format is invalid",
                                value = AccountActivationExamples.INVALID_EMAIL_ADDRESS
                        ),

                        @ExampleObject(
                                name = "Multiple Validation Errors",
                                summary = "Request contains multiple validation violations",
                                value = AccountActivationExamples.MULTIPLE_VALIDATION_ERRORS
                        ),

                        @ExampleObject(
                                name = "Invalid Token",
                                summary = "Activation token is invalid",
                                value = AccountActivationExamples.INVALID_TOKEN
                        ),

                        @ExampleObject(
                                name = "Token Expired",
                                summary = "Activation token has expired",
                                value = AccountActivationExamples.TOKEN_EXPIRED
                        ),

                        @ExampleObject(
                                name = "Token Already Used",
                                summary = "Activation token has already been consumed",
                                value = AccountActivationExamples.TOKEN_ALREADY_USED
                        )

                }
        )
)

@InternalServerErrorApiDocs
public @interface AccountActivationApiDocs {
}
package com.khaledamin.ims.auth.account.api.documentation.annotations;


import com.khaledamin.ims.auth.account.api.documentation.examples.AccountConfirmResetPasswordExamples;
import com.khaledamin.ims.auth.account.api.documentation.schema.AccountApiActionResponseSchema;
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
        summary = "Confirm Password Reset",
        description = """
                Completes the password reset process.

                Flow:
                - Validates the reset password token.
                - Verifies token ownership.
                - Verifies token type.
                - Verifies token expiration status.
                - Updates account password.
                - Marks the token as consumed.

                Business Rules:
                - Email address must belong to an existing account.
                - Reset token must be valid.
                - Reset token must not be expired.
                - Reset token must not be previously used.
                - Password must satisfy password policy.
                """
)

@ApiResponse(
        responseCode = "200",
        description = "Password reset completed successfully",
        content = @Content(
                schema = @Schema(
                        implementation = AccountApiActionResponseSchema.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Password Reset Completed",
                                summary = "Password updated successfully",
                                value = AccountConfirmResetPasswordExamples.SUCCESS_RESPONSE
                        )
                }
        )
)

@ApiResponse(
        responseCode = "400",
        description = "Validation or reset password failure",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {

                        @ExampleObject(
                                name = "Password Required",
                                summary = "Password is mandatory",
                                value = AccountConfirmResetPasswordExamples.PASSWORD_REQUIRED
                        ),

                        @ExampleObject(
                                name = "Invalid Password Format",
                                summary = "Password violates password policy",
                                value = AccountConfirmResetPasswordExamples.INVALID_PASSWORD_FORMAT
                        ),

                        @ExampleObject(
                                name = "Multiple Validation Errors",
                                summary = "Multiple validation errors",
                                value = AccountConfirmResetPasswordExamples.MULTIPLE_VALIDATION_ERRORS
                        ),

                        @ExampleObject(
                                name = "Invalid Token",
                                summary = "Reset password token is invalid",
                                value = AccountConfirmResetPasswordExamples.INVALID_TOKEN
                        ),

                        @ExampleObject(
                                name = "Token Expired",
                                summary = "Reset password token has expired",
                                value = AccountConfirmResetPasswordExamples.TOKEN_EXPIRED
                        ),

                        @ExampleObject(
                                name = "Token Already Used",
                                summary = "Reset password token has already been consumed",
                                value = AccountConfirmResetPasswordExamples.TOKEN_ALREADY_USED
                        )

                }
        )
)

@ApiResponse(
        responseCode = "409",
        description = "Verification token already used",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {

                        @ExampleObject(
                                name = "Token Already Used",
                                summary = "Reset password token has already been consumed",
                                value = AccountConfirmResetPasswordExamples.TOKEN_ALREADY_USED
                        )
                }
        )
)

@InternalServerErrorApiDocs
public @interface AccountConfirmResetPasswordApiDocs {
}
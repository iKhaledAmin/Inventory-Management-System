package com.khaledamin.ims.auth.account.api.documentation.annotations;

import com.khaledamin.ims.auth.account.api.documentation.examples.AccountResetPasswordRequestExamples;
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
        summary = "Request Password Reset",
        description = """
                Initiates the password reset process.

                Flow:
                - Accepts an email address.
                - If an account exists, a reset password verification code is generated.
                - A reset password email is sent to the account owner.
                - Returns the same response regardless of whether the account exists.

                Security Behavior:
                - Prevents account enumeration.
                - Does not reveal whether the email address is registered.
                - Always returns a successful response when validation passes.

                Business Rules:
                - Email address must be valid.
                """
)

@ApiResponse(
        responseCode = "200",
        description = "Password reset request accepted",
        content = @Content(
                schema = @Schema(
                        implementation = AccountApiActionResponseSchema.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Request Accepted",
                                summary = "Reset password process initiated",
                                value = AccountResetPasswordRequestExamples.SUCCESS_RESPONSE
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
                                name = "Invalid Email Format",
                                summary = "Email address format is invalid",
                                value = AccountResetPasswordRequestExamples.INVALID_EMAIL_FORMAT
                        )
                }
        )
)

@InternalServerErrorApiDocs
public @interface AccountResetPasswordRequestApiDocs {
}
package com.khaledamin.ims.identity.account.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.annotations.UnauthorizedApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthenticatedApiDocs;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
import com.khaledamin.ims.identity.account.api.documentation.examples.AccountUpdateExamples;
import com.khaledamin.ims.identity.account.api.documentation.schema.AccountApiResponseSchema;
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
        summary = "Update My Account",
        description = """
                Updates the currently authenticated account.

                Required Authority:
                - account_update

                Business Rules:
                - Email address must be unique.
                - Username must satisfy validation rules.
                - Password must satisfy password policy.
                - Birth date must be in the past.

                Update Behavior:
                - All fields are optional.
                - Only provided fields are updated.
                - Omitted fields remain unchanged.

                Updatable Fields:
                - email address
                - username
                - password
                - gender
                - birth date
                - phone number
                - profession
                - profile image
                """
)

@ApiResponse(
        responseCode = "200",
        description = "Account updated successfully",
        content = @Content(
                schema = @Schema(
                        implementation = AccountApiResponseSchema.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Partial Account Updated",
                                summary = "Successful account update without profile image",
                                value = AccountUpdateExamples.SUCCESS_SHORT_RESPONSE
                        ),
                        @ExampleObject(
                                name = "Full Account Updated",
                                summary = "Successful account update with profile image",
                                value = AccountUpdateExamples.SUCCESS_FULL_RESPONSE
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
                                name = "Invalid Phone Number",
                                summary = "Phone number format is invalid",
                                value = AccountUpdateExamples.INVALID_PHONE_NUMBER
                        ),

                        @ExampleObject(
                                name = "Invalid Birth Date",
                                summary = "Birth date must be in the past",
                                value = AccountUpdateExamples.INVALID_BIRTH_DATE
                        ),

                        @ExampleObject(
                                name = "Multiple Validation Errors",
                                summary = "Request contains multiple validation violations",
                                value = AccountUpdateExamples.MULTIPLE_VALIDATION_ERRORS
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
                                name = "Email Already Exists",
                                summary = "Email uniqueness violation",
                                value = AccountUpdateExamples.EMAIL_ALREADY_EXISTS
                        )
                }
        )
)

@UnauthenticatedApiDocs
@UnauthorizedApiDocs
@InternalServerErrorApiDocs
public @interface AccountUpdateApiDocs {
}
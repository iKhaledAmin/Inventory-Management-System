package com.khaledamin.ims.identity.account.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.annotations.UnauthorizedApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthenticatedApiDocs;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
import com.khaledamin.ims.identity.account.api.documentation.examples.AccountViewExamples;
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
        summary = "View My Account",
        description = """
                Retrieves the currently authenticated account.

                Required Authority:
                - account_read

                Returns:
                - Account code
                - Username
                - Email address
                - Account status
                - Profile information
                - Profile image
                """
)

@ApiResponse(
        responseCode = "200",
        description = "Account retrieved successfully",
        content = @Content(
                schema = @Schema(
                        implementation = AccountApiResponseSchema.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Partial Account Retrieved",
                                summary = "Successful account retrieval without profile image",
                                value = AccountViewExamples.SUCCESS_SHORT_RESPONSE
                        ),
                        @ExampleObject(
                                name = "Full Account Retrieved",
                                summary = "Successful account retrieval with profile image",
                                value = AccountViewExamples.SUCCESS_FULL_RESPONSE
                        )
                }
        )
)


@UnauthenticatedApiDocs
@UnauthorizedApiDocs
@InternalServerErrorApiDocs
public @interface AccountViewApiDocs {
}
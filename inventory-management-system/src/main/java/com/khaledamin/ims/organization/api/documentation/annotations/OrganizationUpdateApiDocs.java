package com.khaledamin.ims.organization.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.annotations.UnauthorizedApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthenticatedApiDocs;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
import com.khaledamin.ims.organization.api.documentation.examples.OrganizationUpdateExamples;
import com.khaledamin.ims.organization.api.documentation.schema.OrganizationApiResponseSchema;
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
        summary = "Update My Organization",
        description = """
                Updates the organization owned by the currently authenticated account.

                Required Authority:
                - organization_update

                Update Behavior:
                - All fields are optional.
                - Only provided fields are updated.
                - Omitted fields remain unchanged.

                Updatable Fields:
                - organization name
                - organization description
                - organization image
                """
)

@ApiResponse(
        responseCode = "200",
        description = "Organization updated successfully",
        content = @Content(
                schema = @Schema(
                        implementation = OrganizationApiResponseSchema.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Partial Organization Updated",
                                summary = "Successful organization update without image",
                                value = OrganizationUpdateExamples.SUCCESS_SHORT_RESPONSE
                        ),
                        @ExampleObject(
                                name = "Full Organization Updated",
                                summary = "Successful organization update with image",
                                value = OrganizationUpdateExamples.SUCCESS_FULL_RESPONSE
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
                                name = "Invalid Name",
                                summary = "Organization name validation failed",
                                value = OrganizationUpdateExamples.INVALID_NAME
                        ),

                        @ExampleObject(
                                name = "Invalid Description",
                                summary = "Organization description validation failed",
                                value = OrganizationUpdateExamples.INVALID_DESCRIPTION
                        ),

                        @ExampleObject(
                                name = "Multiple Validation Errors",
                                summary = "Request contains multiple validation violations",
                                value = OrganizationUpdateExamples.MULTIPLE_VALIDATION_ERRORS
                        )
                }
        )
)

@UnauthenticatedApiDocs
@UnauthorizedApiDocs
@InternalServerErrorApiDocs
public @interface OrganizationUpdateApiDocs {
}
package com.khaledamin.ims.organization.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthenticatedApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthorizedApiDocs;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
import com.khaledamin.ims.organization.api.documentation.examples.OrganizationSettingsUpdateExamples;
import com.khaledamin.ims.organization.api.documentation.schema.OrganizationSettingsApiResponseSchema;
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
        summary = "Update Organization Settings",
        description = """
                Updates operational settings of the organization owned by
                the currently authenticated account.

                Required Authority:
                - organization_update_settings

                Update Behavior:
                - All fields are optional.
                - Only provided fields are updated.
                - Omitted fields remain unchanged.

                Updatable Settings:
                - Reservation expiration duration
                - Stock allocation strategy

                Business Notes:
                - Reservation expiration controls how long reservations remain active.
                - Allocation strategy determines how inventory batches are selected
                  during stock reservation operations.
                """
)

@ApiResponse(
        responseCode = "200",
        description = "Organization settings updated successfully",
        content = @Content(
                schema = @Schema(
                        implementation = OrganizationSettingsApiResponseSchema.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Settings Updated",
                                summary = "Organization settings updated successfully",
                                value = OrganizationSettingsUpdateExamples.SUCCESS_RESPONSE
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
                                name = "Invalid Expiration Minutes",
                                summary = "Expiration minutes validation failed",
                                value = OrganizationSettingsUpdateExamples.INVALID_EXPIRATION_MINUTES
                        ),

                        @ExampleObject(
                                name = "Multiple Validation Errors",
                                summary = "Request contains multiple validation violations",
                                value = OrganizationSettingsUpdateExamples.MULTIPLE_VALIDATION_ERRORS
                        )
                }
        )
)

@UnauthenticatedApiDocs
@UnauthorizedApiDocs
@InternalServerErrorApiDocs
public @interface OrganizationSettingsUpdateApiDocs {
}
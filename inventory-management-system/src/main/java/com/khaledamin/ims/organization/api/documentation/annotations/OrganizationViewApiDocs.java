package com.khaledamin.ims.organization.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.annotations.ForbiddenApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.InternalServerErrorApiDocs;
import com.khaledamin.ims.core.api.documentation.annotations.UnauthorizedApiDocs;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
import com.khaledamin.ims.organization.api.documentation.examples.OrganizationViewExamples;
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
        summary = "View My Organization",
        description = """
                Retrieves the organization owned by the currently authenticated account.

                Required Authority:
                - organization_read

                Returns:
                - Organization code
                - Organization name
                - Organization description
                - Organization status
                - Organization image
                """
)

@ApiResponse(
        responseCode = "200",
        description = "Organization retrieved successfully",
        content = @Content(
                schema = @Schema(
                        implementation = OrganizationApiResponseSchema.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Organization Without Image",
                                summary = "Successful organization retrieval without image",
                                value = OrganizationViewExamples.SUCCESS_SHORT_RESPONSE
                        ),
                        @ExampleObject(
                                name = "Organization With Image",
                                summary = "Successful organization retrieval with image variants",
                                value = OrganizationViewExamples.SUCCESS_FULL_RESPONSE
                        )
                }
        )
)

@ApiResponse(
        responseCode = "404",
        description = "Organization not found",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Organization Not Found",
                                summary = "Authenticated account does not own an organization",
                                value = OrganizationViewExamples.ORGANIZATION_NOT_FOUND
                        )
                }
        )
)

@UnauthorizedApiDocs
@ForbiddenApiDocs
@InternalServerErrorApiDocs
public @interface OrganizationViewApiDocs {
}
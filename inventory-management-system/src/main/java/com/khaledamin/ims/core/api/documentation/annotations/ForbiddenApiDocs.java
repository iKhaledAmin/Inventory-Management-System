package com.khaledamin.ims.core.api.documentation.annotations;


import com.khaledamin.ims.core.api.documentation.examples.ForbiddenExamples;
import com.khaledamin.ims.core.api.response.ApiErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@io.swagger.v3.oas.annotations.responses.ApiResponse(
        responseCode = "403",
        description = "Access denied",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {
                        @ExampleObject(
                                name = "Access Denied",
                                summary = "Authenticated user does not have sufficient permissions",
                                value = ForbiddenExamples.FORBIDDEN
                        )
                }
        )
)
public @interface ForbiddenApiDocs {
}
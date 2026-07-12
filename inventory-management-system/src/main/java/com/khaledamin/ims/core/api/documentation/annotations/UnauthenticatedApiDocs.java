package com.khaledamin.ims.core.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.examples.UnauthenticatedExamples;
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
        responseCode = "401",
        description = "Authentication required or authentication failed",
        content = @Content(
                schema = @Schema(
                        implementation = ApiErrorResponse.class
                ),
                examples = {

                        @ExampleObject(
                                name = "Token Invalid",
                                summary = "Token invalid",
                                value = UnauthenticatedExamples.TOKEN_INVALID
                        ),


                        @ExampleObject(
                                name = "Token Expired",
                                summary = "Token expired",
                                value = UnauthenticatedExamples.TOKEN_EXPIRED
                        ),

                        @ExampleObject(
                                name = "Principal Locked",
                                summary = "Authenticated principal is locked",
                                value = UnauthenticatedExamples.PRINCIPAL_LOCKED
                        ),

                        @ExampleObject(
                                name = "Principal Inactive",
                                summary = "Authenticated principal is inactive",
                                value = UnauthenticatedExamples.PRINCIPAL_INACTIVE
                        )
                }
        )
)
public @interface UnauthenticatedApiDocs {
}
package com.khaledamin.ims.core.api.documentation.annotations;

import com.khaledamin.ims.core.api.documentation.examples.UnauthorizedExamples;
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
                                name = "Missing Token",
                                summary = "Authentication token was not provided",
                                value = UnauthorizedExamples.TOKEN_MISSING
                        ),

                        @ExampleObject(
                                name = "Expired Token",
                                summary = "JWT token has expired",
                                value = UnauthorizedExamples.TOKEN_EXPIRED
                        ),

                        @ExampleObject(
                                name = "Invalid Token",
                                summary = "Token validation failed",
                                value = UnauthorizedExamples.TOKEN_INVALID
                        ),

                        @ExampleObject(
                                name = "Malformed Token",
                                summary = "Token structure is invalid",
                                value = UnauthorizedExamples.TOKEN_MALFORMED
                        ),

                        @ExampleObject(
                                name = "Invalid Signature",
                                summary = "Token signature validation failed",
                                value = UnauthorizedExamples.TOKEN_SIGNATURE_INVALID
                        ),

                        @ExampleObject(
                                name = "Principal Locked",
                                summary = "Authenticated principal is locked",
                                value = UnauthorizedExamples.PRINCIPAL_LOCKED
                        ),

                        @ExampleObject(
                                name = "Principal Inactive",
                                summary = "Authenticated principal is inactive",
                                value = UnauthorizedExamples.PRINCIPAL_INACTIVE
                        )
                }
        )
)
public @interface UnauthorizedApiDocs {
}
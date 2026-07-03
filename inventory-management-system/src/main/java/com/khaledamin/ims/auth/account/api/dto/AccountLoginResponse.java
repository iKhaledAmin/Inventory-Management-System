package com.khaledamin.ims.auth.account.api.dto;

import com.khaledamin.ims.auth.security.jwt.JwtResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@Schema(
        name = "AccountLoginResponse",
        description = "Authenticated account information and access token"
)
public class AccountLoginResponse {

    @Schema(
            description = "Authenticated account information"
    )
    @JsonProperty("account_info")
    private AccountInfo account;


    @Schema(
            description = "JWT token details"
    )
    @JsonProperty("token_info")
    private JwtResponse token;




    @Getter
    @SuperBuilder
    @Schema(
            name = "AccountLoginInfo",
            description = "Authenticated account details"
    )
    public static class AccountInfo {

        @Schema(
                example = "khaled-amin",
                description = "Account username"
        )
        @JsonProperty("username")
        private String username;

        @Schema(
                example = "ACC-01JY8A7R4W7KX2N8QF5M6P9T3",
                description = "Account business identifier"
        )
        @JsonProperty("account_code")
        private String accountCode;

        @Schema(
                example = "ACTIVE",
                description = "Current account status"
        )
        @JsonProperty("account_status")
        private String accountStatus;

        @Schema(
                example = "[\"CUSTOMER\", \"SELLER\"]",
                description = "Assigned account roles"
        )
        @JsonProperty("roles")
        private List<String> roles;

        @Schema(
                example = "[\"category_read\", \"cart_add_item\", \"cart_read\"]",
                description = "Granted account permissions"
        )
        @JsonProperty("permissions")
        private List<String> permissions;
    }
}
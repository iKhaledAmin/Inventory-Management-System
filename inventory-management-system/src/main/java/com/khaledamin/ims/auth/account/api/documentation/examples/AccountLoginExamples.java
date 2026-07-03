package com.khaledamin.ims.auth.account.api.documentation.examples;

public final class AccountLoginExamples {

    private AccountLoginExamples() {
    }

    public static final String SUCCESS_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "data": {
            "account_info": {
              "username": "khaled-amin",
              "account_code": "ACC-01K123ABC456DEF789GHIJKL",
              "account_status": "ACTIVE",
              "roles": [
                "USER",
                "CUSTOMER"
              ],
              "permissions": [
                "account_read",
                "account_update",
                "order_create"
              ]
            },
            "token_info": {
              "access_token": "eyJhbGciOiJIUzI1NiJ9...",
              "token_type": "Bearer",
              "expires_in": 86400
            }
          }
        }
        """;

    public static final String USERNAME_REQUIRED = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/auth/accounts/login",
            "details": {
              "username": [
                "Username is mandatory"
              ]
            }
          }
        }
        """;

    public static final String PASSWORD_REQUIRED = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/auth/accounts/login",
            "details": {
              "password": [
                "Password is mandatory"
              ]
            }
          }
        }
        """;

    public static final String MULTIPLE_VALIDATION_ERRORS = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/auth/accounts/login",
            "details": {
              "username": [
                "Username is mandatory"
              ],
              "password": [
                "Password is mandatory"
              ]
            }
          }
        }
        """;

    public static final String INVALID_CREDENTIALS = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 401,
            "code": "SECURITY_CREDENTIALS_INVALID",
            "message": "Invalid username or password",
            "path": "/auth/accounts/login",
            "details": {}
          }
        }
        """;

    public static final String ACCOUNT_INACTIVE = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 401,
            "code": "SECURITY_PRINCIPAL_INACTIVE",
            "message": "Account is inactive",
            "path": "/auth/accounts/login",
            "details": {}
          }
        }
        """;

    public static final String ACCOUNT_LOCKED = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 401,
            "code": "SECURITY_PRINCIPAL_LOCKED",
            "message": "Account is locked",
            "path": "/auth/accounts/login",
            "details": {}
          }
        }
        """;
}
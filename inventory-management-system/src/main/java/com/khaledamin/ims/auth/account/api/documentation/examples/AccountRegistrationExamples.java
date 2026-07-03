package com.khaledamin.ims.auth.account.api.documentation.examples;

public class AccountRegistrationExamples {
    private AccountRegistrationExamples() {
    }

    public static final String SUCCESS = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "data": {
            "email_address": "khaled-amin@example.com",
            "status": "PENDING_ACTIVATION"
          }
        }
        """;

    public static final String INVALID_USERNAME = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/auth/accounts/register",
            "details": {
              "username": [
                "Username format is invalid"
              ]
            }
          }
        }
        """;

    public static final String INVALID_PASSWORD = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/auth/accounts/register",
            "details": {
              "password": [
                "Password must contain uppercase, lowercase, digit and special character"
              ]
            }
          }
        }
        """;

    public static final String INVALID_EMAIL = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/auth/accounts/register",
            "details": {
              "emailAddress": [
                "Email address format is invalid"
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
            "path": "/auth/accounts/register",
            "details": {
              "username": [
                "Username format is invalid"
              ],
              "password": [
                "Password must contain uppercase, lowercase, digit and special character"
              ],
              "emailAddress": [
                "Email address format is invalid"
              ]
            }
          }
        }
        """;

    public static final String USERNAME_ALREADY_EXISTS = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 409,
            "code": "ACCOUNT_USERNAME_ALREADY_EXISTS",
            "message": "Account username already exists",
            "path": "/auth/accounts/register",
            "details": {
              "username": "khaled-amin"
            }
          }
        }
        """;

    public static final String EMAIL_ALREADY_EXISTS = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 409,
            "code": "ACCOUNT_EMAIL_ALREADY_EXISTS",
            "message": "Account email already exists",
            "path": "/auth/accounts/register",
            "details": {
              "emailAddress": "khaled-amin@example.com"
            }
          }
        }
        """;
}

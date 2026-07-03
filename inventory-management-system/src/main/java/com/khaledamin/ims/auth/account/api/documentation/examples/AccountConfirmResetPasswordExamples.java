package com.khaledamin.ims.auth.account.api.documentation.examples;

public final class AccountConfirmResetPasswordExamples {

    private AccountConfirmResetPasswordExamples() {
    }

    public static final String SUCCESS_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "data": {
            "message": "Your password has been reset successfully."
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
            "path": "/auth/accounts/reset-password-confirm",
            "details": {
              "password": [
                "Password must is mandatory"
              ]
            }
          }
        }
        """;

    public static final String INVALID_PASSWORD_FORMAT = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/auth/accounts/reset-password-confirm",
            "details": {
              "password": [
                "Password must contain uppercase, lowercase, digit and special character"
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
            "path": "/auth/accounts/activate",
            "details": {
              "password": [
                "Password must contain uppercase, lowercase, digit and special character"
              ],
              "reset_code": [
                "Reset code is mandatory"
              ]
            }
          }
        }
        """;



    public static final String INVALID_TOKEN = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 400,
            "code": "AUTH_RESET_PASSWORD_FAILED",
            "message": "Reset password failed",
            "path": "/auth/accounts/reset-password-confirm",
            "details": {
              "reason": "Invalid token"
            }
          }
        }
        """;


    public static final String TOKEN_EXPIRED = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 400,
            "code": "AUTH_RESET_PASSWORD_FAILED",
            "message": "Reset password failed",
            "path": "/auth/accounts/reset-password-confirm",
            "details": {
              "reason": "Token expired"
            }
          }
        }
        """;

    public static final String TOKEN_ALREADY_USED = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 400,
            "code": "AUTH_RESET_PASSWORD_FAILED",
            "message": "Reset password failed",
            "path": "/auth/accounts/reset-password-confirm",
            "details": {
              "reason": "Token already used"
            }
          }
        }
        """;

}
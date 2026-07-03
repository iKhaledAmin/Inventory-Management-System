package com.khaledamin.ims.auth.account.api.documentation.examples;

public final class AccountResetPasswordRequestExamples {

    private AccountResetPasswordRequestExamples() {
    }

    public static final String SUCCESS_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "data": {
            "message": "If an account exists for this email address,you will receive a reset password email."
          }
        }
        """;

    public static final String INVALID_EMAIL_FORMAT = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/auth/accounts/reset-password-request",
            "details": {
              "emailAddress": [
                "Invalid email address"
              ]
            }
          }
        }
        """;

}
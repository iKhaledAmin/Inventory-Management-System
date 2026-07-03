package com.khaledamin.ims.auth.account.api.documentation.examples;

public final class AccountActivationExamples {

    private AccountActivationExamples() {
    }

    public static final String SUCCESS_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "data": {
            "email_address": "khaled-amin@example.com",
            "account_status": "ACTIVE"
          }
        }
        """;

    public static final String INVALID_EMAIL_ADDRESS = """
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
              "emailAddress": [
                "Invalid email address"
              ]
            }
          }
        }
        """;

    public static final String MISSING_ACTIVATION_CODE = """
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
              "activationCode": [
                "Activation code is mandatory"
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
              "activationCode": [
                "Activation code is mandatory"
              ],
              "emailAddress": [
                "Invalid email address"
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
            "code": "AUTH_ACTIVATION_FAILED",
            "message": "Activation failed",
            "path": "/auth/accounts/activate",
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
            "code": "AUTH_ACTIVATION_FAILED",
            "message": "Activation failed",
            "path": "/auth/accounts/activate",
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
            "status": 409,
            "code": "AUTH_ACTIVATION_FAILED",
            "message": "Activation failed",
            "path": "/auth/accounts/activate",
            "details": {
              "reason": "Token already used"
            }
          }
        }
        """;

}
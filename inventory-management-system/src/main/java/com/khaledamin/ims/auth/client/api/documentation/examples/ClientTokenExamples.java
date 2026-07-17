package com.khaledamin.ims.auth.client.api.documentation.examples;

public final class ClientTokenExamples {

    private ClientTokenExamples() {
    }

    public static final String SUCCESS_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "data": {
            "client_info": {
              "client_id": "ecommerce-service",
              "client_code": "CLI-01KABC123XYZ456PQR789LMN",
              "authorities": [
                "stock_validate_existence",
                "stock_reserve",
                "stock_release"
              ]
            },
            "token_info": {
              "access_token": "eyJhbGciOiJIUzI1NiJ9...",
              "token_type": "Bearer",
              "expires_at": "2026-07-06T10:12:47Z",
              "expires_in": 1798
            }
          }
        }
        """;

    public static final String CLIENT_ID_REQUIRED = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/auth/clients/token",
            "details": {
              "client_id": [
                "Client ID is mandatory"
              ]
            }
          }
        }
        """;

    public static final String CLIENT_SECRET_REQUIRED = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/auth/clients/token",
            "details": {
              "client_secret": [
                "Client secret is mandatory"
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
            "path": "/auth/clients/token",
            "details": {
              "client_id": [
                "Client ID is mandatory"
              ],
              "client_secret": [
                "Client secret is mandatory"
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
            "message": "Invalid credentials",
            "path": "/auth/clients/token",
            "details": {
                  "reason": "Invalid client id or client secret"
                }
          }
        }
        """;

    public static final String CLIENT_INACTIVE = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 401,
            "code": "SECURITY_PRINCIPAL_INACTIVE",
            "message": "Client is not active",
            "path": "/auth/clients/token",
            "details": {}
          }
        }
        """;

    public static final String CLIENT_LOCKED = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 401,
            "code": "SECURITY_PRINCIPAL_LOCKED",
            "message": "Client is locked",
            "path": "/auth/clients/token",
            "details": {}
          }
        }
        """;
}
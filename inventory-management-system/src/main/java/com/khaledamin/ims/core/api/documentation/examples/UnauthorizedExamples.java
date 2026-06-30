package com.khaledamin.ims.core.api.documentation.examples;

public final class UnauthorizedExamples {

    private UnauthorizedExamples() {
    }

    public static final String TOKEN_MISSING = """
        {
          "meta": {
            "request_id": "01KVGQBAB77C3WDT00CKM95Y6V",
            "timestamp": "2026-06-19T22:57:25.252445"
          },
          "error": {
            "status": 401,
            "code": "SECURITY_TOKEN_MISSING",
            "message": "Authentication token is missing",
            "details": {},
            "path": "<path>"
          }
        }
        """;

    public static final String TOKEN_EXPIRED = """
        {
          "meta": {
            "request_id": "01KVGQBAB77C3WDT00CKM95Y6V",
            "timestamp": "2026-06-19T22:57:25.252445"
          },
          "error": {
            "status": 401,
            "code": "SECURITY_TOKEN_EXPIRED",
            "message": "Token expired",
            "details": {},
            "path": "<path>"
          }
        }
        """;

    public static final String TOKEN_INVALID = """
        {
          "meta": {
            "request_id": "01KVGQBAB77C3WDT00CKM95Y6V",
            "timestamp": "2026-06-19T22:57:25.252445"
          },
          "error": {
            "status": 401,
            "code": "SECURITY_TOKEN_INVALID",
            "message": "Invalid token",
            "details": {},
            "path": "<path>"
          }
        }
        """;

    public static final String TOKEN_MALFORMED = """
        {
          "meta": {
            "request_id": "01KVGQBAB77C3WDT00CKM95Y6V",
            "timestamp": "2026-06-19T22:57:25.252445"
          },
          "error": {
            "status": 401,
            "code": "SECURITY_TOKEN_MALFORMED",
            "message": "Malformed token",
            "details": {},
            "path": "<path>"
          }
        }
        """;

    public static final String TOKEN_SIGNATURE_INVALID = """
        {
          "meta": {
            "request_id": "01KVGQBAB77C3WDT00CKM95Y6V",
            "timestamp": "2026-06-19T22:57:25.252445"
          },
          "error": {
            "status": 401,
            "code": "SECURITY_TOKEN_SIGNATURE_INVALID",
            "message": "Invalid token signature",
            "details": {},
            "path": "<path>"
          }
        }
        """;

    public static final String PRINCIPAL_LOCKED = """
        {
          "meta": {
            "request_id": "01KVGQBAB77C3WDT00CKM95Y6V",
            "timestamp": "2026-06-19T22:57:25.252445"
          },
          "error": {
            "status": 401,
            "code": "SECURITY_PRINCIPAL_LOCKED",
            "message": "Principal is locked",
            "details": {},
            "path": "<path>"
          }
        }
        """;

    public static final String PRINCIPAL_INACTIVE = """
        {
          "meta": {
            "request_id": "01KVGQBAB77C3WDT00CKM95Y6V",
            "timestamp": "2026-06-19T22:57:25.252445"
          },
          "error": {
            "status": 401,
            "code": "SECURITY_PRINCIPAL_INACTIVE",
            "message": "Principal is inactive",
            "details": {},
            "path": "<path>"
          }
        }
        """;
}
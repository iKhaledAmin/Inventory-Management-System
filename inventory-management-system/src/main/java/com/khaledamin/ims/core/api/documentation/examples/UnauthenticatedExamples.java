package com.khaledamin.ims.core.api.documentation.examples;

public final class UnauthenticatedExamples {

    private UnauthenticatedExamples() {
    }


    public static final String TOKEN_INVALID = """
        {
          "meta": {
            "request_id": "01KVGQBAB77C3WDT00CKM95Y6V",
            "timestamp": "2026-06-19T22:57:25.252445"
          },
          "error": {
            "status": 401,
            "code": "SECURITY_TOKEN_INVALID",
            "message": "Token invalid",
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
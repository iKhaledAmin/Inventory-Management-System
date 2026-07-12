package com.khaledamin.ims.core.api.documentation.examples;

public final class UnauthorizedExamples {

    private UnauthorizedExamples() {
    }

    public static final String FORBIDDEN = """
        {
          "meta": {
            "request_id": "01KVGQBAB77C3WDT00CKM95Y6V",
            "timestamp": "2026-06-19T22:57:25.252445"
          },
          "error": {
            "status": 403,
            "code": "SECURITY_ACCESS_DENIED",
            "message": "Access denied",
            "details": {},
            "path": "<path>"
          }
        }
        """;
}
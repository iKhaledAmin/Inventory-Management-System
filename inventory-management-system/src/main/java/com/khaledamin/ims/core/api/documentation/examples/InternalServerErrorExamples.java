package com.khaledamin.ims.core.api.documentation.examples;

public final class InternalServerErrorExamples {

    private InternalServerErrorExamples() {
    }

    public static final String INTERNAL_SERVER_ERROR = """
        {
          "meta": {
            "request_id": "01KVGQBAB77C3WDT00CKM95Y6V",
            "timestamp": "2026-06-19T22:57:25.252445"
          },
          "error": {
            "status": 500,
            "code": "INTERNAL_SERVER_ERROR",
            "message": "Internal Server Error",
            "details": {},
            "path": "<path>"
          }
        }
        """;
}
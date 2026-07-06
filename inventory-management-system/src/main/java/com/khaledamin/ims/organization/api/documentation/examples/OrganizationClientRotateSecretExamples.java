package com.khaledamin.ims.organization.api.documentation.examples;

public final class OrganizationClientRotateSecretExamples {

    private OrganizationClientRotateSecretExamples() {
    }

    public static final String SUCCESS_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-07-06T14:30:00",
            "request_id": "01KROTATE123ABC456DEF789GHI"
          },
          "data": {
            "client_id": "CLI-01JY8A7R4W7KX2N8QF5M6P9T3",
            "client_secret": "ims_3Yf8KqL2N9xP7Vb"
          }
        }
        """;

    public static final String CLIENT_NOT_FOUND = """
        {
          "meta": {
            "timestamp": "2026-07-06T14:30:00",
            "request_id": "01KROTATE123ABC456DEF789GHI"
          },
          "error": {
            "status": 404,
            "code": "CLIENT_NOT_FOUND",
            "message": "Client not found",
            "path": "/organization/client/CLI-01KABC123DEF456GHI789JKL/rotate-secret",
            "details": {}
          }
        }
        """;

    public static final String ROTATE_SECRET_FORBIDDEN = """
        {
          "meta": {
            "timestamp": "2026-07-06T14:30:00",
            "request_id": "01KROTATE123ABC456DEF789GHI"
          },
          "error": {
            "status": 403,
            "code": "ORGANIZATION_ROTATE_CLIENT_SECRET_FORBIDDEN",
            "message": "Rotate client secret is forbidden",
            "path": "/organization/client/CLI-01KABC123DEF456GHI789JKL/rotate-secret",
            "details": {}
          }
        }
        """;
}
package com.khaledamin.ims.organization.api.documentation.examples;

public final class OrganizationClientCreateExamples {

    private OrganizationClientCreateExamples() {
    }

    public static final String SUCCESS_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-07-06T10:30:00",
            "request_id": "01KORGCLIENT123ABC456DEF"
          },
          "data": {
            "client_code": "CLI-01KABC123DEF456GHI789JKL",
            "client_id": "inventory-production",
            "name": "Inventory Production Integration",
            "description": "Production integration between Inventory and E-Commerce systems",
            "status": "ACTIVE"
          }
        }
        """;

    public static final String INVALID_CLIENT_ID = """
        {
          "meta": {
            "timestamp": "2026-07-06T10:30:00",
            "request_id": "01KORGCLIENT123ABC456DEF"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/organization/client",
            "details": {
              "client_id": [
                "Client ID is invalid"
              ]
            }
          }
        }
        """;

    public static final String INVALID_NAME = """
        {
          "meta": {
            "timestamp": "2026-07-06T10:30:00",
            "request_id": "01KORGCLIENT123ABC456DEF"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/organization/client",
            "details": {
              "name": [
                "Client name is invalid"
              ]
            }
          }
        }
        """;

    public static final String MULTIPLE_VALIDATION_ERRORS = """
        {
          "meta": {
            "timestamp": "2026-07-06T10:30:00",
            "request_id": "01KORGCLIENT123ABC456DEF"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/organization/client",
            "details": {
              "client_id": [
                "Client ID is invalid"
              ],
              "name": [
                "Client name is invalid"
              ]
            }
          }
        }
        """;

    public static final String CLIENT_ID_ALREADY_EXISTS = """
        {
          "meta": {
            "timestamp": "2026-07-06T10:30:00",
            "request_id": "01KORGCLIENT123ABC456DEF"
          },
          "error": {
            "status": 409,
            "code": "CLIENT_ID_ALREADY_EXISTS",
            "message": "Client ID already exists",
            "path": "/organization/client",
            "details": {}
          }
        }
        """;

    public static final String ORGANIZATION_CLIENT_ALREADY_EXISTS = """
        {
          "meta": {
            "timestamp": "2026-07-06T10:30:00",
            "request_id": "01KORGCLIENT123ABC456DEF"
          },
          "error": {
            "status": 409,
            "code": "ORGANIZATION_CLIENT_ALREADY_EXISTS",
            "message": "Client already exists",
            "path": "/organization/client",
            "details": {}
          }
        }
        """;

    public static final String ORGANIZATION_NOT_FOUND = """
        {
          "meta": {
            "timestamp": "2026-07-06T10:30:00",
            "request_id": "01KORGCLIENT123ABC456DEF"
          },
          "error": {
            "status": 404,
            "code": "ORGANIZATION_NOT_FOUND",
            "message": "Organization not found",
            "path": "/organization/client",
            "details": {}
          }
        }
        """;
}
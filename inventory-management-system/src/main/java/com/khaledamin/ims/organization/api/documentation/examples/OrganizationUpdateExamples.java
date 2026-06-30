package com.khaledamin.ims.organization.api.documentation.examples;

public final class OrganizationUpdateExamples {

    private OrganizationUpdateExamples() {
    }

    public static final String SUCCESS_SHORT_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "data": {
            "code": "ORG-01K123ABC456DEF789GHIJKL",
            "name": "Amazon",
            "description": "Global e-commerce organization",
            "status": "ACTIVE",
            "image": null
          }
        }
        """;

    public static final String SUCCESS_FULL_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "data": {
            "code": "ORG-01K123ABC456DEF789GHIJKL",
            "name": "Amazon",
            "description": "Global e-commerce organization",
            "status": "ACTIVE",
            "image": {
              "code": "IMG-01KXYZ123ABC456DEF789GHIJK",
              "variants": [
                {
                  "resolution": "ORIGINAL",
                  "url": "http://localhost:8080/media/images/organization/IMG-01KXYZ123ABC456DEF789GHIJK/original.webp",
                  "width": 1200,
                  "height": 1200
                },
                {
                  "resolution": "LARGE",
                  "url": "http://localhost:8080/media/images/organization/IMG-01KXYZ123ABC456DEF789GHIJK/large.webp",
                  "width": 900,
                  "height": 600
                },
                {
                  "resolution": "SQUARE_MEDIUM",
                  "url": "http://localhost:8080/media/images/organization/IMG-01KXYZ123ABC456DEF789GHIJK/square-medium.webp",
                  "width": 600,
                  "height": 600
                }
              ]
            }
          }
        }
        """;

    public static final String INVALID_NAME = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/organization",
            "details": {
              "name": [
                "Organization name must contain only letters and spaces"
              ]
            }
          }
        }
        """;

    public static final String INVALID_DESCRIPTION = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/organization",
            "details": {
              "description": [
                "Organization description is too long"
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
            "path": "/organization",
            "details": {
              "name": [
                "Organization name must contain only letters and spaces"
              ],
              "description": [
                "Organization description is too long"
              ]
            }
          }
        }
        """;

}
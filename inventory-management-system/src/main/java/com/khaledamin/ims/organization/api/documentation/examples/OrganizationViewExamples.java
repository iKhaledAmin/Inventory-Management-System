package com.khaledamin.ims.organization.api.documentation.examples;

public final class OrganizationViewExamples {

    private OrganizationViewExamples() {
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
            "description": "Online Shopping",
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
            "description": "Online Shopping",
            "status": "ACTIVE",
            "image": {
              "code": "IMG-01KXYZ123ABC456DEF789GHIJK",
              "variants": [
                {
                  "resolution": "ORIGINAL",
                  "url": "http://localhost:8080/media/images/organization/IMG-01KXYZ123ABC456DEF789GHIJK/original.jpg",
                  "width": 1600,
                  "height": 900
                },
                {
                  "resolution": "LARGE",
                  "url": "http://localhost:8080/media/images/organization/IMG-01KXYZ123ABC456DEF789GHIJK/large.jpg",
                  "width": 1200,
                  "height": 675
                },
                {
                  "resolution": "SQUARE_MEDIUM",
                  "url": "http://localhost:8080/media/images/organization/IMG-01KXYZ123ABC456DEF789GHIJK/square_medium.jpg",
                  "width": 500,
                  "height": 500
                }
              ]
            }
          }
        }
        """;

    public static final String ORGANIZATION_NOT_FOUND = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 404,
            "code": "ORGANIZATION_NOT_FOUND",
            "message": "Organization not found",
            "path": "/organization",
            "details": {}
          }
        }
        """;
}
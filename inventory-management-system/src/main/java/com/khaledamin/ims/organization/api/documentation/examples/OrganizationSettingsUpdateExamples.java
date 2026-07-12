package com.khaledamin.ims.organization.api.documentation.examples;

public final class OrganizationSettingsUpdateExamples {

    private OrganizationSettingsUpdateExamples() {
    }

    public static final String SUCCESS_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-07-12T14:00:00",
            "request_id": "01KABC123DEF456GHI789JKL"
          },
          "data": {
            "reservation_expiration_minutes": 15,
            "allocation_strategy": "FEFO"
          }
        }
        """;

    public static final String INVALID_EXPIRATION_MINUTES = """
        {
          "meta": {
            "timestamp": "2026-07-12T14:00:00",
            "request_id": "01KABC123DEF456GHI789JKL"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/organization/settings",
            "details": {
              "reservationExpirationMinutes": [
                "Reservation expiration minutes must be greater than zero"
              ]
            }
          }
        }
        """;

    public static final String MULTIPLE_VALIDATION_ERRORS = """
        {
          "meta": {
            "timestamp": "2026-07-12T14:00:00",
            "request_id": "01KABC123DEF456GHI789JKL"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/organization/settings",
            "details": {
              "reservationExpirationMinutes": [
                "Reservation expiration minutes must be greater than zero"
              ],
              "allocationStrategy": [
                "Invalid allocation strategy"
              ]
            }
          }
        }
        """;
}
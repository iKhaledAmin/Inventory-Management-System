package com.khaledamin.ims.reservation.api.documentation.examples;

public final class ReservationCreateExamples {

    private ReservationCreateExamples() {
    }

    // ------------------------------------------------------------------------
    // Success
    // ------------------------------------------------------------------------

    public static final String RESERVATION_CREATED = """
        {
          "meta": {
            "timestamp": "2026-07-10T15:30:00",
            "request_id": "01RSVCREATE123"
          },
          "data": {
            "success": true,
            "reservation_info": {
              "reservation_code": "RSV-01KXBCY8KCQN4R6FFE8SJZRJ7L",
              "expires_at": "2026-07-10T16:00:00Z"
            },
            "unavailable_item_infos": []
          }
        }
        """;



    // ------------------------------------------------------------------------
    // Business Failure (Reservation cannot be created)
    // ------------------------------------------------------------------------

    public static final String INSUFFICIENT_STOCK = """
        {
          "meta": {
            "timestamp": "2026-07-10T15:30:00",
            "request_id": "01RSVCREATE123"
          },
          "data": {
            "success": false,
            "reservation_info": null,
            "unavailable_item_infos": [
              {
                "stock_code": "STK-01KXBCY8KCQN4R6FFE8SJZRJ7N",
                "requested_quantity": 10,
                "available_quantity": 3
              },
              {
                "stock_code": "STK-01KXBCY8KCQN4fFFE8SJZRJ7K",
                "requested_quantity": 5,
                "available_quantity": 1
              }
            ]
          }
        }
        """;



    // ------------------------------------------------------------------------
    // Validation Errors
    // ------------------------------------------------------------------------

    public static final String INVALID_QUANTITY = """
        {
          "meta": {
            "timestamp": "2026-07-10T15:30:00",
            "request_id": "01RSVCREATE123"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/reservations",
            "details": {
              "quantity": [
                "Reservation quantity must be greater than zero"
              ]
            }
          }
        }
        """;


    public static final String NULL_QUANTITY = """
        {
          "meta": {
            "timestamp": "2026-07-10T15:30:00",
            "request_id": "01RSVCREATE123"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/reservations",
            "details": {
              "quantity": [
                "Reservation quantity must not be null"
              ]
            }
          }
        }
        """;


    public static final String MULTIPLE_VALIDATION_ERRORS = """
        {
          "meta": {
            "timestamp": "2026-07-10T15:30:00",
            "request_id": "01RSVCREATE123"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/reservations",
            "details": {
              "quantity": [
                "Reservation quantity must be greater than zero"
              ],
              "stocks[1].quantity": [
                "Reservation quantity must not be null"
              ]
            }
          }
        }
        """;



    // ------------------------------------------------------------------------
    // Business Policy Errors
    // ------------------------------------------------------------------------

    public static final String RESERVATION_FORBIDDEN = """
        {
          "meta": {
            "timestamp": "2026-07-10T15:30:00",
            "request_id": "01RSVCREATE123"
          },
          "error": {
            "status": 403,
            "code": "RESERVATION_FORBIDDEN",
            "message": "Reserve stock is forbidden",
            "path": "/reservations",
            "details": {}
          }
        }
        """;


    public static final String STOCK_NOT_FOUND = """
        {
          "meta": {
            "timestamp": "2026-07-10T15:30:00",
            "request_id": "01RSVCREATE123"
          },
          "error": {
            "status": 404,
            "code": "STOCK_NOT_FOUND",
            "message": "Stock not found",
            "path": "/reservations",
            "details": {}
          }
        }
        """;
}
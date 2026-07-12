package com.khaledamin.ims.reservation.api.documentation.examples;

public final class ReservationConfirmExamples {

    private ReservationConfirmExamples() {
    }

    // ------------------------------------------------------------------------
    // Success
    // ------------------------------------------------------------------------

    public static final String RESERVATION_CONFIRMED = """
        {
          "meta": {
            "timestamp": "2026-07-10T15:30:00",
            "request_id": "01RSVCONFIRM123"
          },
          "data": {
            "message": "Reservation confirmed successfully"
          }
        }
        """;



    // ------------------------------------------------------------------------
    // Validation
    // ------------------------------------------------------------------------

    public static final String INVALID_RESERVATION_CODE = """
        {
          "meta": {
            "timestamp": "2026-07-10T15:30:00",
            "request_id": "01RSVCONFIRM123"
          },
          "error": {
            "status": 400,
            "code": "RESERVATION_CODE_INVALID",
            "message": "Reservation code is invalid",
            "path": "/reservations/confirm",
            "details": {}
          }
        }
        """;



    // ------------------------------------------------------------------------
    // Not Found
    // ------------------------------------------------------------------------

    public static final String RESERVATION_NOT_FOUND = """
        {
          "meta": {
            "timestamp": "2026-07-10T15:30:00",
            "request_id": "01RSVCONFIRM123"
          },
          "error": {
            "status": 404,
            "code": "RESERVATION_NOT_FOUND",
            "message": "Reservation not found",
            "path": "/reservations/RSV-1752153845123/confirm",
            "details": {}
          }
        }
        """;



    // ------------------------------------------------------------------------
    // Business Conflicts
    // ------------------------------------------------------------------------

    public static final String ALREADY_CONFIRMED = """
        {
          "meta": {
            "timestamp": "2026-07-10T15:30:00",
            "request_id": "01RSVCONFIRM123"
          },
          "error": {
            "status": 409,
            "code": "RESERVATION_ALREADY_CONFIRMED",
            "message": "Reservation is already confirmed",
            "path": "/reservations/RSV-1752153845123/confirm",
            "details": {}
          }
        }
        """;


    public static final String ALREADY_RELEASED = """
        {
          "meta": {
            "timestamp": "2026-07-10T15:30:00",
            "request_id": "01RSVCONFIRM123"
          },
          "error": {
            "status": 409,
            "code": "RESERVATION_ALREADY_RELEASED",
            "message": "Reservation is already released",
            "path": "/reservations/RSV-1752153845123/confirm",
            "details": {}
          }
        }
        """;


    public static final String ALREADY_EXPIRED = """
        {
          "meta": {
            "timestamp": "2026-07-10T15:30:00",
            "request_id": "01RSVCONFIRM123"
          },
          "error": {
            "status": 409,
            "code": "RESERVATION_ALREADY_EXPIRED",
            "message": "Reservation is already expired",
            "path": "/reservations/RSV-1752153845123/confirm",
            "details": {}
          }
        }
        """;



    // ------------------------------------------------------------------------
    // Business Authorization
    // ------------------------------------------------------------------------

    public static final String CONFIRMATION_FORBIDDEN = """
        {
          "meta": {
            "timestamp": "2026-07-10T15:30:00",
            "request_id": "01RSVCONFIRM123"
          },
          "error": {
            "status": 403,
            "code": "RESERVATION_CONFIRMATION_FORBIDDEN",
            "message": "Confirm stock reservation is forbidden",
            "path": "/reservations/RSV-1752153845123/confirm",
            "details": {}
          }
        }
        """;
}
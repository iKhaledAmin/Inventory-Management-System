package com.khaledamin.ims.stock.api.documentation.examples;

public final class StockListBatchExamples {

    private StockListBatchExamples() {
    }

    public static final String SUCCESS_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKBATCH123456789"
          },
          "data": [
            {
              "code": "BAT-01AAA111",
              "received_quantity": 100,
              "consumed_quantity": 10,
              "available_quantity": 90,
              "received_date": "2026-06-01",
              "expiration_date": "2027-06-01",
              "unit_cost": 500.00,
              "stock_value": 45000.00,
              "expired": false
            },
            {
              "code": "BAT-01AAA222",
              "received_quantity": 50,
              "consumed_quantity": 5,
              "available_quantity": 45,
              "received_date": "2026-05-10",
              "expiration_date": "2027-05-10",
              "unit_cost": 480.00,
              "stock_value": 21600.00,
              "expired": false
            }
          ],
          "page_info": {
            "first": true,
            "has_next": false,
            "has_previous": false,
            "last": true,
            "page": 0,
            "size": 20,
            "total_elements": 2,
            "total_pages": 1
          }
        }
        """;

    public static final String STOCK_NOT_FOUND = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKERR123456789"
          },
          "error": {
            "status": 404,
            "code": "STOCK_NOT_FOUND",
            "message": "Stock not found",
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL/batches",
            "details": {}
          }
        }
        """;

    public static final String FORBIDDEN = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKERR123456789"
          },
          "error": {
            "status": 403,
            "code": "STOCK_LIST_BATCHES_FORBIDDEN",
            "message": "List batches is forbidden",
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL/batches",
            "details": {}
          }
        }
        """;

    public static final String INVALID_PAGE_NUMBER = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKERR123456789"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL/batches",
            "details": {
              "page": [
                "must be greater than or equal to 0"
              ]
            }
          }
        }
        """;

    public static final String INVALID_PAGE_SIZE = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKERR123456789"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL/batches",
            "details": {
              "size": [
                "must be less than or equal to 100"
              ]
            }
          }
        }
        """;

    public static final String INVALID_SORT_FIELD = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKERR123456789"
          },
          "error": {
            "status": 400,
            "code": "STOCK_SORT_FIELD_INVALID",
            "message": "Invalid stock sort field",
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL/batches",
            "details": {}
          }
        }
        """;

    public static final String MULTIPLE_VALIDATION_ERRORS = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKERR123456789"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL/batches",
            "details": {
              "page": [
                "must be greater than or equal to 0"
              ],
              "size": [
                "must be less than or equal to 100"
              ]
            }
          }
        }
        """;
}
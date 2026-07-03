package com.khaledamin.ims.stock.api.documentation.examples;

public final class StockListExamples {

    private StockListExamples() {
    }

    public static final String SUCCESS_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKLIST123456789"
          },
          "data": [
            {
              "code": "STK-01AAA111",
              "sku": "IPHONE-17",
              "name": "iPhone 17",
              "description": "Apple device",
              "status": "ACTIVE",
              "image_url": "http://localhost:8080/api/v1/media/images/stock/IMG-01KW71M28DNWC73CY3TXFZ7DJH/square_thumbnail.jpg"
            },
            {
              "code": "STK-01BBB222",
              "sku": "SAMSUNG-S25",
              "name": "Samsung S25",
              "description": "Android flagship",
              "status": "ACTIVE",
              "image_url": "http://localhost:8080/api/v1/media/images/stock/IMG-01KW71M28DNWC73CY3TXFZ7DJH/square_thumbnail.jpg"
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
            "path": "/stocks",
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
            "path": "/stocks",
            "details": {
              "size": [
                "must be less than or equal to 100"
              ]
            }
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
            "path": "/stocks",
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

    public static final String INVALID_SORT_FIELD = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKERR123456789"
          },
          "error": {
            "status": 400,
            "code": "STOCK_SORT_FIELD_INVALID",
            "details": {},
            "message": "Invalid stock sort field",
            "path": "/stocks"
          }
        }
        """;
}
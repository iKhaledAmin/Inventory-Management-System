package com.khaledamin.ims.stock.api.documentation.examples;

public final class StockRestockExamples {

    private StockRestockExamples() {
    }

    public static final String SUCCESS_SHORT_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKRESTOCK123"
          },
          "data": {
            "code": "STK-01KABC123DEF456GHI789JKL",
            "sku": "IPHONE-17-PRO",
            "name": "Apple iPhone 17 Pro",
            "description": "Latest Apple flagship smartphone",
            "status": "ACTIVE",
            "totalBatchCount": 3,
            "totalReceivedQuantity": 250,
            "totalConsumedQuantity": 10,
            "totalAvailableQuantity": 240,
            "totalStockValue": 240000.00,
            "nearestExpirationDate": "2027-06-01",
            "image": null
          }
        }
        """;

    public static final String SUCCESS_FULL_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKRESTOCK123"
          },
          "data": {
            "code": "STK-01KABC123DEF456GHI789JKL",
            "sku": "IPHONE-17-PRO",
            "name": "Apple iPhone 17 Pro",
            "description": "Latest Apple flagship smartphone",
            "status": "ACTIVE",
            "totalBatchCount": 3,
            "totalReceivedQuantity": 250,
            "totalConsumedQuantity": 10,
            "totalAvailableQuantity": 240,
            "totalStockValue": 240000.00,
            "nearestExpirationDate": "2027-06-01",
            "image": {
              "code": "IMG-01KSTOCKPRIMARY",
              "variants": [
                {
                  "resolution": "ORIGINAL",
                  "url": "http://localhost:8080/media/images/stock/original.jpg",
                  "width": 1200,
                  "height": 900
                },
                {
                  "resolution": "SQUARE_THUMBNAIL",
                  "url": "http://localhost:8080/media/images/stock/thumbnail.jpg",
                  "width": 150,
                  "height": 150
                },
                {
                  "resolution": "SQUARE_MEDIUM",
                  "url": "http://localhost:8080/media/images/stock/medium.jpg",
                  "width": 500,
                  "height": 500
                }
              ]
            }
          }
        }
        """;

    public static final String INVALID_RECEIVED_QUANTITY = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKRESTOCK123"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL",
            "details": {
              "receivedQuantity": [
                "must be greater than 0"
              ]
            }
          }
        }
        """;

    public static final String INVALID_UNIT_COST = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKRESTOCK123"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL",
            "details": {
              "unitCost": [
                "must be greater than 0"
              ]
            }
          }
        }
        """;

    public static final String INVALID_EXPIRATION_DATE = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKRESTOCK123"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL",
            "details": {
              "expirationDate": [
                "must be a future date"
              ]
            }
          }
        }
        """;

    public static final String MULTIPLE_VALIDATION_ERRORS = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKRESTOCK123"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL",
            "details": {
              "receivedQuantity": [
                "must be greater than 0"
              ],
              "unitCost": [
                "must be greater than 0"
              ]
            }
          }
        }
        """;

    public static final String NOT_FOUND = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STK6Q6WY7K9Q8A5B3D2E1F4G"
          },
          "error": {
            "status": 404,
            "code": "STOCK_NOT_FOUND",
            "message": "Stock not found",
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL",
            "details": {}
          }
        }
        """;

    public static final String FORBIDDEN = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STK6Q6WY7K9Q8A5B3D2E1F4G"
          },
          "error": {
            "status": 403,
            "code": "STOCK_RESTOCK_FORBIDDEN",
            "message": "Restock is forbidden",
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL",
            "details": {}
          }
        }
        """;
}
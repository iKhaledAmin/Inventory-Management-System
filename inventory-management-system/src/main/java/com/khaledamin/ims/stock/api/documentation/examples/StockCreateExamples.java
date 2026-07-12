package com.khaledamin.ims.stock.api.documentation.examples;

public final class StockCreateExamples {

    private StockCreateExamples() {
    }

    public static final String SUCCESS_SHORT_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKCREATE123"
          },
          "data": {
            "code": "STK-01KABC123DEF456GHI789JKL",
            "sku": "IPHONE-17-PRO",
            "name": "Apple iPhone 17 Pro",
            "description": "Latest Apple flagship smartphone",
            "status": "ACTIVE",
            "totalBatchCount": 0,
            "totalReceivedQuantity": 0,
            "totalAvailableQuantity": 0,
            "totalReservedQuantity": 0,
            "totalConsumedQuantity": 0,
            "totalStockValue": 0,
            "nearestExpirationDate": null,
            "image": {}
          }
        }
        """;

    public static final String SUCCESS_FULL_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKCREATE123"
          },
          "data": {
            "code": "STO-01KABC123DEF456GHI789JKL",
            "sku": "IPHONE-17-PRO",
            "name": "Apple iPhone 17 Pro",
            "description": "Latest Apple flagship smartphone",
            "status": "ACTIVE",
            "totalBatchCount": 0,
            "totalReceivedQuantity": 0,
            "totalConsumedQuantity": 0,
            "totalAvailableQuantity": 0,
            "totalStockValue": 0,
            "nearestExpirationDate": null,
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


    public static final String INVALID_SKU = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKCREATE123"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/stocks",
            "details": {
              "sku":"must not be blank"
            }
          }
        }
        """;

    public static final String INVALID_NAME = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKUPDATE123"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/stocks",
            "details": {
              "name": [
                "Stock name contains unsupported characters"
              ]
            }
          }
        }
        """;

    public static final String INVALID_DESCRIPTION = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKUPDATE123"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/stocks",
            "details": {
              "description": [
                "Stock description is too long"
              ]
            }
          }
        }
        """;

    public static final String MULTIPLE_VALIDATION_ERRORS = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKUPDATE123"
          },
          "error": {
            "status": 400,
            "code": "METHOD_ARGUMENT_INVALID",
            "message": "Validation failed",
            "path": "/stocks",
            "details": {
              "name": [
                "Stock name contains unsupported characters"
              ],
              "description": [
                "Stock description is too long"
              ]
            }
          }
        }
        """;

    public static final String SKU_ALREADY_EXISTS = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 409,
            "code": "STOCK_SKU_ALREADY_EXISTS",
            "message": "Stock SKU already exists",
            "path": "/stocks",
            "details": {}
          }
        }
        """;
}
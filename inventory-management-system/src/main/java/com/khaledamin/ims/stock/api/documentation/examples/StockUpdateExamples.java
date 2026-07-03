package com.khaledamin.ims.stock.api.documentation.examples;

public final class StockUpdateExamples {

    private StockUpdateExamples() {
    }

    public static final String SUCCESS_SHORT_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKUPDATE123"
          },
          "data": {
            "code": "STK-01KABC123DEF456GHI789JKL",
            "sku": "IPHONE-17-PRO",
            "name": "Apple iPhone 17 Pro Max",
            "description": "Updated description",
            "status": "ACTIVE",
            "totalBatchCount": 2,
            "totalReceivedQuantity": 150,
            "totalConsumedQuantity": 10,
            "totalAvailableQuantity": 140,
            "totalStockValue": 140000.00,
            "nearestExpirationDate": null,
            "image" : null
          }
        }
        """;

    public static final String SUCCESS_FULL_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKUPDATE123"
          },
          "data": {
            "code": "STK-01KABC123DEF456GHI789JKL",
            "sku": "IPHONE-17-PRO",
            "name": "Apple iPhone 17 Pro Max",
            "description": "Updated description",
            "status": "ACTIVE",
            "totalBatchCount": 2,
            "totalReceivedQuantity": 150,
            "totalConsumedQuantity": 10,
            "totalAvailableQuantity": 140,
            "totalStockValue": 140000.00,
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
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL",
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
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL",
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
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL",
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

    public static final String NOT_FOUND = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKUPDATE123"
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
            "request_id": "01STKUPDATE123"
          },
          "error": {
            "status": 403,
            "code": "STOCK_UPDATE_FORBIDDEN",
            "message": "Update stock is forbidden",
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL",
            "details": {}
          }
        }
        """;
}
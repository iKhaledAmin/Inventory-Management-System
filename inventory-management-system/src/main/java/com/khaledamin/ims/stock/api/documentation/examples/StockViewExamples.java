package com.khaledamin.ims.stock.api.documentation.examples;

public final class StockViewExamples {

    private StockViewExamples() {
    }

    public static final String SUCCESS_SHORT_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STK6Q6WY7K9Q8A5B3D2E1F4G"
          },
          "data": {
            "code": "STK-01KABC123DEF456GHI789JKL",
            "sku": "IPHONE-17-PRO",
            "name": "Apple iPhone 17 Pro",
            "description": "Latest flagship device",
            "status": "ACTIVE",
            "totalBatchCount": 2,
            "totalReceivedQuantity": 150,
            "totalAvailableQuantity": 130,
            "totalReservedQuantity": 10,
            "totalConsumedQuantity": 10,
            "totalStockValue": 140000.00,
            "nearestExpirationDate": "2027-06-01",
            "image": null
          }
        }
        """;

    public static final String SUCCESS_FULL_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STK6Q6WY7K9Q8A5B3D2E1F4G"
          },
          "data": {
            "code": "STK-01KABC123DEF456GHI789JKL",
            "sku": "IPHONE-17-PRO",
            "name": "Apple iPhone 17 Pro",
            "description": "Latest flagship device",
            "status": "ACTIVE",
            "totalBatchCount": 2,
            "totalReceivedQuantity": 150,
            "totalAvailableQuantity": 130,
            "totalReservedQuantity": 10,
            "totalConsumedQuantity": 10,
            "totalStockValue": 140000.00,
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
            "code": "STOCK_VIEW_FORBIDDEN",
            "message": "View stock is forbidden",
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL",
            "details": {}
          }
        }
        """;
}
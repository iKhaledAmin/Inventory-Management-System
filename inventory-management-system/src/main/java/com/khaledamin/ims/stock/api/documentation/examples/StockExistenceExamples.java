package com.khaledamin.ims.stock.api.documentation.examples;

public final class StockExistenceExamples {

    private StockExistenceExamples() {}

    public static final String STOCK_EXISTS = """
    {
      "meta": {
        "timestamp": "2026-07-03T10:00:00",
        "request_id": "01STKCREATE123"
      },
      "data": {
        "stock_code": "STK-01KABC123DEF456GHI789JKL",
        "exists": true
      }
    }
    """;

    public static final String STOCK_NOT_EXISTS = """
    {
      "meta": {
        "timestamp": "2026-07-03T10:00:00",
        "request_id": "01STKCREATE123"
      },
      "data": {
        "stock_code": "STK-01KABC123DEF456GHI78958",
        "exists": false
      }
    }
    """;

    public static final String NOT_FOUND = """
    {
      "meta": {
        "timestamp": "2026-07-03T10:00:00",
        "request_id": "REQ-IMS-404"
      },
      "error": {
        "status": 404,
        "code": "STOCK_NOT_FOUND",
        "message": "Stock not found",
        "path": "/stocks/STK-INVALID/exists",
        "details": {}
      }
    }
    """;
}
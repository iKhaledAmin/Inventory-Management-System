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
}
package com.khaledamin.ims.stock.api.documentation.examples;

public final class StockDeleteExamples {

    private StockDeleteExamples() {
    }

    public static final String STOCK_DELETED = """
        {
          "meta": {
            "timestamp": "2026-06-11T15:30:00",
            "request_id": "01JX8H4Y9Z9X4K7T3S8A1B2C3D"
          },
          "data": {
            "message": "Stock deleted successfully"
          }
        }
        """;

    public static final String NOT_FOUND = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKDELETE123"
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
            "request_id": "01STKDELETE123"
          },
          "error": {
            "status": 403,
            "code": "STOCK_DELETE_FORBIDDEN",
            "message": "Delete stock is forbidden",
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL",
            "details": {}
          }
        }
        """;

    public static final String NOT_ALLOWED = """
        {
          "meta": {
            "timestamp": "2026-07-02T10:30:00",
            "request_id": "01STKDELETE123"
          },
          "error": {
            "status": 409,
            "code": "STOCK_DELETE_NOT_ALLOWED",
            "message": "Cannot delete stock with available stock batches",
            "path": "/stocks/STK-01KABC123DEF456GHI789JKL",
            "details": {}
          }
        }
        """;
}
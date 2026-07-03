package com.khaledamin.ims.stock.domain.generator;

import com.khaledamin.ims.core.generator.UlidGenerator;

public class StockCodeGenerator {

    public static String generateItemCode() {
        return "STK" + "-" + UlidGenerator.generate();
    }

    public static String generateBatchCode() {
        return "BAT" + "-" + UlidGenerator.generate();
    }
}

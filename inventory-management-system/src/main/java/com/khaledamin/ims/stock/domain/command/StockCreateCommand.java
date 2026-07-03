package com.khaledamin.ims.stock.domain.command;

import com.khaledamin.ims.stock.api.dto.StockCreateRequest;
import com.khaledamin.ims.stock.domain.value.StockDescription;
import com.khaledamin.ims.stock.domain.value.StockName;
import com.khaledamin.ims.stock.domain.value.StockSKU;

public record StockCreateCommand(StockSKU sku, StockName name, StockDescription description) {

    public static StockCreateCommand of(String sku, String name, String description) {

        return new StockCreateCommand(
                StockSKU.of(sku),
                StockName.of(name),
                StockDescription.of(description)
        );
    }

    public static StockCreateCommand of(StockCreateRequest request) {
        return of(
                request.getSku(),
                request.getName(),
                request.getDescription()
        );
    }
}
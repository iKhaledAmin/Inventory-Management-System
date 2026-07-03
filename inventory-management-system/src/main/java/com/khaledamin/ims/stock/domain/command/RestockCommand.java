package com.khaledamin.ims.stock.domain.command;

import com.khaledamin.ims.stock.api.dto.RestockRequest;
import com.khaledamin.ims.stock.domain.value.StockExpirationDate;
import com.khaledamin.ims.stock.domain.value.StockReceivedQuantity;
import com.khaledamin.ims.stock.domain.value.StockUnitCost;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RestockCommand(StockReceivedQuantity receivedQuantity, StockExpirationDate expirationDate, StockUnitCost unitCost) {

    public static RestockCommand of(Long receivedQuantity, LocalDate expirationDate, BigDecimal unitCost){
        return new RestockCommand(
                StockReceivedQuantity.of(receivedQuantity),
                StockExpirationDate.of(expirationDate),
                StockUnitCost.of(unitCost)
        );
    }

    public static RestockCommand of(RestockRequest request) {
        return of(
                request.getQuantity(),
                request.getExpirationDate(),
                request.getUnitCost()
        );
    }
}

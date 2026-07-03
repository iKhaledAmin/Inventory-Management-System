package com.khaledamin.ims.stock.domain.model;

import com.khaledamin.ims.stock.exception.StockValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StockBatchSortField {

    RECEIVED_DATE("receivedDate"),
    EXPIRATION_DATE("expirationDate"),

    RECEIVED_QUANTITY("receivedQuantity"),
    AVAILABLE_QUANTITY("availableQuantity"),

    UNIT_COST("unitCost")
    ;

    private final String field;


    public static String getDefault() {
        return RECEIVED_DATE.getField();
    }

    public static String getFieldFrom(String queryParam) {
        try {
            return StockBatchSortField.valueOf(queryParam).getField();
        } catch (IllegalArgumentException e) {
            throw StockValidationException.invalidSortField()
                    .withDebugDetails("sortField" , queryParam);
        }
    }
}

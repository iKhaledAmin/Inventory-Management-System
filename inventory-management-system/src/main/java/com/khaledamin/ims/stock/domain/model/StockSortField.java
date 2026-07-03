package com.khaledamin.ims.stock.domain.model;

import com.khaledamin.ims.stock.exception.StockValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StockSortField {
    NAME("name"),
    CREATED_AT("createdAt"),
    SKU("sku")
            ;

    private final String field;


    public static String getDefault() {
        return NAME.getField();
    }

    public static String getFieldFrom(String queryParam) {
        try {
            return StockSortField.valueOf(queryParam).getField();
        } catch (IllegalArgumentException e) {
            throw StockValidationException.invalidSortField()
                    .withDebugDetails("sortField" , queryParam);
        }
    }
}

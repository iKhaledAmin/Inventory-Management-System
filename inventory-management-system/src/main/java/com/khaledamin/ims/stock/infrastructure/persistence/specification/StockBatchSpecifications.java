package com.khaledamin.ims.stock.infrastructure.persistence.specification;

import com.khaledamin.ims.stock.domain.model.StockBatch;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public final class StockBatchSpecifications {

    private StockBatchSpecifications() {
    }

    public static Specification<StockBatch> stockCode(String stockCode) {

        return (root, query, cb) ->
                cb.equal(
                        root.get("stockItem").get("code"),
                        stockCode
                );
    }

    public static Specification<StockBatch> hasStock() {

        return (root, query, cb) ->
                cb.greaterThan(
                        root.get("availableQuantity"),
                        0L
                );
    }

    public static Specification<StockBatch> expired() {

        return (root, query, cb) ->
                cb.lessThan(
                        root.get("expirationDate"),
                        LocalDate.now()
                );
    }

    public static Specification<StockBatch> expirationBefore(LocalDate date) {

        return (root, query, cb) ->
                cb.lessThan(
                        root.get("expirationDate"),
                        date
                );
    }

}
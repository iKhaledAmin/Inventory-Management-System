package com.khaledamin.ims.stock.domain.model;

import com.khaledamin.ims.core.audit.AuditableEntity;
import com.khaledamin.ims.stock.domain.command.RestockCommand;
import com.khaledamin.ims.stock.domain.generator.StockCodeGenerator;
import com.khaledamin.ims.stock.exception.StockTechnicalException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "stock_batches")
public class StockBatch extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batch_id")
    private Long id;

    @Column(name = "code",nullable = false,updatable = false,unique = true)
    private String code;

    @Column(name = "received_quantity",nullable = false,updatable = false)
    private Long receivedQuantity;

    @Column(name = "available_quantity",nullable = false)
    private Long availableQuantity;

    @Column(name = "received_date",nullable = false,updatable = false)
    private LocalDate receivedDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "unit_cost", precision = 19, scale = 4)
    private BigDecimal unitCost;


    // ------------------------------------------------------ Relations ----------------------------------------------------- //

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private StockItem stockItem;

    // ---------------------------------------------------- End Relations ---------------------------------------------------- //


    // ---------------------------------------------------- Methods -------------------------------------------------------- //


    public static StockBatch create(RestockCommand command, StockItem stockItem) {
        if (command == null) {
            throw StockTechnicalException.nullRestockCommand();
        }

        if (stockItem == null) {
            throw StockTechnicalException.nullStockItem();
        }

        String code = StockCodeGenerator.generateBatchCode();

        return StockBatch.builder()
                .code(code)
                .receivedQuantity(command.receivedQuantity().value())
                .availableQuantity(command.receivedQuantity().value())
                .receivedDate(LocalDate.now())
                .expirationDate(command.expirationDate().value())
                .unitCost(command.unitCost().value())
                .stockItem(stockItem)
                .build();
    }


    public long getConsumedQuantity(){
        return receivedQuantity - availableQuantity;
    }

    public boolean isEmpty() {
        return availableQuantity == 0;
    }

    public boolean hasStock() {
        return availableQuantity > 0;
    }

    public boolean isExpired() {

        return expirationDate != null && expirationDate.isBefore(LocalDate.now());
    }

    public boolean expiresToday() {

        return expirationDate != null && expirationDate.equals(LocalDate.now());
    }

    public long daysUntilExpiration() {

        if (expirationDate == null) {
            return Long.MAX_VALUE;
        }

        return ChronoUnit.DAYS.between(
                LocalDate.now(),
                expirationDate
        );
    }

    public BigDecimal getStockValue() {

        if (unitCost == null) {
            return BigDecimal.ZERO;
        }

        return unitCost.multiply(
                BigDecimal.valueOf(availableQuantity)
        );
    }

    // ---------------------------------------------------- End Methods ------------------------------------------------------ //
}

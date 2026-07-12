package com.khaledamin.ims.stock.domain.model;

import com.khaledamin.ims.core.audit.AuditableEntity;
import com.khaledamin.ims.stock.domain.command.RestockCommand;
import com.khaledamin.ims.stock.domain.generator.StockCodeGenerator;
import com.khaledamin.ims.stock.exception.StockBusinessException;
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

    @Column(name = "reserved_quantity", nullable = false)
    private Long reservedQuantity;

    @Column(name = "consumed_quantity",nullable = false)
    private Long consumedQuantity;

    @Column(name = "received_date",nullable = false,updatable = false)
    private LocalDate receivedDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "unit_cost", precision = 19, scale = 4)
    private BigDecimal unitCost;


    // ------------------------------------------------------ Relations ----------------------------------------------------- //

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    // ---------------------------------------------------- End Relations ---------------------------------------------------- //


    // ---------------------------------------------------- Methods -------------------------------------------------------- //


    public static StockBatch create(RestockCommand command, Stock stock) {
        if (command == null) {
            throw StockTechnicalException.nullRestockCommand();
        }

        if (stock == null) {
            throw StockTechnicalException.nullStockItem();
        }

        String code = StockCodeGenerator.generateBatchCode();

        return StockBatch.builder()
                .code(code)
                .receivedQuantity(command.receivedQuantity().value())
                .availableQuantity(command.receivedQuantity().value())
                .reservedQuantity(0L)
                .consumedQuantity(0L)
                .receivedDate(LocalDate.now())
                .expirationDate(command.expirationDate().value())
                .unitCost(command.unitCost().value())
                .stock(stock)
                .build();
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

        long quantity = receivedQuantity - consumedQuantity;

        return unitCost.multiply(
                BigDecimal.valueOf(quantity)
        );
    }

    public void reserve(long quantity) {

        if (availableQuantity < quantity) {
            throw StockBusinessException.insufficientStock();
        }

        availableQuantity -= quantity;
        reservedQuantity += quantity;
    }

    public void release(long quantity) {

        if (reservedQuantity < quantity) {
            throw StockBusinessException.invalidReleaseQuantity();
        }

        reservedQuantity -= quantity;
        availableQuantity += quantity;
    }

    public void consume(long quantity) {

        if (reservedQuantity < quantity) {
            throw StockBusinessException.invalidConsumeQuantity();
        }

        reservedQuantity -= quantity;
        consumedQuantity += quantity;
    }

    // ---------------------------------------------------- End Methods ------------------------------------------------------ //
}

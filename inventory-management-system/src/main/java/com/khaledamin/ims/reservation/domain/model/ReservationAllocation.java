package com.khaledamin.ims.reservation.domain.model;

import com.khaledamin.ims.core.audit.AuditableEntity;
import com.khaledamin.ims.reservation.domain.value.ReservationQuantity;
import com.khaledamin.ims.reservation.exception.ReservationTechnicalException;
import com.khaledamin.ims.stock.domain.model.StockBatch;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "reservation_allocations")
public class ReservationAllocation extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allocation_id")
    private Long id;

    @Column(name = "quantity", nullable = false,updatable = false)
    private Long quantity;


    // ------------------------------------------------------ Relations ----------------------------------------------------- //

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_batch_id", nullable = false)
    private StockBatch stockBatch;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reservation_item_id", nullable = false)
    private ReservationItem reservationItem;

    // ---------------------------------------------------- End Relations ---------------------------------------------------- //

    // ---------------------------------------------------- Methods -------------------------------------------------------- //

    public static ReservationAllocation create(
            StockBatch stockBatch, ReservationItem item, ReservationQuantity quantity
    ) {

        if (stockBatch == null) throw ReservationTechnicalException.nullStockBatch();
        if (quantity == null) throw ReservationTechnicalException.nullQuantity();
        if (item == null) throw ReservationTechnicalException.nullReservationItem();

        return ReservationAllocation.builder()
                .stockBatch(stockBatch)
                .reservationItem(item)
                .quantity(quantity.value())
                .build();
    }


    public void reserveStockBatch() {

        stockBatch.reserve(quantity);
    }

    public void releaseStockBatch() {

        stockBatch.release(quantity);
    }

    public void consumeStockBatch() {

        stockBatch.consume(quantity);
    }
    // ---------------------------------------------------- End Methods ---------------------------------------------------- //
}

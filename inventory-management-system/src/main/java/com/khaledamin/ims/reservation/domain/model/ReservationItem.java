package com.khaledamin.ims.reservation.domain.model;

import com.khaledamin.ims.core.audit.AuditableEntity;
import com.khaledamin.ims.reservation.domain.value.ReservationQuantity;
import com.khaledamin.ims.reservation.exception.ReservationTechnicalException;
import com.khaledamin.ims.stock.domain.model.Stock;
import com.khaledamin.ims.stock.domain.model.StockBatch;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "reservation_items")
public class ReservationItem extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "quantity",nullable = false,updatable = false)
    private Long quantity;

    // ------------------------------------------------------ Relations ----------------------------------------------------- //

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @Builder.Default
    @OneToMany(
            mappedBy = "reservationItem",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ReservationAllocation> allocations = new ArrayList<>();

    // ---------------------------------------------------- End Relations ---------------------------------------------------- //

    // ---------------------------------------------------- Methods -------------------------------------------------------- //

    public static ReservationItem create(Stock stock, Reservation reservation, ReservationQuantity quantity) {

        if (stock == null) throw ReservationTechnicalException.nullStock();
        if (reservation == null) throw ReservationTechnicalException.nullReservation();
        if (quantity == null) throw ReservationTechnicalException.nullQuantity();

        return ReservationItem.builder()
                .stock(stock)
                .reservation(reservation)
                .quantity(quantity.value())
                .build();
    }

    public void addAllocation(StockBatch stockBatch, ReservationQuantity quantity) {

        ReservationAllocation allocation = ReservationAllocation.create(stockBatch,this,quantity);

        allocations.add(allocation);
    }

    public void consumeAllocations() {

        allocations.forEach(
                ReservationAllocation::consumeStockBatch
        );
    }

    public void releaseAllocations() {

        allocations.forEach(
                ReservationAllocation::releaseStockBatch
        );
    }
}
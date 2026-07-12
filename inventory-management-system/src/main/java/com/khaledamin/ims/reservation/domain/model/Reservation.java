package com.khaledamin.ims.reservation.domain.model;

import com.khaledamin.ims.core.audit.AuditableEntity;
import com.khaledamin.ims.organization.domain.model.Organization;
import com.khaledamin.ims.reservation.domain.generator.ReservationCodeGenerator;
import com.khaledamin.ims.reservation.domain.value.ReservationExpirationDate;
import com.khaledamin.ims.reservation.domain.value.ReservationQuantity;
import com.khaledamin.ims.reservation.exception.ReservationBusinessException;
import com.khaledamin.ims.reservation.exception.ReservationTechnicalException;
import com.khaledamin.ims.stock.domain.model.Stock;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "reservations")
public class Reservation extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(name = "code",nullable = false,updatable = false,unique = true)
    private String code;

    @Column(name = "expires_at",nullable = false,updatable = false)
    private Instant expiresAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private ReservationStatus status;


    // ------------------------------------------------------ Relations ----------------------------------------------------- //

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organization_id",nullable = false)
    private Organization organization;


    @Builder.Default
    @OneToMany(
            mappedBy = "reservation",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ReservationItem> items = new ArrayList<>();


    // ---------------------------------------------------- End Relations ---------------------------------------------------- //

    // ---------------------------------------------------- Methods -------------------------------------------------------- //


    public static Reservation create(Organization organization, ReservationExpirationDate expirationDate) {

        if (expirationDate == null){
            throw ReservationTechnicalException.nullExpirationDate();
        }

        Reservation reservation = Reservation.builder()
                .code(ReservationCodeGenerator.generate())
                .expiresAt(expirationDate.value())
                .status(ReservationStatus.getDefaultStatus())
                .build();

        reservation.attachOrganization(organization);

        return reservation;
    }

    public ReservationItem addItem(Stock stock, ReservationQuantity quantity) {

        ReservationItem item = ReservationItem.create(stock, this, quantity);

        items.add(item);

        return item;
    }

    private void attachOrganization(Organization organization) {

        if (organization == null) {
            throw ReservationTechnicalException.nullOrganization();
        }

        this.organization = organization;
    }

    public ReservationStatus getEffectiveStatus() {

        if (isExpired()) {
            return ReservationStatus.EXPIRED;
        }

        return status;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }



    public void confirm() {

        validateCanConfirm();

        for (ReservationItem item : items) {

            item.consumeAllocations();
        }

        this.status = ReservationStatus.CONFIRMED;
    }

    public void release() {

        if (status == ReservationStatus.EXPIRED) return;

        validateCanRelease();

        for (ReservationItem item : items) {

            item.releaseAllocations();
        }

        this.status = ReservationStatus.RELEASED;
    }


    public void expire() {

        if (status == ReservationStatus.EXPIRED) return;

        validateCanExpire();

        for (ReservationItem item : items) {

            item.releaseAllocations();
        }

        this.status = ReservationStatus.EXPIRED;
    }

    private void validateCanConfirm(){

        if (getEffectiveStatus() == ReservationStatus.EXPIRED)
            throw ReservationBusinessException.alreadyExpired();

        if (getEffectiveStatus() == ReservationStatus.CONFIRMED)
            throw ReservationBusinessException.alreadyConfirmed();

        if (getEffectiveStatus() == ReservationStatus.RELEASED)
            throw ReservationBusinessException.alreadyReleased();
    }

    private void validateCanRelease(){

        if (getEffectiveStatus() == ReservationStatus.CONFIRMED)
            throw ReservationBusinessException.alreadyConfirmed();

        if (getEffectiveStatus() == ReservationStatus.RELEASED)
            throw ReservationBusinessException.alreadyReleased();
    }

    private void validateCanExpire(){

        if (getEffectiveStatus() == ReservationStatus.CONFIRMED)
            throw ReservationBusinessException.alreadyConfirmed();

        if (getEffectiveStatus() == ReservationStatus.RELEASED)
            throw ReservationBusinessException.alreadyReleased();
    }
}

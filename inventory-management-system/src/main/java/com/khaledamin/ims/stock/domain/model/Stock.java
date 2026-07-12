package com.khaledamin.ims.stock.domain.model;

import com.khaledamin.ims.core.audit.LifecycleAuditableEntity;
import com.khaledamin.ims.identity.core.model.Actor;
import com.khaledamin.ims.media.image.domain.model.Image;
import com.khaledamin.ims.organization.domain.model.Organization;
import com.khaledamin.ims.stock.domain.command.RestockCommand;
import com.khaledamin.ims.stock.domain.command.StockCreateCommand;
import com.khaledamin.ims.stock.domain.command.StockUpdateCommand;
import com.khaledamin.ims.stock.domain.generator.StockCodeGenerator;
import com.khaledamin.ims.stock.exception.StockBusinessException;
import com.khaledamin.ims.stock.exception.StockTechnicalException;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "stocks",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_stock_org_sku",
                        columnNames = {"organization_id", "sku"}
                )
        }
)
@SQLRestriction("deleted_at IS NULL")
public class Stock extends LifecycleAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long id;

    @Column(name = "code",nullable = false,updatable = false,unique = true)
    private String code;

    @Column(name = "sku", nullable = false,updatable = false)
    private String sku;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StockStatus status;


    // ------------------------------------------------------ Relations ----------------------------------------------------- //

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organization_id",nullable = false)
    private Organization organization;

    @Builder.Default
    @OneToMany(
            mappedBy = "stock",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<StockBatch> stockBatches = new ArrayList<>();

    // ---------------------------------------------------- End Relations ---------------------------------------------------- //


    // ---------------------------------------------------- Methods -------------------------------------------------------- //

    public static Stock create(StockCreateCommand command){
        if (command == null) {
            throw StockTechnicalException.nullCreateCommand();
        }

        String code = StockCodeGenerator.generateItemCode();

        return Stock.builder()
                .code(code)
                .sku(command.sku().toString())
                .name(command.name().toString())
                .description(command.description().toString())
                .status(StockStatus.getDefault())
                .stockBatches(new ArrayList<>())
                .build();

    }

    public void update(StockUpdateCommand command) {
        if (command == null) {
            throw StockTechnicalException.nullUpdateCommand();
        }

        command.name().ifPresent(name -> this.name = name.toString());
        command.description().ifPresent(description -> this.description = description.toString());
    }

    public void delete(Actor actor) {

        if (hasStock()) {
            throw StockBusinessException.cannotDeleteNotAllowed();
        }

        super.delete(actor);
        this.status = StockStatus.INACTIVE;
    }

    public void attachOrganization(Organization organization){
        if (organization == null) {
            throw StockTechnicalException.nullOrganization();
        }

        this.organization = organization;
    }

    public void addImage(Image image) {

        if (image == null) {
            throw StockTechnicalException.nullImage();
        }

        this.image = image;
    }

    public void updateImage(Image image) {

        if (image == null) {
             throw StockTechnicalException.nullImage();
        }

        this.image = image;
    }


    public void restock(RestockCommand command){
        if (command == null) {
            throw StockTechnicalException.nullRestockCommand();
        }

        StockBatch newBatch = StockBatch.create(command, this);
        this.stockBatches.add(newBatch);
    }

    public long getReceivedQuantity() {
        return stockBatches.stream()
                .mapToLong(StockBatch::getReceivedQuantity)
                .sum();
    }

    public long getAvailableQuantity() {
        return stockBatches.stream()
                .mapToLong(StockBatch::getAvailableQuantity)
                .sum();
    }

    public long getReservedQuantity() {

        return stockBatches.stream()
                .mapToLong(StockBatch::getReservedQuantity)
                .sum();
    }

    public long getConsumedQuantity() {
        return stockBatches.stream()
                .mapToLong(StockBatch::getConsumedQuantity)
                .sum();
    }


    public boolean hasStock() {
        return getAvailableQuantity() > 0;
    }

    public boolean isActive() {
        return status == StockStatus.ACTIVE;
    }

    public boolean isAvailable() {
        return isActive() && hasStock();
    }

    public int getBatchCount() {
        return stockBatches.size();
    }

    public Optional<LocalDate> getNearestExpirationDate() {
        return stockBatches.stream()
                .map(StockBatch::getExpirationDate)
                .filter(Objects::nonNull)
                .min(LocalDate::compareTo);
    }

    public BigDecimal getStockValue() {

        return stockBatches.stream()
                .map(StockBatch::getStockValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean ownedBy(Organization otherOrganization) {
        return this.organization.isSame(otherOrganization);
    }

    // ---------------------------------------------------- End Methods ------------------------------------------------------ //

}

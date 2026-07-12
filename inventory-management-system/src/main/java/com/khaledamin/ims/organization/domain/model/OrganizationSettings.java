package com.khaledamin.ims.organization.domain.model;

import com.khaledamin.ims.core.audit.AuditableEntity;
import com.khaledamin.ims.organization.domain.command.OrganizationSettingsUpdateCommand;
import com.khaledamin.ims.organization.exception.OrganizationTechnicalException;
import com.khaledamin.ims.stock.domain.model.StockAllocationStrategy;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrganizationSettings extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "settings_id")
    private Long id;

    private Long reservationExpirationMinutes;

    @Enumerated(EnumType.STRING)
    private StockAllocationStrategy allocationStrategy;


    public static OrganizationSettings create() {

        return OrganizationSettings.builder()
                .reservationExpirationMinutes(5L) // 5 minutes as default (organization owner can change it)
                .allocationStrategy(StockAllocationStrategy.getDefault())
                .build();
    }


    public void update(OrganizationSettingsUpdateCommand command) {

        if (command == null) {
            throw OrganizationTechnicalException.nullOrganizationSettingsUpdateCommand();
        }

        command.reservationExpirationMinutes()
                .ifPresent(value -> this.reservationExpirationMinutes = value);

        command.allocationStrategy()
                .ifPresent(value -> this.allocationStrategy = value);
    }
}

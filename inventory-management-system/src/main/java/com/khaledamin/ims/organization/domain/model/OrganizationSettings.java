package com.khaledamin.ims.organization.domain.model;

import com.khaledamin.ims.core.audit.AuditableEntity;
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


    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;


    public static OrganizationSettings create(Organization organization) {
        if (organization == null){
            throw OrganizationTechnicalException.nullOrganization();
        }

        return OrganizationSettings.builder()
                .organization(organization)
                .reservationExpirationMinutes(5L) // 5 minutes as default (organization owner can change it)
                .allocationStrategy(StockAllocationStrategy.getDefault())
                .build();
    }
}

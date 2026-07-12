package com.khaledamin.ims.organization.domain.command;

import com.khaledamin.ims.organization.api.dto.OrganizationSettingsUpdateRequest;
import com.khaledamin.ims.stock.domain.model.StockAllocationStrategy;

import java.util.Optional;

public record OrganizationSettingsUpdateCommand(

        Optional<Long> reservationExpirationMinutes,
        Optional<StockAllocationStrategy> allocationStrategy

) {

    public static OrganizationSettingsUpdateCommand of(
            Long reservationExpirationMinutes,
            StockAllocationStrategy allocationStrategy
    ) {

        return new OrganizationSettingsUpdateCommand(
                Optional.ofNullable(reservationExpirationMinutes),
                Optional.ofNullable(allocationStrategy)
        );
    }

    public static OrganizationSettingsUpdateCommand of(OrganizationSettingsUpdateRequest request) {

        return of(
                request.getReservationExpirationMinutes(),
                request.getAllocationStrategy()
        );
    }
}
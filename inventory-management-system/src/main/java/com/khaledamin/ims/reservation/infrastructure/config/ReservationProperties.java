package com.khaledamin.ims.reservation.infrastructure.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "application.reservation")
@Validated
public record ReservationProperties(

        @NotNull
        Scheduler scheduler

) {

    public record Scheduler(

            @Min(1)
            long intervalSeconds

    ) {}
}
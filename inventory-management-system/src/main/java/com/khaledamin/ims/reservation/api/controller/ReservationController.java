package com.khaledamin.ims.reservation.api.controller;

import com.khaledamin.ims.core.api.response.ApiActionResponse;
import com.khaledamin.ims.core.api.response.ApiResponse;
import com.khaledamin.ims.core.api.response.ApiResponseFactory;
import com.khaledamin.ims.reservation.api.documentation.annotations.ReservationConfirmApiDocs;
import com.khaledamin.ims.reservation.api.documentation.annotations.ReservationCreateApiDocs;
import com.khaledamin.ims.reservation.api.documentation.annotations.ReservationReleaseApiDocs;
import com.khaledamin.ims.reservation.api.dto.ReservationRequest;
import com.khaledamin.ims.reservation.api.dto.ReservationResponse;
import com.khaledamin.ims.reservation.application.service.ReservationManagementService;
import com.khaledamin.ims.reservation.domain.value.ReservationCode;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Reservation Management",
        description = """
                APIs for stock reservation lifecycle.

                Intended for inventory clients such as:
                - E-Commerce System
                - Order Management System
                - ERP Integrations
                - External Inventory Consumers

                Features:
                - Reserve stock
                - Confirm reservation
                - Release reservation
                """
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationManagementService reservationManagementService;


    @ReservationCreateApiDocs
    @PostMapping
    @PreAuthorize("hasAuthority('reservation_create')")
    public ResponseEntity<ApiResponse<ReservationResponse>> reserveStocks(

            @Valid
            @RequestBody
            ReservationRequest request
    ) {

        ReservationResponse response = reservationManagementService.reserveStocks(request);

        return ResponseEntity.ok(
                ApiResponseFactory.success(response)
        );
    }



    @ReservationConfirmApiDocs
    @PostMapping("/{code}/confirm")
    @PreAuthorize("hasAuthority('reservation_confirm')")
    public ResponseEntity<ApiResponse<ApiActionResponse>> confirmReservation(

            @Parameter(
                    description = "Reservation unique business identifier",
                    example = "RSV-01KABC123DEF456GHI789JKL",
                    required = true
            )
            @PathVariable
            String code
    ) {

        reservationManagementService.confirmReservation(
                ReservationCode.of(code)
        );

        return ResponseEntity.ok(
                ApiResponseFactory.success(
                        ApiActionResponse.builder()
                                .message("Reservation confirmed successfully")
                                .build()
                )
        );
    }



    @ReservationReleaseApiDocs
    @PostMapping("/{code}/release")
    @PreAuthorize("hasAuthority('reservation_release')")
    public ResponseEntity<ApiResponse<ApiActionResponse>> releaseReservation(

            @Parameter(
                    description = "Reservation unique business identifier",
                    example = "RSV-01KABC123DEF456GHI789JKL",
                    required = true
            )
            @PathVariable
            String code
    ) {

        reservationManagementService.releaseReservation(
                ReservationCode.of(code)
        );

        return ResponseEntity.ok(
                ApiResponseFactory.success(
                        ApiActionResponse.builder()
                                .message("Reservation released successfully")
                                .build()
                )
        );
    }
}
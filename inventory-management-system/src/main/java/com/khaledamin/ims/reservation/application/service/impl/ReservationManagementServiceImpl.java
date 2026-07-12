package com.khaledamin.ims.reservation.application.service.impl;

import com.khaledamin.ims.core.logging.event.BusinessEventLogger;
import com.khaledamin.ims.identity.core.model.Actor;
import com.khaledamin.ims.identity.core.provider.ActorProvider;
import com.khaledamin.ims.organization.application.service.OrganizationQueryService;
import com.khaledamin.ims.organization.domain.model.Organization;
import com.khaledamin.ims.reservation.api.dto.*;
import com.khaledamin.ims.reservation.application.service.ReservationManagementService;
import com.khaledamin.ims.reservation.application.service.ReservationQueryService;
import com.khaledamin.ims.reservation.domain.model.Reservation;
import com.khaledamin.ims.reservation.domain.model.ReservationAllocation;
import com.khaledamin.ims.reservation.domain.model.ReservationItem;
import com.khaledamin.ims.reservation.domain.policy.ReservationAccessPolicy;
import com.khaledamin.ims.reservation.domain.repository.ReservationRepository;
import com.khaledamin.ims.reservation.domain.value.ReservationCode;
import com.khaledamin.ims.reservation.domain.value.ReservationExpirationDate;
import com.khaledamin.ims.reservation.domain.value.ReservationQuantity;
import com.khaledamin.ims.stock.application.model.StockBatchAllocationPlan;
import com.khaledamin.ims.stock.application.model.StockAllocationPlan;
import com.khaledamin.ims.stock.application.resolver.StockAllocationPlannerResolver;
import com.khaledamin.ims.stock.application.service.StockAllocationPlanner;
import com.khaledamin.ims.stock.application.service.StockQueryService;
import com.khaledamin.ims.stock.domain.model.Stock;
import com.khaledamin.ims.stock.domain.model.StockAllocationStrategy;
import com.khaledamin.ims.stock.domain.value.StockCode;
import com.khaledamin.ims.stock.domain.value.StockQuantity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
public class ReservationManagementServiceImpl implements ReservationManagementService {

    private final ReservationQueryService reservationQueryService;
    private final ReservationRepository reservationRepository;
    private final ReservationAccessPolicy reservationAccessPolicy;
    private final StockAllocationPlannerResolver stockAllocationPlannerResolver;
    private final ActorProvider actorProvider;
    private final OrganizationQueryService organizationQueryService;
    private final StockQueryService stockQueryService;
    private final BusinessEventLogger businessEventLogger;

    @Transactional
    @Override
    public ReservationResponse reserveStocks(ReservationRequest request) {

        Actor actor = actorProvider.getCurrent();
        Organization organization = organizationQueryService.getByMemberIdentity(
                actor.getActorIdentity()
        );

        Map<String, StockAllocationPlan> reservationPlans = buildReservationPlans(
                request,organization,actor
        );

        List<UnavailableItemInfo> unavailableItems = findUnavailableItems(reservationPlans);

        if (!unavailableItems.isEmpty()) {
            return buildFailedReservationResponse(unavailableItems);
        }

        Reservation reservation = createReservationAggregate(reservationPlans,organization);

        reserveStockBatches(reservation);

        Reservation saved = reservationRepository.save(reservation);

        // Log the business operation event
        businessEventLogger.reservationCreated(
                saved.getCode(),
                organization.getCode()
        );

        return buildSuccessReservationResponse(saved);
    }


    @Transactional
    @Override
    public void releaseReservation(ReservationCode reservationCode){

        Reservation reservation = reservationQueryService.getByCode(reservationCode);

        Actor actor = actorProvider.getCurrent();
        Organization organization = organizationQueryService.getByMemberIdentity(
                actor.getActorIdentity()
        );

        reservationAccessPolicy.canRelease(actor,reservation);

        reservation.release();

        Reservation saved = reservationRepository.save(reservation);

        // Log the business operation event
        businessEventLogger.reservationReleased(
                saved.getCode(),
                organization.getCode()
        );

    }



    @Transactional
    @Override
    public void confirmReservation(ReservationCode reservationCode){

        Reservation reservation = reservationQueryService.getByCode(reservationCode);

        Actor actor = actorProvider.getCurrent();
        Organization organization = organizationQueryService.getByMemberIdentity(
                actor.getActorIdentity()
        );

        reservationAccessPolicy.canConfirm(actor,reservation);

        reservation.confirm();

        Reservation saved = reservationRepository.save(reservation);

        // Log the business operation event
        businessEventLogger.reservationConfirmed(
                saved.getCode(),
                organization.getCode()
        );

    }



    // ----------------------------------------------- Helper Methods ------------------------------------ //

    private Map<String, StockAllocationPlan> buildReservationPlans(
            ReservationRequest request,
            Organization organization,
            Actor actor
    ) {

        Map<String, StockAllocationPlan> reservationPlans = new HashMap<>();

        for (ReservationStockRequest requestStock : request.getStocks()) {

            StockAllocationPlan reservationPlan = buildReservationPlan(
                    requestStock,
                    organization,
                    actor
            );

            reservationPlans.put(
                    requestStock.getStockCode(),
                    reservationPlan
            );
        }

        return reservationPlans;
    }

    private StockAllocationPlan buildReservationPlan(
            ReservationStockRequest request,
            Organization organization,
            Actor actor
    ) {

        StockCode stockCode = StockCode.of(request.getStockCode());
        StockQuantity stockQuantity = StockQuantity.of(request.getQuantity());

        StockAllocationStrategy strategy = organization.getSettings().getAllocationStrategy();
        Stock stock = stockQueryService.getByCode(stockCode);

        reservationAccessPolicy.canReserve(actor,stock);

        StockAllocationPlanner planner = stockAllocationPlannerResolver.resolve(strategy);

        return planner.plan(stockCode, stockQuantity);
    }

    private List<UnavailableItemInfo> findUnavailableItems(
            Map<String, StockAllocationPlan> reservationPlans
    ) {

        List<UnavailableItemInfo> unavailableItems = new ArrayList<>();

        for (Map.Entry<String, StockAllocationPlan> entry : reservationPlans.entrySet()) {

            String stockCode = entry.getKey();

            StockAllocationPlan reservationPlan = entry.getValue();

            if (reservationPlan.reservable()) {
                continue;
            }

            unavailableItems.add(
                    UnavailableItemInfo.of(
                            stockCode,
                            reservationPlan.requestedQuantity(),
                            reservationPlan.availableQuantity()
                    )
            );
        }

        return unavailableItems;
    }

    private ReservationResponse buildFailedReservationResponse(List<UnavailableItemInfo> unavailableItems) {

        return ReservationResponse.builder()
                .success(false)
                .reservationInfo(null)
                .unavailableItemInfos(unavailableItems)
                .build();
    }

    private ReservationResponse buildSuccessReservationResponse(Reservation reservation) {

        return ReservationResponse.builder()
                .success(true)
                .reservationInfo(
                        buildReservationInfo(reservation)
                )
                .unavailableItemInfos(List.of())
                .build();
    }

    private ReservationInfo buildReservationInfo(Reservation reservation) {

        return ReservationInfo.builder()
                .reservationCode(
                        reservation.getCode()
                )
                .expiresAt(
                        reservation.getExpiresAt()
                )
                .build();
    }

    private Reservation createReservationAggregate(
            Map<String, StockAllocationPlan> reservationPlans,
            Organization organization
            ){

        Long expirationMinutes = organization.getSettings().getReservationExpirationMinutes();

        ReservationExpirationDate expirationDate = ReservationExpirationDate.of(
                Instant.now().plus(
                        expirationMinutes,
                        ChronoUnit.MINUTES
                )
        );

        Reservation reservation = Reservation.create(organization, expirationDate);

        for (StockAllocationPlan plan : reservationPlans.values()) {

            Stock stock = plan.stock();

            ReservationQuantity stockQuantity = ReservationQuantity.of(
                    plan.requestedQuantity()
            );

            ReservationItem item = reservation.addItem(stock, stockQuantity);

            for (StockBatchAllocationPlan batchPlan : plan.batchPlans()) {

                ReservationQuantity batchQuantity = ReservationQuantity.of(
                        batchPlan.quantity()
                );

                item.addAllocation(
                        batchPlan.stockBatch(),
                        batchQuantity
                );
            }
        }

        return reservation;
    }

    private void reserveStockBatches(Reservation reservation) {

        for (ReservationItem item : reservation.getItems()) {

            for (ReservationAllocation allocation : item.getAllocations()) {

                allocation.reserveStockBatch();
            }
        }
    }
}
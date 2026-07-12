package com.khaledamin.ims.reservation.domain.policy;

import com.khaledamin.ims.identity.core.model.Actor;
import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.reservation.domain.model.Reservation;
import com.khaledamin.ims.reservation.exception.ReservationPolicyException;
import com.khaledamin.ims.stock.domain.model.Stock;
import org.springframework.stereotype.Component;

@Component
public class ReservationAccessPolicy {


    public void canReserve(Actor actor, Stock stock) {
        ActorIdentity actorIdentity = actor.getActorIdentity();

        if (!stock.getOrganization().hasMember(actorIdentity)) {

            throw ReservationPolicyException.forbiddenReservation()
                    .withDebugDetails("problem","current actor is not member in this organization")
                    .withDebugDetails("stockCode", stock.getCode())
                    .withDebugDetails("organizationCode", stock.getOrganization().getCode());
        }
    }

    public void canRelease(Actor actor, Reservation reservation) {
        ActorIdentity actorIdentity = actor.getActorIdentity();

        if (!reservation.getOrganization().hasMember(actorIdentity)) {

            throw ReservationPolicyException.forbiddenRelease()
                    .withDebugDetails("problem","current actor is not member in this organization")
                    .withDebugDetails("reservationCode", reservation.getCode())
                    .withDebugDetails("organizationCode", reservation.getOrganization().getCode());
        }
    }

    public void canConfirm(Actor actor, Reservation reservation) {
        ActorIdentity actorIdentity = actor.getActorIdentity();

        if (!reservation.getOrganization().hasMember(actorIdentity)) {

            throw ReservationPolicyException.forbiddenConfirmation()
                    .withDebugDetails("problem","current actor is not member in this organization")
                    .withDebugDetails("reservationCode", reservation.getCode())
                    .withDebugDetails("organizationCode", reservation.getOrganization().getCode());
        }
    }
}

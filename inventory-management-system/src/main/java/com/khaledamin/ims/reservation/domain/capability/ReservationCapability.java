package com.khaledamin.ims.reservation.domain.capability;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.identity.capability.domain.definition.CapabilityDefinition;
import com.khaledamin.ims.identity.capability.domain.value.*;
import com.khaledamin.ims.identity.core.model.ActorType;
import lombok.Getter;

@Getter
public enum ReservationCapability implements CapabilityDefinition {


    RESERVATION_CREATE(
            "RESERVATION_CREATE",
            "reservation",
            "create",
            "Create Reservation",
            "Allows reserving stocks ",
            "CLIENT"
    ),

    RESERVATION_CONFIRM(
            "RESERVATION_CONFIRM",
            "reservation",
            "confirm",
            "Confirm Reservation",
            "Allows confirming reservations and consuming reserved stock",
            "CLIENT"
    ),

    RESERVATION_RELEASE(
            "RESERVATION_RELEASE",
            "reservation",
            "release",
            "Release Reservation",
            "Allows releasing reservations and returning reserved stock",
            "CLIENT"
    );





    private final CapabilityCode code;
    private final CapabilityResource resource;
    private final CapabilityAction action;
    private final CapabilityName name;
    private final CapabilityDescription description;
    private final ActorType expectedActorType;
    ReservationCapability(
            String code,
            String resource,
            String action,
            String name,
            String description,
            String expectedActorType
    ) {
        this.code = CapabilityCode.of(code);
        this.resource = CapabilityResource.of(resource);
        this.action = CapabilityAction.of(action);
        this.name = CapabilityName.of(name);
        this.description = CapabilityDescription.of(description);
        this.expectedActorType = ActorType.from(expectedActorType);
    }

    @Override
    public SystemDomain getDomain() {
        return SystemDomain.RESERVATION;
    }
}

package com.khaledamin.ims.reservation.domain.capability;

import com.khaledamin.ims.identity.capability.application.provider.CapabilityProvider;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ReservationCapabilityProvider implements CapabilityProvider {

    @Override
    public Set<ReservationCapability> getCapabilities() {
        return Set.of(ReservationCapability.values());
    }
}

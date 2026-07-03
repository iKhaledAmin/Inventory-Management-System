package com.khaledamin.ims.stock.domain.capability;

import com.khaledamin.ims.identity.capability.application.provider.CapabilityProvider;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class StockCapabilityProvider implements CapabilityProvider {
    @Override
    public Set<StockCapability> getCapabilities() {
        return Set.of(StockCapability.values());
    }
}

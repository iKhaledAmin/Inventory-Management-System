package com.khaledamin.ims.identity.role.domain.definition;

import com.khaledamin.ims.identity.capability.domain.value.CapabilityCode;
import com.khaledamin.ims.organization.domain.capability.OrganizationCapability;
import com.khaledamin.ims.stock.domain.capability.StockCapability;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OwnerCapability implements RoleCapabilityDefinition {
    @Override
    public RoleDefinition getRole() {
        return RoleDefinition.OWNER;
    }

    @Override
    public Set<CapabilityCode> getCapabilityCodes() {
        return Set.of(
                // Organization Capabilities
                OrganizationCapability.ORGANIZATION_UPDATE.getCode(),
                OrganizationCapability.ORGANIZATION_READ.getCode(),
                OrganizationCapability.ORGANIZATION_CREATE_CLIENT.getCode(),
                OrganizationCapability.ORGANIZATION_ROTATE_CLIENT_SECRET.getCode(),
                OrganizationCapability.ORGANIZATION_UPDATE_SETTINGS.getCode(),

                // Stock Capabilities
                StockCapability.STOCK_CREATE.getCode(),
                StockCapability.STOCK_UPDATE.getCode(),
                StockCapability.STOCK_DELETE.getCode(),
                StockCapability.STOCK_READ.getCode(),
                StockCapability.STOCK_READ_BATCHES.getCode(),
                StockCapability.STOCK_RESTOCK.getCode()
        );
    }
}

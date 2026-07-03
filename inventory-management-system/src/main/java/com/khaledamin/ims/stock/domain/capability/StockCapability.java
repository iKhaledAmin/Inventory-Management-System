package com.khaledamin.ims.stock.domain.capability;


import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.identity.capability.domain.definition.CapabilityDefinition;
import com.khaledamin.ims.identity.capability.domain.value.*;
import lombok.Getter;

@Getter
public enum StockCapability implements CapabilityDefinition {

    STOCK_CREATE(
            "STOCK_CREATE",
            "stock",
            "create",
            "Create Stock",
            "Allows creating new stock items"
    ),

    STOCK_UPDATE(
            "STOCK_UPDATE",
            "stock",
            "update",
            "Update Stock",
            "Allows updating stock items"
    ),

    STOCK_DELETE(
            "STOCK_DELETE",
            "stock",
            "delete",
            "Delete Stock",
            "Allows deleting stock items"
    ),

    STOCK_READ(
            "STOCK_READ",
            "stock",
            "read",
            "Read Stock",
            "Allows viewing stock item details"
    ),

    STOCK_READ_BATCHES(
            "STOCK_READ_BATCHES",
            "stock",
            "read_batches",
            "Read Stock Batches",
            "Allows viewing stock batch details"
    ),

    STOCK_RESTOCK(
            "STOCK_RESTOCK",
            "stock",
            "restock",
            "Restock Stock",
            "Allows restocking stock items"
    )





    ;


    private final CapabilityCode code;
    private final CapabilityResource resource;
    private final CapabilityAction action;
    private final CapabilityName name;
    private final CapabilityDescription description;
    StockCapability(
            String code,
            String resource,
            String action,
            String name,
            String description
    ) {
        this.code = CapabilityCode.of(code);
        this.resource = CapabilityResource.of(resource);
        this.action = CapabilityAction.of(action);
        this.name = CapabilityName.of(name);
        this.description = CapabilityDescription.of(description);
    }

    @Override
    public SystemDomain getDomain() {
        return SystemDomain.STOCK;
    }
}

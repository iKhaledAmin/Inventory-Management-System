package com.khaledamin.ims.stock.domain.capability;


import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.identity.capability.domain.definition.CapabilityDefinition;
import com.khaledamin.ims.identity.capability.domain.value.*;
import com.khaledamin.ims.identity.core.model.ActorType;
import lombok.Getter;

@Getter
public enum StockCapability implements CapabilityDefinition {

    STOCK_CREATE(
            "STOCK_CREATE",
            "stock",
            "create",
            "Create Stock",
            "Allows creating new stock items",
            "ACCOUNT"
    ),

    STOCK_UPDATE(
            "STOCK_UPDATE",
            "stock",
            "update",
            "Update Stock",
            "Allows updating stock items",
            "ACCOUNT"
    ),

    STOCK_DELETE(
            "STOCK_DELETE",
            "stock",
            "delete",
            "Delete Stock",
            "Allows deleting stock items",
            "ACCOUNT"
    ),

    STOCK_READ(
            "STOCK_READ",
            "stock",
            "read",
            "Read Stock",
            "Allows viewing stock item details",
            "ACCOUNT"
    ),

    STOCK_READ_BATCHES(
            "STOCK_READ_BATCHES",
            "stock",
            "read_batches",
            "Read Stock Batches",
            "Allows viewing stock batch details",
            "ACCOUNT"
    ),

    STOCK_RESTOCK(
            "STOCK_RESTOCK",
            "stock",
            "restock",
            "Restock Stock",
            "Allows restocking stock items",
            "ACCOUNT"
    ),


    STOCK_VALIDATE_EXISTENCE(
            "STOCK_VALIDATE_EXISTENCE",
            "stock",
            "validate_existence",
            "Validate Stock Existence",
            "Allows clients (machines) to validate stock item existence",
            "CLIENT"
    )





    ;


    private final CapabilityCode code;
    private final CapabilityResource resource;
    private final CapabilityAction action;
    private final CapabilityName name;
    private final CapabilityDescription description;
    private final ActorType expectedActorType;
    StockCapability(
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
        return SystemDomain.STOCK;
    }

}

package com.khaledamin.ims.identity.account.domain.capability;



import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.identity.capability.domain.definition.CapabilityDefinition;
import com.khaledamin.ims.identity.capability.domain.value.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountCapability implements CapabilityDefinition {

    // -------------------------------- Self Operations -------------------------------- //

    ACCOUNT_READ(
            "ACCOUNT_READ",
            "account",
            "read",
            "Read Own Account",
            "Allows authenticated users to view their own account information"
    ),

    ACCOUNT_UPDATE(
            "ACCOUNT_UPDATE",
            "account",
            "update",
            "Update Own Account",
            "Allows authenticated users to update their own account information"
    ),


    // -------------------------------- Admin Operations -------------------------------- //

//    ACCOUNT_READ(
//            "ACCOUNT_READ",
//            "account",
//            "read",
//            "Read Accounts",
//            "Allows viewing account clientDetails for any account"
//    ),
//
//    ACCOUNT_CREATE(
//            "ACCOUNT_CREATE",
//            "account",
//            "create",
//            "Create Accounts",
//            "Allows admins to create new accounts"
//    ),
//
//    ACCOUNT_UPDATE(
//            "ACCOUNT_UPDATE",
//            "account",
//            "update",
//            "Update Accounts",
//            "Allows updating account information for any account"
//    ),





;

    private final CapabilityCode code;
    private final CapabilityResource resource;
    private final CapabilityAction action;
    private final CapabilityName name;
    private final CapabilityDescription description;

    AccountCapability(
            String code, String resource,
            String action, String name,
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
        return SystemDomain.ACCOUNT;
    }
}
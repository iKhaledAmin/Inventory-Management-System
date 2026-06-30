package com.khaledamin.ims.identity.role.domain.definition;

import com.khaledamin.ims.identity.account.domain.capability.AccountCapability;
import com.khaledamin.ims.identity.capability.domain.value.CapabilityCode;
import org.springframework.stereotype.Component;


import java.util.Set;

@Component
public class UserCapability implements RoleCapabilityDefinition {
    @Override
    public RoleDefinition getRole() {
        return RoleDefinition.USER;
    }

    @Override
    public Set<CapabilityCode> getCapabilityCodes() {

        return Set.of(
                AccountCapability.ACCOUNT_READ.getCode(),
                AccountCapability.ACCOUNT_UPDATE.getCode()
        );
    }
}

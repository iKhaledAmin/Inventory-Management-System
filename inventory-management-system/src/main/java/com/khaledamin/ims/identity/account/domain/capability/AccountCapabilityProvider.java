package com.khaledamin.ims.identity.account.domain.capability;

import com.khaledamin.ims.identity.capability.application.provider.CapabilityProvider;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AccountCapabilityProvider implements CapabilityProvider {

    @Override
    public Set<AccountCapability> getCapabilities() {
        return Set.of(AccountCapability.values());
    }
}
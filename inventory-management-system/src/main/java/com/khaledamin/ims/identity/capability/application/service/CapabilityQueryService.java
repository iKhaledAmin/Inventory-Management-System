package com.khaledamin.ims.identity.capability.application.service;

import com.khaledamin.ims.identity.capability.domain.model.Capability;
import com.khaledamin.ims.identity.capability.domain.value.CapabilityCode;
import com.khaledamin.ims.core.constant.SystemDomain;

import java.util.List;
import java.util.Optional;

public interface CapabilityQueryService {
    boolean existsByCode(CapabilityCode code);

    Optional<Capability> getOptionalByCode(CapabilityCode code);
    Capability getByCode(CapabilityCode code);

    List<Capability> getByDomain(SystemDomain domain);
    Optional<Capability> getOptionalByCodeAndModule(CapabilityCode code, SystemDomain domain);

    List<Capability> getAll();
}

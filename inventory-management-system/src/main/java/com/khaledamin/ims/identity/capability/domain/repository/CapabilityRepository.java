package com.khaledamin.ims.identity.capability.domain.repository;

import com.khaledamin.ims.identity.capability.domain.model.Capability;
import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.identity.capability.domain.value.CapabilityCode;
import com.khaledamin.ims.identity.core.model.ActorType;

import java.util.List;
import java.util.Optional;


public interface CapabilityRepository {
    Capability save(Capability capability);
    void delete(Capability capability);

    boolean existsByCode(CapabilityCode code);

    Optional<Capability> findByCode(CapabilityCode code);

    Optional<Capability> findByCodeAndDomain(CapabilityCode code, SystemDomain domain);

    List<Capability> findAllByDomain(SystemDomain domain);

    List<Capability> findAll();


    void flush();

    List<Capability> findAllByExpectedActorType(ActorType actorType);
}

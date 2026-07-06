package com.khaledamin.ims.identity.capability.infrastructure.persistence;

import com.khaledamin.ims.identity.capability.domain.model.Capability;
import com.khaledamin.ims.core.persistence.BaseRepository;
import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.identity.core.model.ActorType;

import java.util.List;
import java.util.Optional;

public interface CapabilityJpaRepository extends BaseRepository<Capability, Long> {
    boolean existsByCode(String code);
    Optional<Capability> findByCode(String code);

    List<Capability> findAllByDomain(SystemDomain domain);

    Optional<Capability> findByCodeAndDomain(String name, String domain);

    List<Capability> findAllByExpectedActorType(ActorType actorType);
}

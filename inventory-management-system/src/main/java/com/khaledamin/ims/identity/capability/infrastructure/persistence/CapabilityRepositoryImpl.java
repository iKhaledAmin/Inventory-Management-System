package com.khaledamin.ims.identity.capability.infrastructure.persistence;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.identity.capability.domain.model.Capability;
import com.khaledamin.ims.identity.capability.domain.repository.CapabilityRepository;
import com.khaledamin.ims.identity.capability.domain.value.CapabilityCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
public class CapabilityRepositoryImpl implements CapabilityRepository {

    private final CapabilityJpaRepository capabilityJpaRepository;

    @Override
    public Capability save(Capability capability) {
        return capabilityJpaRepository.save(capability);
    }

    @Override
    public void delete(Capability capability) {
        capabilityJpaRepository.delete(capability);
    }

    @Override
    public boolean existsByCode(CapabilityCode code) {
        return capabilityJpaRepository.existsByCode(code.value());
    }

    @Override
    public Optional<Capability> findByCode(CapabilityCode code) {
        return capabilityJpaRepository.findByCode(code.value());
    }

    @Override
    public Optional<Capability> findByCodeAndDomain(CapabilityCode code, SystemDomain domain) {
        return capabilityJpaRepository.findByCodeAndDomain(code.value(), domain.name());
    }

    @Override
    public List<Capability> findAllByDomain(SystemDomain domain) {
        return capabilityJpaRepository.findAllByDomain(domain);
    }

    @Override
    public List<Capability> findAll() {
        return capabilityJpaRepository.findAll();
    }

    @Override
    public void flush() {
        capabilityJpaRepository.flush();
    }

}

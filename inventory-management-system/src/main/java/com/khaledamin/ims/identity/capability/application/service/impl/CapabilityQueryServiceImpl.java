package com.khaledamin.ims.identity.capability.application.service.impl;

import com.khaledamin.ims.identity.capability.application.service.CapabilityQueryService;
import com.khaledamin.ims.identity.capability.domain.model.Capability;
import com.khaledamin.ims.identity.capability.domain.repository.CapabilityRepository;
import com.khaledamin.ims.identity.capability.domain.value.CapabilityCode;
import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.identity.capability.exception.CapabilityBusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CapabilityQueryServiceImpl implements CapabilityQueryService {

    private final CapabilityRepository capabilityRepository;

    @Override
    public boolean existsByCode(CapabilityCode code) {
        return capabilityRepository.existsByCode(code);
    }

    @Override
    public Optional<Capability> getOptionalByCode(CapabilityCode code){
        return capabilityRepository.findByCode(code);
    }

    @Override
    public Capability getByCode(CapabilityCode code) {
        return getOptionalByCode(code)
                .orElseThrow(() -> CapabilityBusinessException.notFound()
                        .withClientDetails("reason", "Capability not found")
                        .withClientDetails("code", code.toString())
                );
    }


    @Override
    public Optional<Capability> getOptionalByCodeAndModule(CapabilityCode code, SystemDomain domain) {
        return capabilityRepository.findByCodeAndDomain(code, domain);
    }


    @Override
    public List<Capability> getAll() {
        return capabilityRepository.findAll();
    }

    @Override
    public List<Capability> getByDomain(SystemDomain domain) {
        return capabilityRepository.findAllByDomain(domain);
    }

}

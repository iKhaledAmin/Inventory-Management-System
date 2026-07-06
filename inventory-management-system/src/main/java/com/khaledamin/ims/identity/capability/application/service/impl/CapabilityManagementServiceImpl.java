package com.khaledamin.ims.identity.capability.application.service.impl;


import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.logging.event.BusinessEventLogger;
import com.khaledamin.ims.identity.capability.application.service.CapabilityManagementService;
import com.khaledamin.ims.identity.capability.application.service.CapabilityQueryService;
import com.khaledamin.ims.identity.capability.domain.command.CapabilityCreateCommand;
import com.khaledamin.ims.identity.capability.domain.command.CapabilityUpdateCommand;
import com.khaledamin.ims.identity.capability.domain.definition.CapabilityDefinition;
import com.khaledamin.ims.identity.capability.domain.model.Capability;
import com.khaledamin.ims.identity.capability.domain.repository.CapabilityRepository;
import com.khaledamin.ims.identity.capability.domain.value.CapabilityCode;
import com.khaledamin.ims.identity.capability.exception.CapabilityTechnicalException;
import com.khaledamin.ims.identity.role.application.service.RoleCapabilityManagementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class CapabilityManagementServiceImpl implements CapabilityManagementService {
    private final CapabilityRepository capabilityRepository;
    private final CapabilityQueryService capabilityQueryService;
    private final BusinessEventLogger businessEventLogger;
    private final RoleCapabilityManagementService roleCapabilityManagementService;


    @Transactional
    @Override
    public Capability create(CapabilityDefinition definition) {
        if (definition == null){
            throw CapabilityTechnicalException.nullCreateCommand();
        }

        CapabilityCreateCommand command = CapabilityCreateCommand.of(
                definition.getCode().toString(),
                definition.getResource().toString(),
                definition.getAction().toString(),
                definition.getName().toString(),
                definition.getDescription().toString(),
                definition.getDomain(),
                definition.getExpectedActorType()
        );

        Capability newCapability = Capability.create(command);
        Capability saved = capabilityRepository.save(newCapability);

        businessEventLogger.capabilityCreated(
                saved.getCode()
        );

        return saved;
    }

    @Transactional
    @Override
    public Capability update(CapabilityCode code, CapabilityDefinition definition) {
        if (definition == null){
            throw CapabilityTechnicalException.nullUpdateCommand();
        }

        Capability existingCapability = capabilityQueryService.getByCode(code);

        if (!existingCapability.requiresUpdate(definition)) {
            return existingCapability;
        }

        CapabilityUpdateCommand command = CapabilityUpdateCommand.of(
                definition.getName().toString(),
                definition.getDescription().toString()
        );

        existingCapability.update(command);

        Capability saved = capabilityRepository.save(existingCapability);

        businessEventLogger.capabilityUpdated(
                saved.getCode()
        );

        return saved;
    }


    // this is a very dangerous method need adjustment if you will be used
    @Override
    @Transactional
    public void delete(CapabilityCode code) {

        Capability capability = capabilityQueryService.getByCode(code);

        roleCapabilityManagementService.cleanupCapability(code);

        capabilityRepository.flush();

        capabilityRepository.delete(capability);

        businessEventLogger.capabilityDeleted(code.toString());

    }

    @Transactional(readOnly = true)
    @Override
    public Capability viewCapability(CapabilityCode code) {

        Capability capability = capabilityQueryService.getByCode(code);

        businessEventLogger.capabilityViewed(
                capability.getCode()
        );

        return capability;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Capability> listCapabilities(SystemDomain domain) {

        List<Capability> capabilities =
                domain == null ? capabilityQueryService.getAll() : capabilityQueryService.getByDomain(domain);

        businessEventLogger.capabilityListed(
                domain != null ? domain.name() : "ALL"
        );

        return capabilities;
    }

}

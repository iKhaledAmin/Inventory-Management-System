package com.khaledamin.ims.identity.capability.application.service;

import com.khaledamin.ims.identity.capability.domain.model.Capability;
import com.khaledamin.ims.identity.capability.domain.definition.CapabilityDefinition;
import com.khaledamin.ims.identity.capability.domain.value.CapabilityCode;
import com.khaledamin.ims.core.constant.SystemDomain;

import java.util.List;

public interface CapabilityManagementService {

    Capability create(CapabilityDefinition capability);
    Capability update(CapabilityCode code,CapabilityDefinition definition);
    void delete(CapabilityCode code);


    Capability viewCapability(CapabilityCode code);
    List<Capability> listCapabilities(SystemDomain domain);

}

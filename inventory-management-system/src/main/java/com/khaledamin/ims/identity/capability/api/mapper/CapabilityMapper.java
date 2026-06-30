package com.khaledamin.ims.identity.capability.api.mapper;


import com.khaledamin.ims.core.mapper.BaseMapper;
import com.khaledamin.ims.core.mapper.GlobalMapperConfig;
import com.khaledamin.ims.identity.capability.api.dto.CapabilityResponse;
import com.khaledamin.ims.identity.capability.domain.model.Capability;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface CapabilityMapper extends BaseMapper<CapabilityResponse, Capability> {

}

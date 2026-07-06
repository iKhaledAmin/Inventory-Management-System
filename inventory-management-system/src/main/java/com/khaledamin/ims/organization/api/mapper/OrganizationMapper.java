package com.khaledamin.ims.organization.api.mapper;


import com.khaledamin.ims.core.mapper.BaseMapper;
import com.khaledamin.ims.core.mapper.GlobalMapperConfig;
import com.khaledamin.ims.identity.client.api.mapper.ClientMapper;
import com.khaledamin.ims.organization.api.dto.OrganizationResponse;
import com.khaledamin.ims.organization.domain.model.Organization;
import com.khaledamin.ims.media.image.api.mapper.ImageMapper;
import org.mapstruct.Mapper;

@Mapper(
        config = GlobalMapperConfig.class,
        uses = {ImageMapper.class, ClientMapper.class}

)
public interface OrganizationMapper extends BaseMapper<OrganizationResponse, Organization> {
}

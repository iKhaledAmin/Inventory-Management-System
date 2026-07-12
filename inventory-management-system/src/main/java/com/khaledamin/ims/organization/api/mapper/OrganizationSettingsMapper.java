package com.khaledamin.ims.organization.api.mapper;

import com.khaledamin.ims.core.mapper.BaseMapper;
import com.khaledamin.ims.core.mapper.GlobalMapperConfig;
import com.khaledamin.ims.organization.api.dto.OrganizationSettingsResponse;
import com.khaledamin.ims.organization.domain.model.OrganizationSettings;
import org.mapstruct.Mapper;

@Mapper(
        config = GlobalMapperConfig.class
)
public interface OrganizationSettingsMapper extends
        BaseMapper<OrganizationSettingsResponse, OrganizationSettings> {
}
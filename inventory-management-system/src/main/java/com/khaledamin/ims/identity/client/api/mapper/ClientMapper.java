package com.khaledamin.ims.identity.client.api.mapper;

import com.khaledamin.ims.core.mapper.BaseMapper;
import com.khaledamin.ims.core.mapper.GlobalMapperConfig;
import com.khaledamin.ims.identity.client.api.dto.ClientResponse;
import com.khaledamin.ims.identity.client.domain.model.Client;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface ClientMapper extends BaseMapper<ClientResponse, Client> {
}

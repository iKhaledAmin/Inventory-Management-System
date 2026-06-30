package com.khaledamin.ims.identity.core.mapper;


import com.khaledamin.ims.identity.core.dto.IdentityResponse;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.core.mapper.GlobalMapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface IdentityMapper {

    IdentityResponse toResponse(ActorIdentity identity);

    default String map(ActorCode actorCode) {
        return actorCode == null ? null : actorCode.toString();
    }

}
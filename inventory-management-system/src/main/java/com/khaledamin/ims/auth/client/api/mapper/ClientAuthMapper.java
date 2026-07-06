package com.khaledamin.ims.auth.client.api.mapper;

import com.khaledamin.ims.auth.client.infrastructure.principal.ClientPrincipal;
import com.khaledamin.ims.auth.client.api.dto.ClientTokenResponse;
import com.khaledamin.ims.auth.security.core.jwt.JwtMapper;
import com.khaledamin.ims.core.mapper.GlobalMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mapper(
        config = GlobalMapperConfig.class,
        uses = JwtMapper.class
)
public interface ClientAuthMapper {

    @Mapping(target = "client", source = "principal")
    @Mapping(target = "token", source = "jwtToken")
    ClientTokenResponse toTokenResponse(String jwtToken, ClientPrincipal principal);

    @Mapping(target = "clientCode", expression = "java(principal.getActorCode().toString())")
    @Mapping(target = "authorities", expression = "java(mapAuthorities(principal.getAuthorities()))")
    ClientTokenResponse.ClientInfo toClientInfo(ClientPrincipal principal);

    default List<String> mapAuthorities(Set<String> authorities) {
        return authorities == null ? List.of() : new ArrayList<>(authorities);
    }
}
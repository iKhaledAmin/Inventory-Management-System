package com.khaledamin.ims.identity.role.api.mapper;




import com.khaledamin.ims.core.mapper.BaseMapper;
import com.khaledamin.ims.core.mapper.GlobalMapperConfig;
import com.khaledamin.ims.identity.capability.api.mapper.CapabilityMapper;
import com.khaledamin.ims.identity.role.api.dto.RoleResponse;
import com.khaledamin.ims.identity.role.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;


@Mapper(
        config = GlobalMapperConfig.class,
        uses = {CapabilityMapper.class}
)
public interface RoleMapper extends BaseMapper<RoleResponse, Role> {

    @Named("roleToName")
    default String map(Role role) {
        return role.getName();
    }

    @Named("rolesToNames")
    default List<String> mapList(Set<Role> roles) {
        if (roles == null) return List.of();
        return roles.stream()
                .map(Role::getName)
                .toList();
    }


}

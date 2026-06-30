package com.khaledamin.ims.identity.role.infrastructure.persistence;

import com.khaledamin.ims.identity.role.domain.model.Role;
import com.khaledamin.ims.core.persistence.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleJpaRepository extends BaseRepository<Role, Long> {

    Optional<Role> findByName(String name);

    List<Role> findAllByDefaultRoleTrue();

    boolean existsByDisplayName(String roleDisplayName);
    boolean existsByName(String name);

    List<Role> findAllByNameIn(List<String> names);


    @Query("""
        select distinct r
        from Role r
        join r.roleCapabilities rc
        join rc.capability c
        where c.code = :code
       """)
    List<Role> findAllByCapabilityCode(@Param("code") String code);
}

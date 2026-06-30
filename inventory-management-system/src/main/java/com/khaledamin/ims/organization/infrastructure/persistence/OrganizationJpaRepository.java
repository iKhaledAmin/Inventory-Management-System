package com.khaledamin.ims.organization.infrastructure.persistence;



import com.khaledamin.ims.core.persistence.BaseRepository;
import com.khaledamin.ims.organization.domain.model.Organization;

import java.util.Optional;

public interface OrganizationJpaRepository extends BaseRepository<Organization, Long> {
    Optional<Organization> findByCode(String code);

    Optional<Organization> findByOwnerId(Long ownerId);
}

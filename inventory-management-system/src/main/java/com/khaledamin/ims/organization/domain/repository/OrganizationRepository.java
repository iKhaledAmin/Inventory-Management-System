package com.khaledamin.ims.organization.domain.repository;

import com.khaledamin.ims.organization.domain.model.Organization;

import java.util.Optional;

public interface OrganizationRepository {

    Organization save(Organization category);

    Optional<Organization> findByCode(String code);

    Optional<Organization> findByOwnerId(Long ownerId);
}

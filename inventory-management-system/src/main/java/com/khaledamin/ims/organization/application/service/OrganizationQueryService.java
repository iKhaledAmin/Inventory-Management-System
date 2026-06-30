package com.khaledamin.ims.organization.application.service;

import com.khaledamin.ims.organization.domain.model.Organization;
import com.khaledamin.ims.organization.domain.value.OrganizationCode;

import java.util.Optional;

public interface OrganizationQueryService {
    Optional<Organization> getOptionalByCode(OrganizationCode code);
    Organization getByCode(OrganizationCode code);

    Optional<Organization> getOptionalByOwnerId(Long ownerId);
    Organization getByOwnerId(Long ownerId);
}

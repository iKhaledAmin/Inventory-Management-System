package com.khaledamin.ims.organization.application.service;

import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.organization.domain.model.Organization;
import com.khaledamin.ims.organization.domain.value.OrganizationCode;

import java.util.Optional;

public interface OrganizationQueryService {
    Optional<Organization> getOptionalByCode(OrganizationCode code);
    Organization getByCode(OrganizationCode code);

    Optional<Organization> getOptionalByOwnerIdentity(ActorIdentity ownerIdentity);
    Organization getByOwnerIdentity(ActorIdentity ownerIdentity);

    Optional<Organization> getOptionalByMemberIdentity(ActorIdentity memberIdentity);
    Organization getByMemberIdentity(ActorIdentity memberIdentity);
}

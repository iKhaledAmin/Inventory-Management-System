package com.khaledamin.ims.organization.application.service.impl;

import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.organization.application.service.OrganizationQueryService;
import com.khaledamin.ims.organization.domain.model.Organization;
import com.khaledamin.ims.organization.domain.repository.OrganizationRepository;
import com.khaledamin.ims.organization.domain.value.OrganizationCode;
import com.khaledamin.ims.organization.exception.OrganizationBusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrganizationQueryServiceImpl implements OrganizationQueryService {

    private final OrganizationRepository organizationRepository;

    @Override
    public Optional<Organization> getOptionalByCode(OrganizationCode code) {
        return organizationRepository.findByCode(code.toString());
    }

    @Override
    public Organization getByCode(OrganizationCode code) {
        return getOptionalByCode(code)
                .orElseThrow(() -> OrganizationBusinessException.notFound()
                        .withDebugDetails("reason", "Organization not found")
                        .withDebugDetails("code", code.toString())
                );
    }


    @Override
    public Optional<Organization> getOptionalByOwnerIdentity(ActorIdentity ownerIdentity) {
        return organizationRepository.findByOwnerIdentity(ownerIdentity);
    }

    @Override
    public Organization getByOwnerIdentity(ActorIdentity ownerIdentity) {
        return getOptionalByOwnerIdentity(ownerIdentity)
                .orElseThrow(() -> OrganizationBusinessException.notFound()
                        .withDebugDetails("reason", "Organization not found")
                        .withDebugDetails("ownerIdentity", ownerIdentity.toString())
                );
    }
}

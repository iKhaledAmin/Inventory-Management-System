package com.khaledamin.ims.organization.infrastructure.persistence;

import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.organization.domain.model.Organization;
import com.khaledamin.ims.organization.domain.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class OrganizationRepositoryImp implements OrganizationRepository {
    private final OrganizationJpaRepository organizationJpaRepository;

    @Override
    public Organization save(Organization category) {
        return organizationJpaRepository.save(category);
    }

    @Override
    public Optional<Organization> findByCode(String code) {
        return organizationJpaRepository.findByCode(code);
    }

    @Override
    public Optional<Organization> findByOwnerIdentity(ActorIdentity ownerIdentity) {
        return organizationJpaRepository.findByOwnerIdentity(ownerIdentity);
    }
}

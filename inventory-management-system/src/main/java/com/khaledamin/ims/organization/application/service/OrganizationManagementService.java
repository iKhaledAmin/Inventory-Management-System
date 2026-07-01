package com.khaledamin.ims.organization.application.service;

import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.organization.api.dto.OrganizationCreateRequest;
import com.khaledamin.ims.organization.api.dto.OrganizationUpdateRequest;
import com.khaledamin.ims.organization.domain.model.Organization;

public interface OrganizationManagementService {

    Organization create(OrganizationCreateRequest request, ActorCode ownerCode);

    Organization update(OrganizationUpdateRequest request);

    Organization view();

}

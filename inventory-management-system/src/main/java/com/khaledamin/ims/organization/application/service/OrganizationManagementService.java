package com.khaledamin.ims.organization.application.service;

import com.khaledamin.ims.identity.account.domain.model.Account;
import com.khaledamin.ims.organization.api.dto.OrganizationUpdateRequest;
import com.khaledamin.ims.organization.domain.model.Organization;
import com.khaledamin.ims.organization.domain.value.OrganizationDescription;
import com.khaledamin.ims.organization.domain.value.OrganizationName;

public interface OrganizationManagementService {

    Organization create(OrganizationName name , OrganizationDescription description, Account owner);

    Organization update(OrganizationUpdateRequest request);

    Organization view();

}

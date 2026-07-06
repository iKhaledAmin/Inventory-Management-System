package com.khaledamin.ims.organization.domain;

import com.khaledamin.ims.identity.client.domain.model.Client;
import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.organization.domain.model.Organization;
import com.khaledamin.ims.organization.exception.OrganizationPolicyException;
import org.springframework.stereotype.Component;

@Component
public class OrganizationAccessPolicy {

    public void canRotateClientSecret(Client client, Organization organization) {
        ActorIdentity clientIdentity = client.getActorIdentity();

        if (!organization.hasMember(clientIdentity)){
            throw OrganizationPolicyException.forbiddenRotateClientSecret()
                    .withDebugDetails("problem","client does not belong to organization")
                    .withDebugDetails("clintCode", client.getClientCode())
                    .withDebugDetails("organizationCode", organization.getCode());
        }
    }
}

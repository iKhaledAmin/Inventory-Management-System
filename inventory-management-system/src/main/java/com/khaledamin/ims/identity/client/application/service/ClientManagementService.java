package com.khaledamin.ims.identity.client.application.service;

import com.khaledamin.ims.identity.client.api.dto.ClientCreateRequest;
import com.khaledamin.ims.identity.client.domain.model.Client;
import com.khaledamin.ims.identity.core.model.ActorCode;

public interface ClientManagementService {
    Client create (ClientCreateRequest request);
    String rotateSecret(ActorCode clientCode);
}

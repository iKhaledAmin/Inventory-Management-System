package com.khaledamin.ims.identity.client.application.service;

import com.khaledamin.ims.identity.client.domain.model.Client;
import com.khaledamin.ims.identity.client.domain.value.ClientId;
import com.khaledamin.ims.identity.core.model.ActorCode;

import java.util.Optional;

public interface ClientQueryService {
    Optional<Client> getOptionalByClientId(String clientId);
    boolean existsByClientId(ClientId clientId);

    Optional<Client> getOptionalByClientCode(ActorCode clientCode);
    Client getByClientCode(ActorCode clientCode);


}


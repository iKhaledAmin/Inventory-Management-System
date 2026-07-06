package com.khaledamin.ims.identity.client.domain.repository;

import com.khaledamin.ims.identity.client.domain.model.Client;

import java.util.Optional;

public interface ClientRepository {

    Client save(Client client);

    Optional<Client> findByClientId(String clientId);

    boolean existsByClientId(String string);

    Optional<Client> findByClientCode(String clientCode);


}

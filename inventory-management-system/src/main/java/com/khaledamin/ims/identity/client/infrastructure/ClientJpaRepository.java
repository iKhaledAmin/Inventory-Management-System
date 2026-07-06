package com.khaledamin.ims.identity.client.infrastructure;

import com.khaledamin.ims.core.persistence.BaseRepository;
import com.khaledamin.ims.identity.client.domain.model.Client;

import java.util.Optional;

public interface ClientJpaRepository  extends BaseRepository<Client,Long> {

    Optional<Client> findByClientId(String clientId);

    Optional<Client> findByClientCode(String clientCode);

    boolean existsByClientId(String string);
}

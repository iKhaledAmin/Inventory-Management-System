package com.khaledamin.ims.identity.client.infrastructure;

import com.khaledamin.ims.identity.client.domain.model.Client;
import com.khaledamin.ims.identity.client.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class ClientRepositoryImpl implements ClientRepository {
    private final ClientJpaRepository clientJpaRepository;

    @Override
    public Client save(Client client) {
        return clientJpaRepository.save(client);
    }

    @Override
    public Optional<Client> findByClientId(String clientId) {
        return clientJpaRepository.findByClientId(clientId);
    }

    @Override
    public boolean existsByClientId(String string) {
        return clientJpaRepository.existsByClientId(string);
    }

    @Override
    public Optional<Client> findByClientCode(String clientCode) {
        return clientJpaRepository.findByClientCode(clientCode);
    }
}

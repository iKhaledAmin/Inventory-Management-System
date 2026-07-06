package com.khaledamin.ims.identity.client.application.service.impl;

import com.khaledamin.ims.identity.client.application.service.ClientQueryService;
import com.khaledamin.ims.identity.client.domain.exception.ClientBusinessException;
import com.khaledamin.ims.identity.client.domain.model.Client;
import com.khaledamin.ims.identity.client.domain.repository.ClientRepository;
import com.khaledamin.ims.identity.client.domain.value.ClientId;
import com.khaledamin.ims.identity.core.model.ActorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientQueryServiceImpl implements ClientQueryService {
    private final ClientRepository clientRepository;

    @Override
    public Optional<Client> getOptionalByClientId(String clientId) {
        return clientRepository.findByClientId(clientId);
    }

    @Override
    public boolean existsByClientId(ClientId clientId) {
        return clientRepository.existsByClientId(clientId.toString());
    }

    @Override
    public Optional<Client> getOptionalByClientCode(ActorCode clientCode) {
        return clientRepository.findByClientCode(clientCode.toString());
    }

    @Override
    public Client getByClientCode(ActorCode clientCode) {
        return getOptionalByClientCode(clientCode)
                .orElseThrow(() -> ClientBusinessException.notFound()
                        .withDebugDetails("problem", "Client not found")
                        .withDebugDetails("code", clientCode.toString())
                );
    }
}

package com.khaledamin.ims.identity.client.application.service.impl;

import com.khaledamin.ims.core.logging.event.BusinessEventLogger;
import com.khaledamin.ims.identity.capability.application.service.CapabilityQueryService;
import com.khaledamin.ims.identity.capability.domain.model.Capability;
import com.khaledamin.ims.identity.client.api.dto.ClientCreateRequest;
import com.khaledamin.ims.identity.client.application.service.ClientManagementService;
import com.khaledamin.ims.identity.client.application.service.ClientQueryService;
import com.khaledamin.ims.identity.client.domain.command.ClientCreateCommand;
import com.khaledamin.ims.identity.client.domain.exception.ClientBusinessException;
import com.khaledamin.ims.identity.client.domain.generator.ClientSecretGenerator;
import com.khaledamin.ims.identity.client.domain.model.Client;
import com.khaledamin.ims.identity.client.domain.repository.ClientRepository;
import com.khaledamin.ims.identity.client.domain.value.ClientHashSecret;
import com.khaledamin.ims.identity.client.domain.value.ClientId;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorType;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientManagementServiceImpl implements ClientManagementService {
    private final ClientRepository clientRepository;
    private final ClientQueryService clientQueryService;
    private final CapabilityQueryService capabilityQueryService;
    private final BusinessEventLogger businessEventLogger;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Client create (ClientCreateRequest request) {

        ensureClientIdUniqueness(request);

        String initialHashSecret = generateHashSecret(
                ClientSecretGenerator.generate()
        );

        ClientCreateCommand command = ClientCreateCommand.of(request, initialHashSecret);

        Client newClient = Client.create(command);

        List<Capability> capabilities = capabilityQueryService.getAllByExpectedActorType(
                ActorType.CLIENT
        );

        newClient.assignCapabilities(capabilities);

        Client saved = clientRepository.save(newClient);

        businessEventLogger.clientCreated(
                saved.getClientCode()
        );

        return saved;
    }

    @Transactional
    @Override
    public String rotateSecret(ActorCode clientCode){

        Client client = clientQueryService.getByClientCode(clientCode);

        String newRawSecret =  ClientSecretGenerator.generate();
        String newHashSecret = generateHashSecret(newRawSecret);

        client.rotateSecret(
                ClientHashSecret.of(newHashSecret)
        );

        Client saved = clientRepository.save(client);

        businessEventLogger.clientSecretRotated(
                saved.getClientCode()
        );

        return newRawSecret;
    }




    private String generateHashSecret(String rawSecret){

        return passwordEncoder.encode(rawSecret);
    }

    private void ensureClientIdUniqueness(ClientCreateRequest request) {
        ClientId clientId = ClientId.of(
                request.getClientId()
        );
        if (clientQueryService.existsByClientId(clientId)){
            throw ClientBusinessException.clientIdAlreadyExists()
                    .withDebugDetails("clientId",clientId.toString());
        }
    }
}

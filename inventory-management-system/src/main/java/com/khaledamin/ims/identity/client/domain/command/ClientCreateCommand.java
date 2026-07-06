package com.khaledamin.ims.identity.client.domain.command;

import com.khaledamin.ims.identity.client.api.dto.ClientCreateRequest;
import com.khaledamin.ims.identity.client.domain.value.ClientDescription;
import com.khaledamin.ims.identity.client.domain.value.ClientName;
import com.khaledamin.ims.identity.client.domain.value.ClientHashSecret;
import com.khaledamin.ims.identity.client.domain.value.ClientId;

public record ClientCreateCommand (
        ClientId clientId,
        ClientHashSecret secretHash,
        ClientName name,
        ClientDescription description
){
    public static ClientCreateCommand of(
            String clientId,
            String hashSecret,
            String name,
            String description) {
        return new ClientCreateCommand(
                ClientId.of(clientId),
                ClientHashSecret.of(hashSecret),
                ClientName.of(name),
                ClientDescription.of(description)
        );
    }

    public static ClientCreateCommand of(ClientCreateRequest request, String hashSecret) {
        return of(
                request.getClientId(),
                hashSecret,
                request.getName(),
                request.getDescription()
        );
    }
}

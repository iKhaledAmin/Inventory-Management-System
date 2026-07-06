package com.khaledamin.ims.identity.client.domain.command;

import com.khaledamin.ims.identity.client.domain.value.ClientDescription;
import com.khaledamin.ims.identity.client.domain.value.ClientName;

import java.util.Optional;

public record ClientUpdateCommand (Optional<ClientName> name, Optional<ClientDescription> description) {


    public static ClientUpdateCommand of(String name, String description)
    {
        return new ClientUpdateCommand(
                Optional.ofNullable(name).map(ClientName::of),
                Optional.ofNullable(description).map(ClientDescription::of)
        );
    }
}

package com.khaledamin.ims.identity.client.domain.model;


import com.khaledamin.ims.core.audit.LifecycleAuditableEntity;
import com.khaledamin.ims.identity.capability.domain.model.Capability;
import com.khaledamin.ims.identity.client.domain.command.ClientCreateCommand;
import com.khaledamin.ims.identity.client.domain.command.ClientUpdateCommand;
import com.khaledamin.ims.identity.client.domain.exception.ClientBusinessException;
import com.khaledamin.ims.identity.client.domain.exception.ClientTechnicalException;
import com.khaledamin.ims.identity.client.domain.value.ClientHashSecret;
import com.khaledamin.ims.identity.core.generator.ActorCodeGenerator;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorSource;
import com.khaledamin.ims.identity.core.model.ActorType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "clients")
@SQLRestriction("deleted_at IS NULL")
public class Client extends LifecycleAuditableEntity implements ActorSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "client_code", nullable = false, updatable = false, unique = true)
    private String clientCode;



    /**
     * Public identifier used for authentication (machine identifier).
     */
    @Column(name = "client_id", nullable = false, updatable = false,unique = true)
    private String clientId;

    /**
     * Hashed secret used for authentication.
     */
    @Column(name = "client_secret_hash", nullable = false)
    private String clientSecretHash;



    /**
     * Name of the client (human label).
     */
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ClientStatus status = ClientStatus.getDefault();


    // -------------------------------------------- Relationships --------------------------------------------

    @Builder.Default
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientCapability> clientCapabilities = new ArrayList<>();

    // -------------------------------------------- End Relationships --------------------------------------------

    // ------------------------------------- Actor Source ------------------------------------- //

    @Override
    public ActorType getActorType() {
        return ActorType.CLIENT;
    }

    @Override
    public ActorCode getActorCode() {
        return ActorCode.of(clientCode);
    }

    // ------------------------------------- End Actor Source ---------------------------------- //



    // ------------------------------------ Business Methods -------------------------------- //

    public static Client create(ClientCreateCommand command){

        if (command == null ){
            throw ClientTechnicalException.nullCreateCommand();
        }

        String code = ActorCodeGenerator.generate(ActorType.CLIENT).toString();

        return Client.builder()
                .clientCode(code)
                .clientId(command.clientId().toString())
                .clientSecretHash(command.secretHash().toString())
                .name(command.name().toString())
                .description(command.description().toString())
                .clientCapabilities(new ArrayList<>())
                .build();
    }

    public void update(ClientUpdateCommand command){

        if (command == null ){
            throw ClientTechnicalException.nullUpdateCommand();
        }

        command.name().ifPresent(name -> this.name = name.toString());
        command.description().ifPresent(description -> this.description = description.toString());

    }

    public void assignCapability(Capability capability) {
        if (capability == null){
            throw ClientTechnicalException.nullCapability();
        }

        ensureCanAssignCapability(capability);

        ClientCapability clientCapability = ClientCapability.create(capability, this);

        clientCapabilities.add(clientCapability);
    }

    public void assignCapabilities(List<Capability> capabilities){
        capabilities.forEach(this::assignCapability);
    }

    public void removeCapability(Capability capability){
        if (capability == null){
            throw ClientTechnicalException.nullCapability();
        }

        ensureCanRemoveCapability(capability);

        clientCapabilities.removeIf(rc -> rc.getCapability().equals(capability));

    }


    public boolean hasCapability(Capability capability){

        if (capability == null){
            return false;
        }

        return clientCapabilities.stream()
                .anyMatch(
                        clientCapability ->
                                clientCapability.getCapability().getId().equals(capability.getId())
                );
    }

    public Set<Capability> getCapabilities() {
        return clientCapabilities
                .stream()
                .map(ClientCapability::getCapability)
                .collect(Collectors.toSet());
    }

    public Set<String> getAuthority() {
        return getCapabilities()
                .stream()
                .map(Capability::toAuthority)
                .collect(Collectors.toSet());
    }

    public void activate() { this.status = ClientStatus.ACTIVE; }
    public void disable()  { this.status = ClientStatus.DISABLED; }

    public void rotateSecret(ClientHashSecret hashSecret){
        if (hashSecret == null){
            throw ClientTechnicalException.nullHashSecret();
        }

        this.setClientSecretHash(
                hashSecret.toString()
        );
    }




    private void ensureCanAssignCapability(Capability capability) {
        if (this.hasCapability(capability)) {
            throw ClientBusinessException.capabilityAlreadyAssigned()
                    .withDebugDetails("capabilityCode", capability.getCode())
                    .withDebugDetails("clientCode",this.clientCode);
        }
    }

    private void ensureCanRemoveCapability(Capability capability) {

        if (!this.hasCapability(capability)) {
            throw ClientBusinessException.capabilityNotAssigned()
                    .withDebugDetails("capabilityCode", capability.getCode())
                    .withDebugDetails("clientCode",this.clientCode);
        }
    }
    // ----------------------------------- End Business Methods ----------------------------- //
}
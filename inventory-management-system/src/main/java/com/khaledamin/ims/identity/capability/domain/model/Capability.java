package com.khaledamin.ims.identity.capability.domain.model;

import com.khaledamin.ims.core.audit.AuditableEntity;
import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.identity.capability.domain.command.CapabilityCreateCommand;
import com.khaledamin.ims.identity.capability.domain.command.CapabilityUpdateCommand;
import com.khaledamin.ims.identity.capability.domain.definition.CapabilityDefinition;
import com.khaledamin.ims.identity.capability.exception.CapabilityTechnicalException;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "capabilities",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_capability_name_module",
                        columnNames = {"name", "module"}
                )
        }
)
public class Capability extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "capability_id")
    private Long id;

    /**
     * Immutable internal identifier.
     * Example:
     * ROLE_CREATE
     * STOCK_ITEM_CREATE
     */
    @Column(name = "code", nullable = false, updatable = false, unique = true, length = 100)
    private String code;

    /**
     * Protected resource.
     * Example:
     * role
     */
    @Column(name = "resource", nullable = false, updatable = false)
    private String resource;

    /**
     * Allowed action.
     * Example:
     * create
     */
    @Column(name = "action", nullable = false, updatable = false)
    private String action;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "domain",
            nullable = false,
            updatable = false,
            comment = "The domain module to which the capabilities belongs"
    )
    private SystemDomain domain;



    public static Capability create(CapabilityCreateCommand command) {
        if (command == null){
            throw CapabilityTechnicalException.nullCreateCommand();
        }

        return Capability.builder()
                .code(command.code().toString())
                .resource(command.resource().toString())
                .action(command.action().toString())
                .name(command.name().toString())
                .description(command.description().toString())
                .domain(command.domain())
                .build();
    }

    public void update(CapabilityUpdateCommand command ){
        if (command == null){
            throw CapabilityTechnicalException.nullUpdateCommand();
        }

        this.name = command.name().toString();
        this.description = command.description().toString();
    }

    public boolean requiresUpdate(CapabilityDefinition definition) {

        return !name.equals(definition.getName().value())
                ||
                !description.equals(definition.getDescription().value());
    }


    /**
     * Converts this capability into its canonical permission representation.
     *
     * <p>
     * The permission representation is used internally by the authorization
     * and security layers for authority evaluation and access control checks.
     * </p>
     *
     * <p>
     * Format:
     * </p>
     * <pre>
     * RESOURCE_ACTION
     * </pre>
     *
     * <p>
     * Examples:
     * </p>
     * <pre>
     * role_create
     * stock_item_update
     * password_reset_confirm
     * </pre>
     *
     * @return canonical permission identifier
     */
    public String toPermission() {
        return resource + "_" + action;
    }

    /**
     * Converts this capability into its canonical OAuth2 scope representation.
     *
     * <p>
     * The scope representation is intended for token-based authorization systems
     * such as OAuth2 and JWT claims.
     * </p>
     *
     * <p>
     * Format:
     * </p>
     * <pre>
     * resource:action
     * </pre>
     *
     * <p>
     * Examples:
     * </p>
     * <pre>
     * role:create
     * stock_item:update
     * password_reset:confirm
     * </pre>
     *
     * @return canonical scope identifier
     */
    public String toScope() {
        return resource + ":" + action;
    }

}
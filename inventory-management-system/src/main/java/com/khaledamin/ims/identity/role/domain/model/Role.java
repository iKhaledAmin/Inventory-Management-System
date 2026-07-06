package com.khaledamin.ims.identity.role.domain.model;

import com.khaledamin.ims.core.audit.AuditableEntity;
import com.khaledamin.ims.identity.capability.domain.model.Capability;
import com.khaledamin.ims.identity.role.domain.command.RoleCreateCommand;
import com.khaledamin.ims.identity.role.domain.command.RoleUpdateCommand;
import com.khaledamin.ims.identity.role.domain.definition.RoleDefinition;
import com.khaledamin.ims.identity.role.exception.RoleBusinessException;
import com.khaledamin.ims.identity.role.exception.RoleTechnicalException;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter(AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "roles")
public class Role extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            updatable = false,
            unique = true,
            columnDefinition = "varchar(50)",
            comment = "Unique identifier of the role. This field is immutable and cannot be changed."
    )
    private String name;

    @Column(
            name = "display_name",
            nullable = false,
            unique = true,
            columnDefinition = "varchar(50)",
            comment = "Human-readable value of the role used for UI and presentation purposes. " +
                    "This field is mutable and can be updated without affecting system behavior."
    )
    private String displayName;

    @Column(
            name = "description",
            columnDefinition = "varchar(255)"
    )
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "role_type",
            nullable = false,
            updatable = false
    )
    private RoleType roleType;

    @Column(name = "is_default",
            nullable = false,
            updatable = false,
            columnDefinition = "boolean default false",
            comment = "Indicates whether the role is automatically assigned to newly registered accounts."
    )
    private boolean defaultRole = false;

    // -------------------------------------- Relationships ----------------------------------- //

    @Builder.Default
    @OneToMany(
            mappedBy = "role",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<RoleCapability> roleCapabilities = new HashSet<>();
    // ------------------------------------ End Relationships -------------------------------- //

// ------------------------------------ Business Methods -------------------------------- //

    public static Role create(RoleCreateCommand command){
        if (command == null) {
            throw RoleTechnicalException.nullCreateCommand();
        }

        return Role.builder()
                .name(command.name().toString())
                .displayName(command.displayName().toString())
                .description(command.description().toString())
                .defaultRole(command.defaultRole())
                .roleType(command.roleType())
                .build();
    }


    public void update(RoleUpdateCommand command) {
        if (command == null) {
            throw RoleTechnicalException.nullUpdateCommand();
        }

        this.displayName = command.displayName().toString();
        this.description = command.description().toString();
    }

    public boolean requiresUpdate(RoleDefinition definition) {

        return !displayName.equals(definition.getDisplayName().value())
                ||
                !description.equals(definition.getDescription().value());
    }

    public void addCapability(Capability capability){
        if (capability == null) {
            throw RoleTechnicalException.nullCapability();
        }

        ensureCanAddCapability(capability);

        RoleCapability roleCapability = RoleCapability.create(capability,this);

        roleCapabilities.add(roleCapability);
    }

    public void removeCapability(Capability capability){
        if (capability == null) {
            throw RoleTechnicalException.nullCapability();
        }

        ensureCanRemoveCapability(capability);

        roleCapabilities.removeIf(rc -> rc.getCapability().equals(capability));
    }


    public Set<Capability> getCapabilities() {
        return roleCapabilities.stream()
                .map(RoleCapability::getCapability)
                .collect(Collectors.toSet());
    }


    public Set<String> getPermissions(){
        return roleCapabilities
                .stream()
                .map(roleCapability -> roleCapability.getCapability().toAuthority())
                .collect(Collectors.toSet());
    }

    public boolean hasCapability(String capabilityCode) {
        if (capabilityCode == null) {
            return false;
        }

        return getCapabilities()
                .stream()
                .anyMatch(c -> c.getCode().equals(capabilityCode));
    }


// ------------------------------------ End Business Methods -------------------------------- //


// ------------------------------------ Validation Methods -------------------------------- //





    private void ensureCanAddCapability(Capability capability){

        if (this.hasCapability(capability.getCode())) {
            throw RoleBusinessException.capabilityAlreadyAssigned()
                    .withClientDetails("capabilityCode", capability.getCode())
                    .withDebugDetails("roleName",this.name);
        }
    }

    private void ensureCanRemoveCapability(Capability capability) {

        if (!this.hasCapability(capability.getCode())) {
            throw RoleBusinessException.capabilityNotAssigned()
                    .withClientDetails("capabilityCode", capability.getCode())
                    .withDebugDetails("roleName",this.name);
        }
    }



// ------------------------------------ End Validation Methods -------------------------------- //

}

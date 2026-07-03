package com.khaledamin.ims.organization.domain.model;

import com.khaledamin.ims.core.audit.LifecycleAuditableEntity;
import com.khaledamin.ims.identity.core.model.Actor;
import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.organization.domain.command.OrganizationCreateCommand;
import com.khaledamin.ims.organization.domain.command.OrganizationUpdateCommand;
import com.khaledamin.ims.organization.domain.generator.OrganizationCodeGenerator;
import com.khaledamin.ims.organization.exception.OrganizationTechnicalException;
import com.khaledamin.ims.media.image.domain.model.Image;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;


@Getter
@Setter(AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "organization")
@SQLRestriction("deleted_at IS NULL")
public class Organization extends LifecycleAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id")
    private Long id;

    @Column(name = "code",nullable = false,updatable = false,unique = true)
    private String code;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrganizationStatus status;

    // --------------------------------------------------- Relations --------------------------------------------------- //

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "image_id")
    private Image image;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "actorType",
                    column = @Column(
                            name = "owner_type",
                            nullable = false,
                            updatable = false
                    )
            ),
            @AttributeOverride(
                    name = "actorCode.value",
                    column = @Column(
                            name = "owner_code",
                            nullable = false,
                            updatable = false
                    )
            )
    })
    private ActorIdentity ownerIdentity;



    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "actorType",
                    column = @Column(name = "member_type")
            ),
            @AttributeOverride(
                    name = "actorCode.value",
                    column = @Column(name = "member_code")
            )
    })
    private ActorIdentity memberIdentity;


    // --------------------------------------------------- End Relations --------------------------------------------------- //


    // ----------------------------------------------- Methods ----------------------------------------------- //

    public static Organization create(OrganizationCreateCommand command) {
        if (command == null){
            throw OrganizationTechnicalException.nullCreateCommand();
        }

        String code = OrganizationCodeGenerator.generate();

        return Organization.builder()
                .code(code)
                .name(command.name().toString())
                .description(command.description().toString())
                .status(OrganizationStatus.getDefault())
                .build();


    }

    public void update(OrganizationUpdateCommand command){
        if (command == null) {
            throw OrganizationTechnicalException.nullUpdateCommand();
        }

        command.name().ifPresent(oName -> this.name = oName.toString());
        command.description().ifPresent(oDescription -> this.description = oDescription.toString());
        command.status().ifPresent(oStatus -> this.status = oStatus);

    }

    public void delete(Actor actor) {
        super.delete(actor);
        this.status = OrganizationStatus.SUSPENDED;
    }

    public void attachOwner(ActorIdentity ownerIdentity){
        if (ownerIdentity == null) {
            throw OrganizationTechnicalException.nullOwnerIdentity();
        }
        this.ownerIdentity = ownerIdentity;
    }

    public void addMember(ActorIdentity memberIdentity){
        if (memberIdentity == null) {
            throw OrganizationTechnicalException.nullMemberIdentity();
        }
        this.memberIdentity = memberIdentity;
    }

    public void addImage(Image image) {

        if (image == null) {
            throw OrganizationTechnicalException.nullImage();
        }

        this.image = image;
    }

    public void updateImage(Image image) {

        if (image == null) {
            throw OrganizationTechnicalException.nullImage();
        }

        this.image = image;
    }
    public boolean isSame(Organization other){
        return this.id.equals(other.id);
    }

    public boolean ownedBy(ActorIdentity memberIdentity){
        return ownerIdentity.isSame(memberIdentity);
    }


    public boolean hasMember(ActorIdentity memberIdentity){
        return this.memberIdentity.isSame(memberIdentity) || ownedBy(memberIdentity);
    }


    // ---------------------------------------------- End Methods ----------------------------------------------- //



}

package com.khaledamin.ims.organization.domain.model;

import com.khaledamin.ims.core.audit.LifecycleAuditableEntity;
import com.khaledamin.ims.identity.account.domain.model.Account;
import com.khaledamin.ims.identity.core.model.Actor;
import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.organization.domain.command.OrganizationCreateCommand;
import com.khaledamin.ims.organization.domain.command.OrganizationUpdateCommand;
import com.khaledamin.ims.organization.exception.OrganizationTechnicalException;
import com.khaledamin.ims.media.image.domain.model.Image;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.HashSet;
import java.util.Set;

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

    // now and later only one owner
    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "owner_id",nullable = false)
    private Account owner;

//    // now only one Client actor
//    // later may be more (Account, Client, etc...)
//    @Builder.Default
//    private Set<ActorIdentity> memberIdentities = new HashSet<>();


    // --------------------------------------------------- End Relations --------------------------------------------------- //


    // ----------------------------------------------- Methods ----------------------------------------------- //

    public static Organization create(OrganizationCreateCommand command,Account owner) {
        if (command == null){
            throw OrganizationTechnicalException.nullCreateCommand();
        }

        if (owner == null){
            throw OrganizationTechnicalException.nullOwnerAccount();
        }

        return Organization.builder()
                .code(command.code().toString())
                .name(command.name().toString())
                .description(command.description().toString())
                //.memberIdentities(new HashSet<>())
                .status(OrganizationStatus.ACTIVE)
                .owner(owner)
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

//    public void addMember(ActorIdentity memberIdentity){
//        if (memberIdentity == null){
//            throw OrganizationTechnicalException.nullMemberIdentity();
//        }
//
//        if (!this.hasMember(memberIdentity)){
//            memberIdentities.add(memberIdentity);
//        }
//
//    }
//
//    public boolean hasMember(ActorIdentity memberIdentity){
//        return memberIdentities.contains(memberIdentity);
//    }

    // ---------------------------------------------- End Methods ----------------------------------------------- //



}

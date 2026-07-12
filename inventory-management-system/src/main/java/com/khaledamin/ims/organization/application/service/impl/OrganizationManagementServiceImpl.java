package com.khaledamin.ims.organization.application.service.impl;

import com.khaledamin.ims.core.exception.core.BaseException;
import com.khaledamin.ims.core.logging.event.BusinessEventLogger;
import com.khaledamin.ims.identity.client.api.dto.ClientCreateRequest;
import com.khaledamin.ims.identity.client.application.service.ClientManagementService;
import com.khaledamin.ims.identity.client.application.service.ClientQueryService;
import com.khaledamin.ims.identity.client.domain.model.Client;
import com.khaledamin.ims.identity.core.model.Actor;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.model.ActorIdentity;
import com.khaledamin.ims.identity.core.provider.ActorProvider;
import com.khaledamin.ims.organization.api.dto.OrganizationCreateRequest;
import com.khaledamin.ims.organization.api.dto.OrganizationUpdateRequest;
import com.khaledamin.ims.organization.application.service.OrganizationManagementService;
import com.khaledamin.ims.organization.application.service.OrganizationQueryService;
import com.khaledamin.ims.organization.domain.policy.OrganizationAccessPolicy;
import com.khaledamin.ims.organization.domain.command.OrganizationCreateCommand;
import com.khaledamin.ims.organization.domain.command.OrganizationUpdateCommand;
import com.khaledamin.ims.organization.domain.model.Organization;
import com.khaledamin.ims.organization.domain.model.OrganizationImagePreset;
import com.khaledamin.ims.organization.domain.repository.OrganizationRepository;
import com.khaledamin.ims.organization.exception.OrganizationBusinessException;
import com.khaledamin.ims.organization.exception.OrganizationTechnicalException;
import com.khaledamin.ims.media.core.model.MediaOwnerType;
import com.khaledamin.ims.media.image.application.service.ImageService;
import com.khaledamin.ims.media.image.domain.model.Image;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class OrganizationManagementServiceImpl implements OrganizationManagementService {
    private final OrganizationQueryService organizationQueryService;
    private final OrganizationRepository organizationRepository;
    private final OrganizationAccessPolicy organizationAccessPolicy;
    private final ImageService imageService;
    private final ActorProvider actorProvider;
    private final ClientManagementService clientManagementService;
    private final ClientQueryService clientQueryService;
    private final BusinessEventLogger businessEventLogger;


    @Transactional
    @Override
    public Organization create(OrganizationCreateRequest request, ActorIdentity ownerIdentity){

        OrganizationCreateCommand command = OrganizationCreateCommand.of(request);

        // Domain logic
        Organization newOrganization = Organization.create(command);

        newOrganization.attachOwner(ownerIdentity);

        // Persist
        Organization saved = organizationRepository.save(newOrganization);

        // Log the business operation event
        businessEventLogger.organizationCreated(
                saved.getCode()
        );

        return saved;
    }

    @Transactional
    @Override
    public Organization update(OrganizationUpdateRequest request) {

        Actor currentActor = actorProvider.getCurrent();

        Organization existingOrganization = organizationQueryService.getByOwnerIdentity(
                currentActor.getActorIdentity()
        );

        OrganizationUpdateCommand command = OrganizationUpdateCommand.of(request);

        if (request.getImage() != null) {
            handleImage(
                    existingOrganization,
                    request.getImage()
            );
        }

        // Domain logic
        existingOrganization.update(command);

        // Persist
        Organization saved = organizationRepository.save(existingOrganization);

        // Log the business operation event
        businessEventLogger.organizationUpdated(
                saved.getCode()
        );

        return saved;
    }


    @Override
    public Organization view() {

        Actor currentActor = actorProvider.getCurrent();

        Organization organization = organizationQueryService.getByOwnerIdentity(
                currentActor.getActorIdentity()
        );

        // Log the business operation event
        businessEventLogger.organizationViewed(
                organization.getCode()
        );

        return organization;
    }


    @Transactional
    @Override
    public Client createClient(ClientCreateRequest request) {

        Actor currentActor = actorProvider.getCurrent();
        Organization organization = organizationQueryService.getByOwnerIdentity(
                currentActor.getActorIdentity()
        );

        // this for now ,later will be possible more than one member [Client or Account] will introduce membership concept
        if (organization.getMemberIdentity() != null){
            throw OrganizationBusinessException.clientAlreadyExists();
        }

        Client newClient = clientManagementService.create(request);

        organization.addMember(
                newClient.getActorIdentity()
        );

        organizationRepository.save(organization);

        return newClient;
    }


    @Transactional
    @Override
    public String rotateClientSecret(ActorCode clientCode) {
        Client client = clientQueryService.getByClientCode(clientCode);

        Actor currentActor = actorProvider.getCurrent();
        Organization organization = organizationQueryService.getByOwnerIdentity(
                currentActor.getActorIdentity()
        );

        organizationAccessPolicy.canRotateClientSecret(client,organization);

        return clientManagementService.rotateSecret(clientCode);
    }



    // --------------------------------------------------- Helper methods ---------------------------------------------------


    private void handleImage(Organization organization, MultipartFile imageFile) {

        if (organization.getImage() == null){

            Image newImage = uploadImageToStorage(imageFile);
            organization.addImage(newImage);


        } else {

            Image existingImage = organization.getImage();

            Image updatedImage = updateImageInStorage(existingImage, imageFile);
            organization.updateImage(updatedImage);
        }
    }


    private Image uploadImageToStorage(MultipartFile newImageFile) {
        try {
            return imageService.create(
                    newImageFile,
                    OrganizationImagePreset.INSTANCE,
                    MediaOwnerType.ORGANIZATION
            );

        } catch (BaseException e) {

            throw OrganizationTechnicalException.imageUploadFailed();
        }
    }

    private Image updateImageInStorage(Image existingImage, MultipartFile newImageFile) {
        try {
            return imageService.update(
                    existingImage,
                    newImageFile,
                    OrganizationImagePreset.INSTANCE,
                    MediaOwnerType.ORGANIZATION
            );

        } catch (BaseException e) {

            throw OrganizationTechnicalException.imageUploadFailed();
        }
    }

}

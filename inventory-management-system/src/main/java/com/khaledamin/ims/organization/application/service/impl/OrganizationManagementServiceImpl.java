package com.khaledamin.ims.organization.application.service.impl;

import com.khaledamin.ims.core.exception.core.BaseException;
import com.khaledamin.ims.core.logging.event.BusinessEventLogger;
import com.khaledamin.ims.identity.account.application.service.AccountQueryService;
import com.khaledamin.ims.identity.account.domain.model.Account;
import com.khaledamin.ims.identity.core.model.Actor;
import com.khaledamin.ims.identity.core.provider.ActorProvider;
import com.khaledamin.ims.organization.api.dto.OrganizationUpdateRequest;
import com.khaledamin.ims.organization.application.service.OrganizationManagementService;
import com.khaledamin.ims.organization.application.service.OrganizationQueryService;
import com.khaledamin.ims.organization.domain.command.OrganizationUpdateCommand;
import com.khaledamin.ims.organization.domain.factory.OrganizationFactory;
import com.khaledamin.ims.organization.domain.model.Organization;
import com.khaledamin.ims.organization.domain.model.OrganizationImagePreset;
import com.khaledamin.ims.organization.domain.repository.OrganizationRepository;
import com.khaledamin.ims.organization.domain.value.OrganizationDescription;
import com.khaledamin.ims.organization.domain.value.OrganizationName;
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
    private final OrganizationFactory organizationFactory;
    private final ImageService imageService;
    private final ActorProvider actorProvider;
    private final AccountQueryService accountQueryService;
    private final BusinessEventLogger businessEventLogger;


    @Transactional
    @Override
    public Organization create(OrganizationName name , OrganizationDescription description, Account owner){

        // Domain logic
        Organization newOrganization = organizationFactory.create(name,description,owner);

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
        Account owner = accountQueryService.getByIdentity(currentActor.getActorIdentity());

        Organization existingOrganization = organizationQueryService.getByOwnerId(owner.getId());

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
        Account owner = accountQueryService.getByIdentity(currentActor.getActorIdentity());

        Organization organization = organizationQueryService.getByOwnerId(owner.getId());


        // Log the business operation event
        businessEventLogger.organizationViewed(
                organization.getCode()
        );

        return organization;
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

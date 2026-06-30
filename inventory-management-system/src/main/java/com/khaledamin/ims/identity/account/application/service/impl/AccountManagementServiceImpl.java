package com.khaledamin.ims.identity.account.application.service.impl;

import com.khaledamin.ims.core.api.pagination.PageResult;
import com.khaledamin.ims.core.exception.core.BaseException;
import com.khaledamin.ims.core.logging.event.BusinessEventLogger;
import com.khaledamin.ims.identity.account.api.dto.AccountCreateRequest;
import com.khaledamin.ims.identity.account.api.dto.AccountPageRequest;
import com.khaledamin.ims.identity.account.api.dto.ProfileUpdateRequest;
import com.khaledamin.ims.identity.account.application.service.AccountManagementService;
import com.khaledamin.ims.identity.account.application.service.AccountQueryService;
import com.khaledamin.ims.identity.account.application.validation.AccountApplicationValidator;
import com.khaledamin.ims.identity.account.domain.command.ProfileUpdateCommand;
import com.khaledamin.ims.identity.account.domain.model.Account;
import com.khaledamin.ims.identity.account.domain.model.AccountFactory;
import com.khaledamin.ims.identity.account.domain.model.AccountImagePreset;
import com.khaledamin.ims.identity.account.domain.repository.AccountRepository;
import com.khaledamin.ims.identity.account.domain.value.EncodedPassword;
import com.khaledamin.ims.identity.account.domain.value.RawPassword;
import com.khaledamin.ims.identity.account.exception.AccountTechnicalException;
import com.khaledamin.ims.identity.core.model.Actor;
import com.khaledamin.ims.identity.core.model.ActorCode;
import com.khaledamin.ims.identity.core.provider.ActorProvider;
import com.khaledamin.ims.identity.role.application.service.RoleQueryService;
import com.khaledamin.ims.identity.role.domain.model.Role;
import com.khaledamin.ims.media.core.model.MediaOwnerType;
import com.khaledamin.ims.media.image.application.service.ImageService;
import com.khaledamin.ims.media.image.domain.model.Image;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Service
public class AccountManagementServiceImpl implements AccountManagementService {

    private final AccountRepository accountRepository;
    private final AccountQueryService accountQueryService;
    private final AccountFactory accountFactory;
    private final ImageService imageService;
    private final RoleQueryService roleQueryService;
    private final ActorProvider actorProvider;
    private final AccountApplicationValidator accountValidator;
    private final PasswordEncoder passwordEncoder;
    private final BusinessEventLogger businessEventLogger;


    @Transactional
    @Override
    public Account create(AccountCreateRequest request) {
        List<Role> roles = roleQueryService.getDefaultRoles();
        return create(request, roles);
    }

    @Transactional
    @Override
    public Account create(AccountCreateRequest request, List<Role> roles) {

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Application validation
        accountValidator.validateCreate(request);

        // Domain logic
        Account account = accountFactory.create(
                request.getUsername(),
                encodedPassword,
                request.getEmailAddress(),
                request.getFirstName(),
                request.getLastName(),
                roles
        );

        // Persist
        Account saved = accountRepository.save(account);

        // Log the business operation event
        businessEventLogger.accountCreated(
                saved.getAccountCode()
        );

        // todo :- create organization

        return saved;
    }


    @Transactional
    @Override
    public Account update(ActorCode accountCode, ProfileUpdateRequest request) {

        Account existingAccount = accountQueryService.getByAccountCode(accountCode);

        ProfileUpdateCommand command = ProfileUpdateCommand.of(request);

        // Application validation
        accountValidator.validateUpdate(existingAccount, request);

        if (request.getImage() != null) {
            handleImage(
                    existingAccount,
                    request.getImage()
            );
        }

        // Domain logic
        existingAccount.update(command);

        // Persist
        Account saved = accountRepository.save(existingAccount);

        // Log the business operation event
        businessEventLogger.accountUpdated(
                saved.getAccountCode()
        );

        return saved;
    }


    @Transactional
    @Override
    public Account activate(ActorCode accountCode) {
        Account target = accountQueryService.getByAccountCode(accountCode);

        // Domain logic
        target.activate();

        // Persist
        Account saved = accountRepository.save(target);

        // Log the business operation event
        businessEventLogger.accountActivated(
                saved.getAccountCode()
        );

        return saved;
    }

    @Transactional
    @Override
    public void resetPassword(ActorCode accountCode, RawPassword rawPassword) {

        Account target = accountQueryService.getByAccountCode(accountCode);

        EncodedPassword encodedPassword = EncodedPassword.of(
                passwordEncoder.encode(rawPassword.toString())
        );

        // Domain logic
        target.resetPassword(encodedPassword);

        // Persist
        Account saved = accountRepository.save(target);

        // Log the business operation event
        businessEventLogger.accountPasswordReset(
                saved.getAccountCode()
        );

    }


    @Override
    public void login(ActorCode accountCode) {
        Account account = accountQueryService.getByAccountCode(accountCode);
        account.login();
        accountRepository.save(account);
    }


    @Transactional(readOnly = true)
    public Account viewAccount(ActorCode accountCode) {

        Account account = accountQueryService.getByAccountCode(accountCode);

        businessEventLogger.accountViewed(
                account.getAccountCode()
        );

        return account;
    }

    @Transactional(readOnly = true)
    public Account viewMyAccount() {

        Actor actor = actorProvider.getCurrent();

        Account account = accountQueryService.getByIdentity(
                actor.getActorIdentity()
        );

        businessEventLogger.accountViewed(
                account.getAccountCode()
        );

        return account;
    }

    @Transactional(readOnly = true)
    public PageResult<Account> listAccounts(AccountPageRequest request) {

        PageResult<Account> accounts = accountRepository.findAll(request);

        businessEventLogger.accountListed(
                request.getPage(),
                request.getSize(),
                request.getSortBy(),
                request.getDirection()
        );

        return accounts;
    }



    private void handleImage(Account account, MultipartFile imageFile) {

        if (account.getProfile().getImage() == null){

            Image newImage = uploadImageToStorage(imageFile);
            account.addImage(newImage);


        } else {

            Image existingImage = account.getProfile().getImage();

            Image updatedImage = updateImageInStorage(existingImage, imageFile);
            account.updateImage(updatedImage);
        }
    }


    // --------------------------------------------------- Helper methods ---------------------------------------------------

    private Image uploadImageToStorage(MultipartFile newImageFile) {
        try {
            return imageService.create(
                    newImageFile,
                    AccountImagePreset.INSTANCE,
                    MediaOwnerType.PROFILE
            );

        } catch (BaseException e) {

            throw AccountTechnicalException.imageUploadFailed();
        }
    }

    private Image updateImageInStorage(Image existingImage, MultipartFile newImageFile) {
        try {
            return imageService.update(
                    existingImage,
                    newImageFile,
                    AccountImagePreset.INSTANCE,
                    MediaOwnerType.PROFILE
            );

        } catch (BaseException e) {

            throw AccountTechnicalException.imageUploadFailed();
        }
    }





}

package com.khaledamin.ims.identity.account.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.technical.TechnicalError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountTechnicalError implements TechnicalError {

    ACCOUNT_NULL(
            SystemDomain.ACCOUNT,
            "ACCOUNT_NULL",
            "Account is null"
    ),

    PROFILE_NULL(
            SystemDomain.ACCOUNT,
            "ACCOUNT_PROFILE_NULL",
            "Account profile is null"
    ),

    ROLE_NULL(
            SystemDomain.ACCOUNT,
            "ACCOUNT_ROLE_NULL",
            "Account role is null"
    ),

    ROLE_List_NULL(
            SystemDomain.ACCOUNT,
            "ACCOUNT_ROLE_LIST_NULL",
            "Account roles list is null"
    ),

    ACCOUNT_UPDATE_COMMAND_NULL(
            SystemDomain.ACCOUNT,
            "ACCOUNT_UPDATE_COMMAND_NULL",
            "Account update command is null"
    ),

    ACCOUNT_CREATE_COMMAND_NULL(
            SystemDomain.ACCOUNT,
            "ACCOUNT_CREATE_COMMAND_NULL",
            "Account create command is null"
    ),

    PROFILE_UPDATE_COMMAND_NULL(
            SystemDomain.ACCOUNT,
            "PROFILE_UPDATE_COMMAND_NULL",
            "Account profile update command is null"
    ),

    PROFILE_CREATE_COMMAND_NULL(
            SystemDomain.ACCOUNT,
            "PROFILE_CREATE_COMMAND_NULL",
            "Account profile create command is null"
    ),

    PROFILE_ALREADY_ATTACHED(
            SystemDomain.ACCOUNT,
            "ACCOUNT_PROFILE_ALREADY_ATTACHED",
            "Account profile is already attached"
    ),

    ACTOR_IDENTITY_NULL(
            SystemDomain.ACCOUNT,
            "ACTOR_IDENTITY_NULL",
            "Account actor identity is null"
    ),


    IMAGE_NULL(
            SystemDomain.ACCOUNT,
            "ACCOUNT_IMAGE_NULL",
            "Account profile image is null"
    ),

    IMAGE_UPLOAD_FAILED(
            SystemDomain.ACCOUNT,
            "ACCOUNT_IMAGE_UPLOAD_FAILED",
            "Account profile image upload failed"
    );
    private final SystemDomain domain;
    private final String code;
    private final String message;
}

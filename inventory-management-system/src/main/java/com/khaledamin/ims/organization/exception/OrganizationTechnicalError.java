package com.khaledamin.ims.organization.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.technical.TechnicalError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrganizationTechnicalError implements TechnicalError {


    CREATE_COMMAND_NULL(
            SystemDomain.ORGANIZATION,
            "ORGANIZATION_CREATE_COMMAND_NULL",
            "Organization create command is null"
    ),

    UPDATE_COMMAND_NULL(
            SystemDomain.ORGANIZATION,
            "ORGANIZATION_UPDATE_COMMAND_NULL",
            "Update command is null"
    ),

    IMAGE_NULL(
            SystemDomain.ORGANIZATION,
            "ORGANIZATION_IMAGE_NULL",
            "Image is null"
    ),

    OWNER_IDENTITY_NULL(
            SystemDomain.ORGANIZATION,
            "ORGANIZATION_OWNER_IDENTITY_NULL",
            "Owner identity is null"
    ),

    MEMBER_IDENTITY_NULL(
            SystemDomain.ORGANIZATION,
            "ORGANIZATION_MEMBER_IDENTITY_NULL",
            "Member identity is null"
    ),

    IMAGE_UPLOAD_FAILED(
            SystemDomain.ACCOUNT,
            "ORGANIZATION_IMAGE_UPLOAD_FAILED",
            "Organization image upload failed"
    ),

    SETTING_UPDATE_COMMAND_NULL(
            SystemDomain.ORGANIZATION,
            "ORGANIZATION_SETTING_UPDATE_COMMAND_NULL",
            "Organization setting update command is null"
    );
    private final SystemDomain domain;
    private final String code;
    private final String message;
}

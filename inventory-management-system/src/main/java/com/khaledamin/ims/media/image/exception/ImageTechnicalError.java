package com.khaledamin.ims.media.image.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.technical.TechnicalError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageTechnicalError implements TechnicalError {

    IMAGE_NULL(
            SystemDomain.MEDIA,
            "IMAGE_NULL",
            "Image must not be null"
    ),

    IMAGE_FILE_NULL(
            SystemDomain.MEDIA,
            "IMAGE_FILE_NULL",
            "Image file must not be null"
    ),

    IMAGE_VARIANT_NULL(
            SystemDomain.MEDIA,
            "IMAGE_VARIANT_NULL",
            "Image variant must not be null"
    ),

    IMAGE_RESOLUTION_NULL(
            SystemDomain.MEDIA,
            "IMAGE_RESOLUTION_NULL",
            "Image resolution must not be null"
    ),

    IMAGE_PRESET_NULL(
            SystemDomain.MEDIA,
            "IMAGE_PRESET_NULL",
            "Image preset must not be null"
    ),

    IMAGE_OWNER_TYPE_NULL(
            SystemDomain.MEDIA,
            "IMAGE_OWNER_TYPE_NULL",
            "Image owner type must not be null"
    ),

    CHECKSUM_GENERATION_FAILED(
            SystemDomain.MEDIA,
            "CHECKSUM_GENERATION_FAILED",
            "Failed to generate image checksum"
    ),

    IMAGE_READ_FAILED(
            SystemDomain.MEDIA,
            "IMAGE_READ_FAILED",
            "Failed to read image content"
    ),

    IMAGE_STORAGE_FAILED(
            SystemDomain.MEDIA,
            "IMAGE_STORAGE_FAILED",
            "Failed to store image"
    ),

    IMAGE_CREATION_FAILED(
            SystemDomain.MEDIA,
            "IMAGE_CREATION_FAILED",
            "Failed to create image"
    )

    ;
    private final SystemDomain domain;
    private final String code;
    private final String message;
}

package com.khaledamin.ims.media.image.exception;

import com.khaledamin.ims.core.constant.SystemDomain;
import com.khaledamin.ims.core.exception.validation.ValidationError;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageValidationError implements ValidationError {


    IMAGE_FILE_EMPTY(
            SystemDomain.MEDIA,
            "IMAGE_FILE_EMPTY",
            "Image file must not be empty"
    ),
    ;

    private final SystemDomain domain;
    private final String code;
    private final String message;
}

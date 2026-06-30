package com.khaledamin.ims.media.image.exception;

import com.khaledamin.ims.core.exception.validation.ValidationError;
import com.khaledamin.ims.core.exception.validation.ValidationException;

public class ImageValidationException extends ValidationException {
    protected ImageValidationException(ValidationError error) {
        super(error);
    }


    public static ImageValidationException imageFileEmpty() {
        return new ImageValidationException(ImageValidationError.IMAGE_FILE_EMPTY);
    }
}

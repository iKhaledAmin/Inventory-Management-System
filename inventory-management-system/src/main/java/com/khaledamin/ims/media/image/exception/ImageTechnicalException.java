package com.khaledamin.ims.media.image.exception;

import com.khaledamin.ims.core.exception.technical.TechnicalException;
import com.khaledamin.ims.core.exception.technical.TechnicalError;

public class ImageTechnicalException extends TechnicalException {

    // -------------------------------------- Constructors --------------------------------------  //
    protected ImageTechnicalException(TechnicalError error) {
        super(error);
    }

    protected ImageTechnicalException(TechnicalError error, Throwable cause) {
        super(error, cause);
    }

    // ----------------------------------- End Constructors ------------------------------------  //

    // ----------------------------------- Static Methods ------------------------------------  //
    public static ImageTechnicalException nullImageVariant() {
        return new ImageTechnicalException(ImageTechnicalError.IMAGE_VARIANT_NULL);
    }

    public static ImageTechnicalException nullImageResolution() {
        return new ImageTechnicalException(ImageTechnicalError.IMAGE_RESOLUTION_NULL);
    }

    public static ImageTechnicalException nullImage() {
        return new ImageTechnicalException(ImageTechnicalError.IMAGE_NULL);
    }

    public static ImageTechnicalException nullImageFile() {
        return new ImageTechnicalException(ImageTechnicalError.IMAGE_FILE_NULL);
    }

    public static ImageTechnicalException nullImagePreset() {
        return new ImageTechnicalException(ImageTechnicalError.IMAGE_PRESET_NULL);
    }

    public static ImageTechnicalException nullOwnerType() {
        return new ImageTechnicalException(ImageTechnicalError.IMAGE_OWNER_TYPE_NULL);
    }


    public static ImageTechnicalException checksumGenerationFailed(Throwable cause) {
        return new ImageTechnicalException(
                ImageTechnicalError.CHECKSUM_GENERATION_FAILED,
                cause
        );
    }

    public static ImageTechnicalException imageReadFailed(Throwable cause) {
        return new ImageTechnicalException(
                ImageTechnicalError.IMAGE_READ_FAILED,
                cause
        );
    }

    public static ImageTechnicalException imageStorageFailed(Throwable cause) {
        return new ImageTechnicalException(
                ImageTechnicalError.IMAGE_STORAGE_FAILED,
                cause
        );
    }

    public static ImageTechnicalException imageCreationFailed(Throwable cause) {
        return new ImageTechnicalException(
                ImageTechnicalError.IMAGE_CREATION_FAILED,
                cause
        );
    }


    // ----------------------------------- End Static Methods ----------------------------------  //
}

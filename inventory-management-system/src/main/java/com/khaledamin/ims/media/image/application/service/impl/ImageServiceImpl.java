package com.khaledamin.ims.media.image.application.service.impl;


import com.khaledamin.ims.media.core.model.MediaOwnerType;
import com.khaledamin.ims.media.core.model.MediaType;
import com.khaledamin.ims.media.core.storage.StorageProvider;
import com.khaledamin.ims.media.core.url.MediaUrlResolver;
import com.khaledamin.ims.media.image.application.model.GeneratedImageVariant;
import com.khaledamin.ims.media.image.application.service.ImageResizer;
import com.khaledamin.ims.media.image.application.service.ImageService;
import com.khaledamin.ims.media.image.domain.factory.ImageFactory;
import com.khaledamin.ims.media.image.domain.factory.StorageKeyGenerator;
import com.khaledamin.ims.media.image.domain.model.Image;
import com.khaledamin.ims.media.image.domain.model.ImagePreset;
import com.khaledamin.ims.media.image.domain.model.ImageResolution;
import com.khaledamin.ims.media.image.domain.model.ImageVariant;
import com.khaledamin.ims.media.image.exception.ImageTechnicalException;
import com.khaledamin.ims.media.image.exception.ImageValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final StorageProvider storageProvider;
    private final ImageFactory imageFactory;
    private final ImageResizer imageResizer;
    private final MediaUrlResolver mediaUrlResolver;
    private final StorageKeyGenerator storageKeyGenerator;

    @Override
    public Image create(MultipartFile file, ImagePreset preset, MediaOwnerType ownerType) {

        validate(file, preset, ownerType);

        Image image = imageFactory.create(file);

        BufferedImage source = readImage(file);

        Set<ImageResolution> resolutions = buildResolutions(preset);

        try {
            for (ImageResolution resolution : resolutions) {

                GeneratedImageVariant generatedVariant = imageResizer.resize(source, resolution);

                String storageKey =
                        storageKeyGenerator.generate(
                                ownerType,
                                image.getCode(),
                                resolution
                        );

                storeVariant(storageKey, generatedVariant);

                ImageVariant variant = ImageVariant.create(storageKey, generatedVariant, resolution);

                image.addVariant(variant);
            }

            return image;
        }catch (Exception e){
            delete(image);
            throw ImageTechnicalException.imageCreationFailed(e);
        }


    }

    @Override
    public Image update(Image existingImage, MultipartFile newFile, ImagePreset preset, MediaOwnerType ownerType) {

        if (existingImage == null) {
            throw ImageTechnicalException.nullImage();
        }

        validate(newFile, preset, ownerType);

        if (existingImage.hasSameContent(newFile)) {
            return existingImage;
        }

        delete(existingImage);

        return create(newFile, preset, ownerType);
    }

    @Override
    public void delete(Image image) {

        if (image == null) {
            throw ImageTechnicalException.nullImage();
        }

        for (ImageVariant variant : image.getVariants()) {

            storageProvider.delete(
                    variant.getStorageKey()
            );
        }
    }

    @Override
    public String getUrl(Image image, ImageResolution resolution) {

        if (image == null) {
            return null;
        }

        ImageVariant variant = image.getVariant(resolution);

        if (variant == null) {
            return null;
        }

        return mediaUrlResolver.resolve(
                MediaType.IMAGE,
                variant.getStorageKey()
        );
    }

    // -------------------------------- Private Methods --------------------------------  //

    private void validate(MultipartFile file, ImagePreset preset,MediaOwnerType ownerType) {

        if (file == null) throw ImageTechnicalException.nullImageFile();

        if (file.isEmpty()) throw ImageValidationException.imageFileEmpty();

        if (preset == null) throw ImageTechnicalException.nullImagePreset();

        if (ownerType == null) throw ImageTechnicalException.nullOwnerType();

    }

    private BufferedImage readImage(MultipartFile file) {

        try {

            BufferedImage image = ImageIO.read(file.getInputStream());

            if (image == null) {
                throw ImageTechnicalException.nullImageFile();
            }

            return image;

        } catch (IOException exception) {

            throw ImageTechnicalException
                    .imageReadFailed(exception);
        }
    }


    private Set<ImageResolution> buildResolutions(ImagePreset preset) {

        Set<ImageResolution> resolutions = new HashSet<>(preset.resolutions());

        if (!preset.hasOriginal()) {
            resolutions.add(
                    ImageResolution.ORIGINAL
            );
        }

        return resolutions;
    }

    private void storeVariant(String storageKey, GeneratedImageVariant variant) {

        try {

            storageProvider.store(
                    storageKey,
                    variant.content()
            );

        } catch (Exception exception) {

            throw ImageTechnicalException.imageStorageFailed(exception);
        }
    }
}
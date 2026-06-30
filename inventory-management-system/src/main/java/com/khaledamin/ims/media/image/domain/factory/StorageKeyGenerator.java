package com.khaledamin.ims.media.image.domain.factory;

import com.khaledamin.ims.media.core.model.MediaOwnerType;
import com.khaledamin.ims.media.image.domain.model.ImageResolution;
import com.khaledamin.ims.media.image.application.model.ImageConstants;
import org.springframework.stereotype.Component;

@Component
public class StorageKeyGenerator {

    public String generate(MediaOwnerType ownerType, String imageCode, ImageResolution resolution) {

        // example: product/IMG-123/original.webp
        // category/IMG-123/large.webp
        // product/IMG-123/square_thumbnail.webp
        return
                ownerType.folder() +
                        "/" +
                        imageCode +
                        "/" +
                        resolution.name().toLowerCase() +
                        "." +
                        ImageConstants.OUTPUT_FORMAT;
    }
}
package com.khaledamin.ims.media.image.domain.model;

import java.util.Set;

public interface ImagePreset {
    Set<ImageResolution> resolutions();

    default boolean hasOriginal() {
        return resolutions().contains(ImageResolution.ORIGINAL);
    }
}

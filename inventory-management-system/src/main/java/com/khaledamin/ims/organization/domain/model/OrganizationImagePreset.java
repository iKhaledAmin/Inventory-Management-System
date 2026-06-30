package com.khaledamin.ims.organization.domain.model;



import com.khaledamin.ims.media.image.domain.model.ImagePreset;
import com.khaledamin.ims.media.image.domain.model.ImageResolution;

import java.util.Set;


public final class OrganizationImagePreset implements ImagePreset {

    public static final OrganizationImagePreset INSTANCE = new OrganizationImagePreset();

    @Override
    public Set<ImageResolution> resolutions(){
        return Set.of(
                ImageResolution.LARGE,
                ImageResolution.SQUARE_MEDIUM
        );
    }
}

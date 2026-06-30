package com.khaledamin.ims.identity.account.domain.model;



import com.khaledamin.ims.media.image.domain.model.ImagePreset;
import com.khaledamin.ims.media.image.domain.model.ImageResolution;

import java.util.Set;

public final class AccountImagePreset implements ImagePreset {
    public static final AccountImagePreset INSTANCE = new AccountImagePreset();

    @Override
    public Set<ImageResolution> resolutions(){
        return Set.of(
                ImageResolution.LARGE,
                ImageResolution.SQUARE_MEDIUM
        );
    }
}

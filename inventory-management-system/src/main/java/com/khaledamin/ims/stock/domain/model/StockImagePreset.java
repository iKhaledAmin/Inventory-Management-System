package com.khaledamin.ims.stock.domain.model;



import com.khaledamin.ims.media.image.domain.model.ImagePreset;
import com.khaledamin.ims.media.image.domain.model.ImageResolution;

import java.util.Set;

public final class StockImagePreset implements ImagePreset {

    public static final StockImagePreset INSTANCE = new StockImagePreset();

    @Override
    public Set<ImageResolution> resolutions(){
        return Set.of(
                ImageResolution.SQUARE_THUMBNAIL,
                ImageResolution.SQUARE_MEDIUM
        );
    }
}
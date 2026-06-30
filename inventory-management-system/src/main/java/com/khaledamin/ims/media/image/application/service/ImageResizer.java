package com.khaledamin.ims.media.image.application.service;

import com.khaledamin.ims.media.image.application.model.GeneratedImageVariant;
import com.khaledamin.ims.media.image.domain.model.ImageResolution;

import java.awt.image.BufferedImage;

public interface ImageResizer {
    GeneratedImageVariant resize(BufferedImage source, ImageResolution resolution);
}
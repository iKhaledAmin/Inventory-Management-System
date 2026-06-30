package com.khaledamin.ims.media.image.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageResolution {

    ORIGINAL(0, 0, ResizeMode.NONE),

    LARGE(1200, 1200, ResizeMode.FIT),

    MEDIUM(800, 800, ResizeMode.FIT),

    SMALL(400, 400, ResizeMode.FIT),

    SQUARE_LARGE(1000, 1000, ResizeMode.SQUARE_CROP),

    SQUARE_MEDIUM(500, 500, ResizeMode.SQUARE_CROP),

    SQUARE_THUMBNAIL(150, 150, ResizeMode.SQUARE_CROP);

    private final int width;
    private final int height;
    private final ResizeMode resizeMode;
}
package com.khaledamin.ims.media.image.application.model;

import java.io.InputStream;

public record GeneratedImageVariant(
        InputStream content,
        int width,
        int height,
        long fileSize,
        String mimeType
) {
}
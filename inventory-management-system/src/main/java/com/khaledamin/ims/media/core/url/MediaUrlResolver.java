package com.khaledamin.ims.media.core.url;

import com.khaledamin.ims.media.core.model.MediaType;

public interface MediaUrlResolver {
    // Responsible for generating public access URLs
    // storageKey: unique identifier for the media file
    String resolve(MediaType mediaType, String storageKey);
}

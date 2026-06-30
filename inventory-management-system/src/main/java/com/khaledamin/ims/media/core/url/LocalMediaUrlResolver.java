package com.khaledamin.ims.media.core.url;

import com.khaledamin.ims.media.core.model.MediaType;
import com.khaledamin.ims.media.core.properties.MediaProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalMediaUrlResolver implements MediaUrlResolver {

    private final MediaProperties mediaProperties;

    @Override
    public String resolve(MediaType mediaType, String storageKey) {

        String prefix = switch (mediaType) {

            case IMAGE -> mediaProperties.images().urlPrefix();

            case DOCUMENT -> mediaProperties.documents().urlPrefix();
        };

        return mediaProperties.publicBaseUrl() + prefix + "/" + storageKey;
    }
}
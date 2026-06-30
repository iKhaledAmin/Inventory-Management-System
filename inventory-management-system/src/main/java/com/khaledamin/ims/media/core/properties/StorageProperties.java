package com.khaledamin.ims.media.core.properties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "application.storage")
@Validated
public record StorageProperties(

        @NotBlank
        String rootPath,

        @NotNull @Valid
        MediaFolder images,

        @NotNull @Valid
        MediaFolder documents

) {

    public String imagesRoot() {
        return rootPath + "/" + images.folder();
    }

    public String documentsRoot() {
        return rootPath + "/" + documents.folder();
    }


    public record MediaFolder(@NotBlank String folder) {}
}
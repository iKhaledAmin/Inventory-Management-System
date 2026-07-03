package com.khaledamin.ims.media.core.model;

public enum MediaOwnerType {

    PROFILE("profile"),
    ORGANIZATION("organization"),
    STOCK("stock"),


    ;

    private final String folder;

    MediaOwnerType(String folder) {
        this.folder = folder;
    }

    public String folder() {
        return folder;
    }
}
package com.khaledamin.ims.identity.client.domain.model;


public enum ClientStatus {
    DISABLED,
    ACTIVE,
    LOCKED;


    // helper methods
    public static ClientStatus getDefault() {
        return DISABLED;
    }

    public boolean isDisabled() { return this == DISABLED;}

    public boolean isActive() { return this == ACTIVE;}

    public boolean isLocked(){ return this == LOCKED;}

}
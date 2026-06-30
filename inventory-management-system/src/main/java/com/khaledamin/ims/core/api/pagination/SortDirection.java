package com.khaledamin.ims.core.api.pagination;

public enum SortDirection {
    ASC,
    DESC
    ;
    public static String getDefault() {
        return DESC.toString();
    }

    public static String getFrom(String direction) {
        try {
            return SortDirection.valueOf(direction).toString();
        } catch (IllegalArgumentException e) {
            return getDefault();
        }
    }
}
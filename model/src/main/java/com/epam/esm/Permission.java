package com.epam.esm;

public enum Permission {
    CERTIFICATE_READ("certificate:read"),
    CERTIFICATE_WRITE("certificate:write"),

    ORDER_READ("order:read"),
    ORDER_WRITE("order:write"),

    USER_READ("user:read"),

    TAG_READ("tag:read"),
    TAG_WRITE("tag:write");

    private final String permission;

    Permission(String permission){
        this.permission=permission;
    }

    public String getPermission() {
        return permission;
    }
}

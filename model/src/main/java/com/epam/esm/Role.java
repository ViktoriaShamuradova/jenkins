package com.epam.esm;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {

    ROLE_USER(Set.of(Permission.CERTIFICATE_READ, Permission.ORDER_READ, Permission.ORDER_WRITE,
            Permission.TAG_READ, Permission.USER_READ)),
    ROLE_ADMINISTRATOR(Set.of(Permission.CERTIFICATE_READ, Permission.CERTIFICATE_WRITE,
            Permission.ORDER_READ, Permission.ORDER_WRITE,
            Permission.USER_READ, Permission.TAG_WRITE, Permission.TAG_READ)),
    ROLE_GUEST(Set.of(Permission.CERTIFICATE_READ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}

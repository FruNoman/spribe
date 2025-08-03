package com.spribe.test.rest.services.player.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum UserRole {
    SUPERVISOR("supervisor"),
    ADMIN("admin"),
    USER("user"),

    EMPTY(""),
    BLANK_SPACE(" "),
    INVALID_ROLE("invalidRole");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static List<String> validCreationRoles() {
        return Arrays.stream(new UserRole[]{SUPERVISOR, ADMIN})
                .map(UserRole::getRole)
                .collect(Collectors.toList());
    }

    public static List<String> invalidRoles() {
        return Arrays.stream(new UserRole[]{
                        EMPTY, BLANK_SPACE, INVALID_ROLE
                })
                .map(UserRole::getRole)
                .collect(Collectors.toList());
    }
}

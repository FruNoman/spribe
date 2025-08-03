package com.spribe.test.rest.services.player.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum UserRole {
    SUPERVISOR("supervisor"),
    ADMIN("admin"),

    USER("user"),
    INVALID_ROLE("invalidRole");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

package com.example.summerpractic2023.base;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, ORG;

    @Override
    public String getAuthority() {
        return name();
    }
}

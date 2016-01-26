package com.registration.demo.utils;


public enum UserRole {

    UNVERIFIED("ROLE_UNVERIFIED"), BLOCKED("ROLE_BLOCKED"), ADMIN("ROLE_ADMIN");

    private String name;

    private UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

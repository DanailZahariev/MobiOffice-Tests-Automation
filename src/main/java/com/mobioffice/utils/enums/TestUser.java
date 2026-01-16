package com.mobioffice.utils.enums;

public enum TestUser {

    STANDARD_USER("YOUR NAME", "PASSWORD", "Test User");

    private final String email;
    private final String password;
    private final String expectedName;

    TestUser(String email, String password, String expectedName) {
        this.email = email;
        this.password = password;
        this.expectedName = expectedName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getExpectedName() {
        return expectedName;
    }
}

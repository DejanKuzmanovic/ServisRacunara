package com.servisracunara.pmf.dto;

import javax.validation.constraints.NotNull;

public class UserDTO {

    @NotNull
    private String username;

    private String fullName;

    private String password;

    @NotNull
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDTO [fullName=").append(fullName).append(", password=")
                .append("[PROTECTED]").append(", matchingPassword=").append("[PROTECTED]")
                .append(", email=").append(email).append("]");
        return builder.toString();
    }
}


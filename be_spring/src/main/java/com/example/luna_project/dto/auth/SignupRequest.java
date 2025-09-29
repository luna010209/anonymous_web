package com.example.luna_project.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record SignupRequest(
        @NotBlank(message = "Username is required!!!")
        String username,

        @NotBlank(message = "Password is required!!!")
        String password,

        @NotBlank(message = "Please confirm password!!!")
        String cfPassword,

        @NotBlank(message = "Phone number is required!!!")
        String phone,

        @NotBlank(message = "Email is required!!!")
        String email
) {
}

package com.example.luna_project.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Please input your email or phone number")
        String emailOrPhone,

        @NotBlank(message = "Please input your password")
        String password
) {
}
